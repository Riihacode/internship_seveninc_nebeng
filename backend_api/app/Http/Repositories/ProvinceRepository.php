<?php

namespace App\Http\Repositories;

use App\Models\Province;

class ProvinceRepository {
    protected $model;

    public function __construct(Province $province) {
        $this->model = $province;
    }

    public function getAll() {
        return $this->model
            ->with('regencies:id,province_id,name')
            ->orderBy('name', 'ASC')
            ->get();
    }

    public function findById($id) {
        return $this->model
            ->with('regencies:id,province_id,name') //province punya relasi one to many ke tabel regencies sehingga sekalian panggil field province_id pada tabel regencies agar lebih efisien
            ->findOrFail($id);
    }
}
