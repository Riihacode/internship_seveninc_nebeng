<?php

namespace App\Http\Services;

use Carbon\Carbon;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;
use App\Http\Repositories\PassengerRideBookingRepository;

class PassengerRideBookingService
{
    protected $bookingRepository;

    public function __construct(PassengerRideBookingRepository $repo)
    {
        $this->bookingRepository = $repo;
    }

     protected function generateBookingNumbers($prefix = 'P'){
        $date = Carbon :: now()->format('Ymd');
        $countToday = $this->bookingRepository->countByDate(Carbon::today());

        $sequence = str_pad($countToday + 1, 4, '0', STR_PAD_LEFT);
        return "{$prefix}-{$date}-{$sequence}";
    }


    // List semua booking
    public function listBookings()
    {
        return $this->bookingRepository->getAll();
    }

    // Detail booking
    public function getBooking($id)
    {
        return $this->bookingRepository->findById($id);
    }

    // Booking per ride
    public function listByRide($rideId)
    {
        return $this->bookingRepository->getByRide($rideId);
    }

    // Get by Code
    public function getByCode(string $code) {
        return $this->bookingRepository->getByCode($code);
    }

    // Booking per customer
    public function listByCustomer($customerId)
    {
        return $this->bookingRepository->getByCustomer($customerId);
    }

    // Tambah booking baru
    public function createBooking(array $data)
    {
        $validator = Validator::make($data, [
            'passenger_ride_id' => 'required|exists:passenger_rides,id',
            'customer_id'       => 'required|exists:customers,id',
            'seats_reserved'    => 'required|integer|min:1',
            'total_price'       => 'required|integer|min:0',
            'status'            => 'nullable|string|in:Pending,Diterima,Ditolak',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        // Default status jika tidak diset
        $data['status'] = $data['status'] ?? 'Pending';
        $data['booking_code'] = $this->generateBookingNumbers('P');
        return $this->bookingRepository->create($data);
    }

    // Update booking
    public function updateBooking($id, array $data)
    {
        $validator = Validator::make($data, [
            'seats_reserved'    => 'sometimes|integer|min:1',
            'total_price'       => 'sometimes|integer|min:0',
            'status'            => 'sometimes|string|in:Pending,Diterima,Ditolak',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->bookingRepository->update($id, $data);
    }

    // Hapus booking
    public function deleteBooking($id)
    {
        return $this->bookingRepository->delete($id);
    }

    // Update status booking (misalnya diterima / ditolak)
    public function updateStatus($id, string $status)
    {
        $validStatuses = ['Pending', 'Diterima', 'Ditolak'];
        if (!in_array($status, $validStatuses)) {
            throw ValidationException::withMessages([
                'status' => 'Invalid booking status value.',
            ]);
        }

        return $this->bookingRepository->update($id, ['status' => $status]);
    }
}
