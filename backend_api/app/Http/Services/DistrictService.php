<?php

namespace App\Http\Services;

use App\Http\Repositories\DistrictRepository;

class DistrictService {
    protected $districtRepository;

    public function __construct(DistrictRepository $districtRepository) {
        $this->districtRepository = $districtRepository;
    }

    public function listDistricts() {
        return $this->districtRepository->getAll();
    }

    public function listByRegency($regencyId) {
        return $this->districtRepository->getByRegency($regencyId);
    }

    public function getDistrict($id) {
        return $this->districtRepository->findById($id);
    }
}
