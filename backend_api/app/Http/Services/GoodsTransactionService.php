<?php

namespace App\Http\Services;

use App\Http\Repositories\GoodsTransactionRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

class GoodsTransactionService
{
    protected $repository;

    public function __construct(GoodsTransactionRepository $repo)
    {
        $this->repository = $repo;
    }

    public function listTransactions()
    {
        return $this->repository->getAll();
    }

    public function getTransaction($id)
    {
        return $this->repository->findById($id);
    }

    public function listByCustomer($customerId)
    {
        return $this->repository->getByCustomer($customerId);
    }

    public function listByStatus($status)
    {
        return $this->repository->getByStatus($status);
    }

    public function createTransaction(array $data)
    {
        $validator = Validator::make($data, [
            'goods_ride_booking_id' => 'required|exists:goods_ride_bookings,id',
            'customer_id'           => 'required|exists:customers,id',
            'total_amount'          => 'required|numeric|min:0',
            'payment_method_id'     => 'required|exists:payment_methods,id',
            'payment_proof_img'     => 'nullable|string|max:255',
            'payment_status'        => 'required|string|in:Pending,Diterima,Ditolak,Credited',
            'credit_used'           => 'nullable|integer|min:0',
            'transaction_date'      => 'nullable|date',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->repository->create($data);
    }

    public function updateTransaction($id, array $data)
    {
        $validator = Validator::make($data, [
            'payment_status'   => 'sometimes|string|in:Pending,Diterima,Ditolak,Credited',
            'payment_proof_img'=> 'nullable|string|max:255',
            'credit_used'      => 'nullable|integer|min:0',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->repository->update($id, $data);
    }

    public function deleteTransaction($id)
    {
        return $this->repository->delete($id);
    }
}
