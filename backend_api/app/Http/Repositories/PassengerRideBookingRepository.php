<?php

namespace App\Http\Repositories;

use App\Models\PassengerRideBooking;

class PassengerRideBookingRepository
{
    protected $model;

    public function __construct(PassengerRideBooking $booking)
    {
        $this->model = $booking;
    }

    // Ambil semua booking beserta relasi
    public function getAll()
    {
        return $this->model
            ->with(['passengerRide', 'customer', 'passengerTransaction'])
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Ambil booking berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with(['passengerRide', 'customer', 'passengerTransaction'])
            ->find($id);
    }

    // Ambil booking berdasarkan passenger_ride_id
    public function getByRide($rideId)
    {
        return $this->model
            ->where('passenger_ride_id', $rideId)
            ->with(['customer', 'passengerTransaction'])
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Ambil booking berdasarkan customer_id
    public function getByCustomer($customerId)
    {
        return $this->model
            ->where('customer_id', $customerId)
            ->with(['passengerRide', 'passengerTransaction'])
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Tambah booking
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update booking
    public function update($id, array $data)
    {
        $booking = $this->model->findOrFail($id);
        $booking->update($data);
        return $booking;
    }

    // Hapus booking
    public function delete($id)
    {
        $booking = $this->model->findOrFail($id);
        return $booking->delete();
    }
}
