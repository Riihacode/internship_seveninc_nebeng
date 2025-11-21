<?php

namespace App\Http\Repositories;

use App\Models\Customer;

class CustomerRepoAdmin
{
    protected $model;

    public function __construct(Customer $customer)
    {
        $this->model = $customer;
    }

    // Ambil semua pelanggan beserta relasi user
    public function getAll()
    {
        return $this->model
            ->with('user')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Ambil pelanggan berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with('user')
            ->find($id);
    }

    // Ambil pelanggan berdasarkan user_id
    public function findByUserId($userId)
    {
        return $this->model
            ->where('user_id', $userId)
            ->with('user')
            ->first();
    }

    // Tambah pelanggan baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update pelanggan
    public function update($id, array $data)
    {
        $customer = $this->model->findOrFail($id);
        $customer->update($data);
        return $customer;
    }

    // Hapus pelanggan
    public function delete($id)
    {
        $customer = $this->model->findOrFail($id);
        return $customer->delete();
    }
    public function paginate($perPage = 10, $filters = [])
    {
        $query = $this->model
            ->with('user')
            ->orderBy('created_at', 'DESC');

            // filter
            if(!empty($filters['status'])) {
                $query->where('verified', $filters['status']);
            }

            // search
            if(!empty($filters['search'])) {
                $search = $filters['search'];

                $query->where(function($q) use ($search) {
                $q->where('full_name', 'LIKE', "%$search%")
                  ->orWhere('telephone', 'LIKE', "%$search%")
                  ->orWhereHas('user', function ($u) use ($search){
                    $u->where('email', 'LIKE', "%$search%");
                  });
            });
            }

        return $query->paginate($perPage);
    }
}
