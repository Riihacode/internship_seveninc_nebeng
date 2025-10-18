<?php

namespace App\Http\Repositories;

use App\Models\DriverWithdrawal;

class DriverWithdrawalRepository
{
    protected $model;

    public function __construct(DriverWithdrawal $driverWithdrawal)
    {
        $this->model = $driverWithdrawal;
    }

    // Ambil semua withdrawal
    public function getAll()
    {
        return $this->model
            ->with('driver')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Ambil withdrawal berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with('driver')
            ->find($id);
    }

    // Ambil semua withdrawal berdasarkan driver_id
    public function getByDriver($driverId)
    {
        return $this->model
            ->where('driver_id', $driverId)
            ->with('driver')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Filter berdasarkan status
    public function getByStatus($status)
    {
        return $this->model
            ->where('status', $status)
            ->with('driver')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Buat withdrawal baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update withdrawal
    public function update($id, array $data)
    {
        $withdrawal = $this->model->findOrFail($id);
        $withdrawal->update($data);
        return $withdrawal;
    }

    // Hapus withdrawal
    public function delete($id)
    {
        $withdrawal = $this->model->findOrFail($id);
        return $withdrawal->delete();
    }
}
