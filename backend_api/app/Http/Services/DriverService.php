<?php

namespace App\Http\Services;

use App\Http\Repositories\DriverRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

class DriverService
{
    protected $driverRepository;

    public function __construct(DriverRepository $driverRepository)
    {
        $this->driverRepository = $driverRepository;
    }

    // List semua driver
    public function listDrivers()
    {
        return $this->driverRepository->getAll();
    }

    // Ambil driver by ID
    public function getDriver($id)
    {
        return $this->driverRepository->findById($id);
    }

    // Buat driver baru
    public function createDriver(array $data)
    {
        $validator = Validator::make($data, [
            'user_id'                               => 'required|exists:users,id',
            'full_name'                             => 'required|string|max:255',
            'telephone'                             => 'required|string|max:20',
            'full_address'                          => 'nullable|string|max:255',
            'profile_img'                           => 'nullable|string|max:255',
            'balance'                               => 'nullable|integer|min:0',
            'credit_score'                          => 'nullable|integer|min:0',

            // ID Card
            'face_img'                              => 'nullable|string|max:255',
            'face_with_id_img'                      => 'nullable|string|max:255',
            'id_card_img'                           => 'nullable|string|max:255',
            'id_card_number'                        => 'nullable|string|max:50',
            'id_card_fullname'                      => 'nullable|string|max:255',
            'id_card_birthdate'                     => 'nullable|date',
            'id_card_verified'                      => 'nullable|boolean',
            'id_card_rejected_reason'               => 'nullable|string|max:255',

            // Driver License
            'driver_license_number'                 => 'nullable|string|max:50',
            'driver_license_type'                   => 'nullable|string|max:10',
            'driver_license_expired'                => 'nullable|date',
            'driver_license_img'                    => 'nullable|string|max:255',
            'driver_license_verified'               => 'nullable|boolean',
            'driver_license_rejected_reason'        => 'nullable|string|max:255',

            // Police Clearance
            'Police_clearance_certificate_number'   => 'nullable|string|max:50',
            'Police_clearance_certificate_fullname' => 'nullable|string|max:255',
            'Police_clearance_certificate_expired'  => 'nullable|date',
            'Police_clearance_certificate_img'      => 'nullable|string|max:255',
            'Police_clearance_verified'             => 'nullable|boolean',
            'Police_clearance_rejected_reason'      => 'nullable|string|max:255',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->driverRepository->create($data);
    }

    // Update driver
    public function updateDriver($id, array $data)
    {
        $validator = Validator::make($data, [
            'full_name'                 => 'sometimes|required|string|max:255',
            'telephone'                 => 'sometimes|required|string|max:20',
            'full_address'              => 'nullable|string|max:255',
            'profile_img'               => 'nullable|string|max:255',
            'balance'                   => 'nullable|integer|min:0',
            'credit_score'              => 'nullable|integer|min:0',

            // Dokumen verifikasi
            'id_card_verified'          => 'nullable|boolean',
            'driver_license_verified'   => 'nullable|boolean',
            'Police_clearance_verified' => 'nullable|boolean',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->driverRepository->update($id, $data);
    }

    // Hapus driver
    public function deleteDriver($id)
    {
        return $this->driverRepository->delete($id);
    }
}
