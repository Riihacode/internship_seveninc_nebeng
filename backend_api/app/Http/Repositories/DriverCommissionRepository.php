<?php

namespace App\Http\Repositories;

use App\Models\DriverCommission;

class DriverCommissionRepository
{
    protected $model;

    public function __construct(DriverCommission $driverCommission)
    {
        $this->model = $driverCommission;
    }

    // Ambil semua komisi beserta driver-nya
    public function getAll()
    {
        return $this->model
            ->with('driver')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Ambil komisi berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with('driver')
            ->find($id);
    }

    // Ambil semua komisi milik driver tertentu
    public function getByDriver($driverId)
    {
        return $this->model
            ->where('driver_id', $driverId)
            ->with('driver')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Tambah komisi baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update komisi (jarang digunakan, tapi disiapkan)
    public function update($id, array $data)
    {
        $commission = $this->model->findOrFail($id);
        $commission->update($data);
        return $commission;
    }

    // Hapus komisi
    public function delete($id)
    {
        $commission = $this->model->findOrFail($id);
        return $commission->delete();
    }
}
