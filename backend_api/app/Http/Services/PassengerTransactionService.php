<?php

namespace App\Http\Services;

use App\Http\Repositories\PassengerTransactionRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

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

    // Tambah transaksi baru
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

        // Default status
        $data['payment_status'] = $data['payment_status'] ?? 'Pending';
        $data['transaction_date'] = $data['transaction_date'] ?? now();

        return $this->transactionRepository->create($data);
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
