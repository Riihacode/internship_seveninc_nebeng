<?php

namespace App\Http\Repositories;

use App\Models\Regency;

class RegencyRepository {
    protected $model;

    public function __construct(Regency $regency) {
        $this->model = $regency;
    }

    public function getAll() {
        return $this->model
            ->with(['province:id,name', 'districts:id,name'])
            ->orderBy('name', 'ASC')
            ->get();
    }

    public function getByProvince($provinceId) {
        return $this->model
            ->where('province_id', $provinceId)
            ->with(['districts:id,regency_id,name'])
            ->orderBy('name', 'ASC')
            ->get();
    }

    public function findById($id) {
        return $this->model
            ->with(['province:id,name', 'districts:id,regency_id,name'])
            ->findOrFail($id);
    }
}
