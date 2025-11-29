<?php

namespace App\Http\Services;

use Exception;
use App\Models\GoodsTransaction;
use App\Models\PassengerRideBooking;
use App\Models\PassengerTransaction;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;
use App\Http\Repositories\PassengerTransactionRepository;

class PassengerTransactionService
{
    protected $transactionRepository;

    public function __construct(PassengerTransactionRepository $repo)
    {
        $this->transactionRepository = $repo;
    }

    // List semua transaksi
    public function listTransactions()
    {
        return $this->transactionRepository->getAll();
    }

    // Detail transaksi
    public function getTransaction($id)
    {
        return $this->transactionRepository->findById($id);
    }

    // Transaksi per customer
    public function listByCustomer($customerId)
    {
        return $this->transactionRepository->getByCustomer($customerId);
    }

    // Transaksi per booking
    public function getByBooking($bookingId)
    {
        return $this->transactionRepository->getByBooking($bookingId);
    }

    public function createTransaction(array $data)
    {
        $validator = Validator::make($data, [
            'passenger_ride_booking_id' => 'required|exists:passenger_ride_bookings,id',
            'customer_id' => 'required|exists:customers,id',
            'total_amount' => 'required|integer|min:0',
            'payment_method_id' => 'required|exists:payment_methods,id',
            'payment_proof_img' => 'nullable|string',
            'payment_status' => 'nullable|string|in:Pending,Diterima,Ditolak,Credited',
            'credit_used' => 'nullable|integer|min:0',
            'transaction_date' => 'nullable|date',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        $booking = PassengerRideBooking::find($data['passenger_ride_booking_id']);
        if(!$booking){
            throw ValidationException::withMessages([
                'passenger_ride_booking_id' => 'Booking not found',
            ]);
        }

        // Generate transaction code
        $transaction_code = $this->generateCode($booking->booking_code);
        $data['transaction_code'] = $transaction_code;
        $data['payment_status'] = $data['payment_status'] ?? 'Pending';
        $data['transaction_date'] = $data['transaction_date'] ?? now();

        // 1️⃣ Simpan transaksi dulu (status masih Pending)
        $transaction = $this->transactionRepository->create($data);

        // 2️⃣ Siapkan payload Midtrans /charge
        $payload = [
            "payment_type" => "bank_transfer",
            "transaction_details" => [
                "order_id"      => $transaction->transaction_code,
                "gross_amount"  => $transaction->total_amount,
            ],
            "customer_details" => [
                "first_name" => $transaction->customer->full_name,
                "email"      => $transaction->customer->user->email ?? "email@example.com",
                "phone"      => $transaction->customer->telephone,
            ],
            "bank_transfer" => [
                "bank" => "bni"  // sementara fix dulu, nanti jika mau dynamic bisa berdasarkan payment_method_id
            ]
        ];

        // 3️⃣ Kirim ke Midtrans
        $serverKey = config('midtrans.server_key');
        $apiUrl = config('midtrans.api_url') . 'charge';

        $response = Http::withBasicAuth($serverKey, "")
            ->withHeaders(["Content-Type" => "application/json"])
            ->post($apiUrl, $payload)
            ->json();

        // 4️⃣ Ambil data dari respon Midtrans
        $vaNumber = $response['va_numbers'][0]['va_number'] ?? null;
        $paymentType = $response['payment_type'] ?? null;
        $midtransTransactionId = $response['transaction_id'] ?? null;
        $orderId = $response['order_id'] ?? null;
        $expiredAt = $response['expiry_time'] ?? null;

        // 5️⃣ Update ke DB
        $transaction->update([
            'midtrans_order_id'       => $orderId,
            'midtrans_transaction_id' => $midtransTransactionId,
            'payment_type'            => $paymentType,
            'va_number'               => $vaNumber,
            'payment_expired_at'      => $expiredAt,
            'payment_response_raw'    => $response,
        ]);

        // 6️⃣ Return sama seperti sebelumnya (ada VA & raw_response)
        return $transaction->fresh([
            'customer',
            'passengerRideBooking',
            'paymentMethod'
        ]);
    }

    public function generateCode($bookingCode){
        $cleanCode = str_replace(['','_'], '-', strtoupper($bookingCode));
        $today = now()->format('Ymd');
        $countToday = PassengerTransaction::whereDate('created_at', now()->toDateString())->count()+1;

        return 'TX-'. $cleanCode . '-' . str_pad($countToday, 4, '0', STR_PAD_LEFT);
    }

    // Update transaksi (misal update bukti pembayaran)
    public function updateTransaction($id, array $data)
    {
        $validator = Validator::make($data, [
            'total_amount' => 'sometimes|integer|min:0',
            'payment_proof_img' => 'nullable|string',
            'payment_status' => 'sometimes|string|in:Pending,Diterima,Ditolak,Credited',
            'credit_used' => 'sometimes|integer|min:0',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->transactionRepository->update($id, $data);
    }

    // Hapus transaksi
    public function deleteTransaction($id)
    {
        return $this->transactionRepository->delete($id);
    }

    // Ubah status pembayaran
    public function updateStatus($id, string $status)
    {
        $validStatuses = ['Pending', 'Diterima', 'Ditolak', 'Credited'];
        if (!in_array($status, $validStatuses)) {
            throw ValidationException::withMessages([
                'status' => 'Invalid payment status value.',
            ]);
        }

        return $this->transactionRepository->update($id, [
            'payment_status' => $status,
        ]);
    }
}
