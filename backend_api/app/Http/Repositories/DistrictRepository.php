<?php

namespace App\Http\Repositories;

use App\Models\District;

class DistrictRepository {
    protected $model;

    public function __construct(District $district) {
        $this->model = $district;
    }

    public function getAll() {
        return $this->model
            ->with('regency:id,province_id,name')
            ->orderBy('name', 'ASC')
            ->get();
    }

    public function getByRegency($regencyId) {
        return $this->model
            ->where('regency_id', $regencyId)
            ->with('regency:id,province_id,name')
            ->orderBy('name', 'ASC')
            ->get();
    }

    public function findById($id) {
        return $this->model
            ->with('regency:id,province_id,name')
            ->findOrFail($id);
    }
}
