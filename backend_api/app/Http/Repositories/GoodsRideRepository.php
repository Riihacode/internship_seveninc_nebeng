<?php

namespace App\Http\Repositories;

use App\Models\GoodsRide;

class GoodsRideRepository {
    protected $model;

    public function __construct(GoodsRide $goodsRide) {
        $this->model = $goodsRide;
    }

    public function getAll() {
        return $this->model
            ->with(['driver', 'departureTerminal', 'arrivalTerminal'])
            ->orderBy('departure_time', 'ASC')
            ->get();
    }

    public function findById($id) {
        return $this->model
            ->with(['driver', 'departureTerminal', 'arrivalTerminal', 'goodsRideBookings'])
            ->find($id);
    }

    public function create(array $data) {
        return $this->model->create($data);
    }

    public function update($id, array $data) {
        $goodsRide = $this->model->findOrFail($id);
        $goodsRide->update($data);
        return $goodsRide;
    }

    public function delete($id) {
        $goodsRide = $this->model->findOrFail($id);
        return $goodsRide->delete();
    }
}
