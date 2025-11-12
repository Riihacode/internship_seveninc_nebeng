<?php

namespace App\Http\Repositories;

use App\Models\Terminal;

class TerminalRepository {
    protected $model;

    public function __construct(Terminal $terminal) {
        $this->model = $terminal;
    }

    public function getAll() {
        return $this->model
            // ->with(['provinces', 'regencies', 'districts'])
            ->with(['province', 'regency', 'district'])
            ->orderBy('name', 'ASC')
            ->get();
    }

    public function findById($id) {
        return $this->model
            ->with([
                // 'provinces',
                // 'regencies',
                // 'districts',
                'province',
                'regency',
                'district',
                // 'departurePassengerRides',
                // 'arrivalPassengerRides',
                // 'departureGoodsRides',
                // 'departurePassengerPricings',
                // 'arrivalPassengerPricings',
                // 'departureGoodsPricings',
                // 'arrivalGoodsPricings',
            ])
            ->find($id);
    }

    public function create(array $data) {
        return $this->model->create($data);
    }

    public function update($id, array $data) {
        $terminal = $this->model->findOrFail($id);
        $terminal->update($data);
        return $terminal;
    }

    public function delete($id) {
        $terminal = $this->model->findOrFail($id);
        return $terminal->delete();
    }
}
