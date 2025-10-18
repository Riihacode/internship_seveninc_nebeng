<?php

namespace App\Http\Repositories;

use App\Models\GoodsTransaction;

class GoodsTransactionRepository
{
    protected $model;

    public function __construct(GoodsTransaction $goodsTransaction)
    {
        $this->model = $goodsTransaction;
    }

    public function getAll()
    {
        return $this->model->with(['goodsRideBooking', 'customer', 'paymentMethod'])->get();
    }

    public function findById($id)
    {
        return $this->model->with(['goodsRideBooking', 'customer', 'paymentMethod'])->findOrFail($id);
    }

    public function getByCustomer($customerId)
    {
        return $this->model->where('customer_id', $customerId)
            ->with(['goodsRideBooking', 'paymentMethod'])
            ->get();
    }

    public function getByStatus($status)
    {
        return $this->model->where('payment_status', $status)
            ->with(['goodsRideBooking', 'customer'])
            ->get();
    }

    public function create(array $data)
    {
        return $this->model->create($data);
    }

    public function update($id, array $data)
    {
        $transaction = $this->findById($id);
        $transaction->update($data);
        return $transaction;
    }

    public function delete($id)
    {
        $transaction = $this->findById($id);
        return $transaction->delete();
    }
}
