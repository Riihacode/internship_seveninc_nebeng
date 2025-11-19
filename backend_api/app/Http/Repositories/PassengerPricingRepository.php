<?php

namespace App\Http\Repositories;

use App\Models\PassengerPricing;

class PassengerPricingRepository
{
    protected $model;

    public function __construct(PassengerPricing $passengerPricing)
    {
        $this->model = $passengerPricing;
    }

    // GET all
    public function getAll()
    {
        return $this->model
            ->with(['departureTerminal', 'arrivalTerminal'])
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // GET by ID
    public function findById($id)
    {
        return $this->model
            ->with(['departureTerminal', 'arrivalTerminal'])
            ->find($id);
    }

    // CREATE
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // UPDATE
    public function update(PassengerPricing $pricing, array $data)
    {
        $pricing->update($data);
        return $pricing;
    }

    // DELETE
    public function delete(PassengerPricing $pricing)
    {
        return $pricing->delete();
    }
}
