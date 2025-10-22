<?php

namespace App\Http\Services;

use App\Http\Repositories\PassengerRideBookingRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

class PassengerRideBookingService
{
    protected $bookingRepository;

    public function __construct(PassengerRideBookingRepository $repo)
    {
        $this->bookingRepository = $repo;
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
