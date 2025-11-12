<?php

namespace App\Http\Repositories;

use App\Models\CreditScoreLog;

class CreditScoreLogRepository
{
    protected $model;

    public function __construct(CreditScoreLog $creditScoreLog)
    {
        $this->model = $creditScoreLog;
    }

    // Ambil semua log beserta relasi driver
    public function getAll()
    {
        return $this->model
            ->with('driver')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Ambil log berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with('driver')
            ->find($id);
    }

    // Ambil semua log milik driver tertentu
    public function getByDriverId($driverId)
    {
        return $this->model
            ->where('driver_id', $driverId)
            ->with('driver')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Tambah log baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update log berdasarkan ID
    public function update($id, array $data)
    {
        $log = $this->model->findOrFail($id);
        $log->update($data);
        return $log;
    }

    // Hapus log
    public function delete($id)
    {
        $log = $this->model->findOrFail($id);
        return $log->delete();
    }
}
