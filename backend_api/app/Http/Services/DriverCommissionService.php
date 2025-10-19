<?php

namespace App\Http\Services;

use App\Http\Repositories\DriverCommissionRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

class DriverCommissionService
{
    protected $driverCommissionRepository;

    public function __construct(DriverCommissionRepository $repo)
    {
        $this->driverCommissionRepository = $repo;
    }

    // Ambil semua komisi
    public function listCommissions()
    {
        return $this->driverCommissionRepository->getAll();
    }

    // Ambil komisi berdasarkan ID
    public function getCommission($id)
    {
        return $this->driverCommissionRepository->findById($id);
    }

    // Ambil semua komisi berdasarkan driver_id
    public function listByDriver($driverId)
    {
        return $this->driverCommissionRepository->getByDriver($driverId);
    }

    // Tambah komisi baru
    public function createCommission(array $data)
    {
        $validator = Validator::make($data, [
            'driver_id' => 'required|exists:drivers,id',
            'income' => 'required|integer|min:0',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->driverCommissionRepository->create($data);
    }

    // Update komisi (opsional)
    public function updateCommission($id, array $data)
    {
        $validator = Validator::make($data, [
            'income' => 'sometimes|integer|min:0',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->driverCommissionRepository->update($id, $data);
    }

    // Hapus komisi
    public function deleteCommission($id)
    {
        return $this->driverCommissionRepository->delete($id);
    }
}
