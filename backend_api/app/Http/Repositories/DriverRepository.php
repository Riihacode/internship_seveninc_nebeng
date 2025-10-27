<?php

namespace App\Http\Repositories;

use App\Models\Driver;

class DriverRepository
{
    protected $model;

    public function __construct(Driver $driver)
    {
        $this->model = $driver;
    }

    // Ambil semua driver beserta relasinya
    public function getAll()
    {
        return $this->model
            ->with(['user', 'vehicles', 'ratings'])
            ->orderBy('full_name', 'ASC')
            ->get();
    }

    // Ambil driver berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with([
                'user',
                'vehicles',
                'passengerRides',
                'goodsRides',
                'driverCommissions',
                'driverWithdrawals',
                'ratings'
            ])
            ->find($id);
    }

    // Buat driver baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update data driver
    public function update($id, array $data)
    {
        $driver = $this->model->findOrFail($id);
        $driver->update($data);
        return $driver;
    }

    // Hapus driver
    public function delete($id)
    {
        $driver = $this->model->findOrFail($id);
        return $driver->delete();
    }

    public function updateRating($driverId, $newRating){
        $driver = $this->findById($driverId);

        $driver->total_rating += $newRating;
        $driver->rating_count += 1;
        $driver->average_rating = $driver->total_rating / $driver->rating_count;
        $driver->save();

        return $driver;

    }
}
