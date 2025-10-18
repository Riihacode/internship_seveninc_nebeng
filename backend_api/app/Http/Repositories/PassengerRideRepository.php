<?php

namespace App\Http\Repositories;

use App\Models\PassengerRide;

class PassengerRideRepository
{
    protected $model;

    public function __construct(PassengerRide $passengerRide)
    {
        $this->model = $passengerRide;
    }

    // Ambil semua ride beserta relasi
    public function getAll()
    {
        return $this->model
            ->with(['driver', 'departureTerminal', 'arrivalTerminal'])
            ->orderBy('departure_time', 'ASC')
            ->get();
    }

    // Ambil ride berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with(['driver', 'departureTerminal', 'arrivalTerminal', 'passengerRideBookings'])
            ->find($id);
    }

    // Ambil ride berdasarkan driver_id
    public function getByDriver($driverId)
    {
        return $this->model
            ->where('driver_id', $driverId)
            ->with(['departureTerminal', 'arrivalTerminal'])
            ->orderBy('departure_time', 'ASC')
            ->get();
    }

    // Filter ride berdasarkan status
    public function getByStatus($status)
    {
        return $this->model
            ->where('ride_status', $status)
            ->with(['driver', 'departureTerminal', 'arrivalTerminal'])
            ->orderBy('departure_time', 'ASC')
            ->get();
    }

    // Buat ride baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update ride
    public function update($id, array $data)
    {
        $ride = $this->model->findOrFail($id);
        $ride->update($data);
        return $ride;
    }

    // Hapus ride
    public function delete($id)
    {
        $ride = $this->model->findOrFail($id);
        return $ride->delete();
    }
}
