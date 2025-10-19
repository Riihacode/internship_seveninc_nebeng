<?php

namespace App\Http\Services;

use App\Http\Repositories\ProvinceRepository;

class ProvinceService {
    protected $provinceRepository;

    public function __construct(ProvinceRepository $provinceRepository) {
        $this->provinceRepository = $provinceRepository;
    }

    public function listProvinces() {
        return $this->provinceRepository->getAll();
    }

    public function getProvince($id) {
        return $this->provinceRepository->findById($id);
    }
}
