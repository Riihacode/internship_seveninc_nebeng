<?php

namespace App\Http\Services;

use App\Http\Repositories\RegencyRepository;

class RegencyService {
    protected $regencyRepository;

    public function __construct(RegencyRepository $regencyRepository) {
        $this->regencyRepository = $regencyRepository;
    }

    public function listRegencies() {
        return $this->regencyRepository->getAll();
    }

    public function listByProvince($provinceId) {
        return $this->regencyRepository->getByProvince($provinceId);
    }

    public function getRegency($id) {
        return $this->regencyRepository->findById($id);
    }
}
