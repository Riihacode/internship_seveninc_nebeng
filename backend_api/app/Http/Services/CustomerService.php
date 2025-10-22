<?php

namespace App\Http\Services;

use App\Http\Repositories\CustomerRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

class CustomerService
{
    protected $customerRepository;

    public function __construct(CustomerRepository $repo)
    {
        $this->customerRepository = $repo;
    }

    // List semua customer
    public function listCustomers()
    {
        return $this->customerRepository->getAll();
    }

    // Ambil 1 customer
    public function getCustomer($id)
    {
        return $this->customerRepository->findById($id);
    }

    // Ambil customer berdasarkan user_id
    public function getCustomerByUser($userId)
    {
        return $this->customerRepository->findByUserId($userId);
    }

    // Buat customer baru
    public function createCustomer(array $data)
    {
        $validator = Validator::make($data, [
            'user_id'           => 'required|exists:users,id|unique:customers,user_id',
            'full_name'         => 'required|string|max:255',
            'telephone'         => 'required|string|max:20',
            'full_address'      => 'required|string|max:255',
            'profile_img'       => 'nullable|string',
            'verified'          => 'nullable|boolean',
            'face_img'          => 'nullable|string',
            'face_with_id_img'  => 'nullable|string',
            'id_card_img'       => 'nullable|string',
            'id_card_number'    => 'nullable|string|max:50',
            'id_card_fullname'  => 'nullable|string|max:255',
            'id_card_birthdate' => 'nullable|date',
            'credit_amount'     => 'nullable|integer|min:0',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        $data['verified'] = $data['verified'] ?? false;
        $data['credit_amount'] = $data['credit_amount'] ?? 0;

        return $this->customerRepository->create($data);
    }

    // Update data customer
    public function updateCustomer($id, array $data)
    {
        $validator = Validator::make($data, [
            'full_name'         => 'sometimes|string|max:255',
            'telephone'         => 'sometimes|string|max:20',
            'full_address'      => 'sometimes|string|max:255',
            'profile_img'       => 'nullable|string',
            'verified'          => 'sometimes|boolean',
            'face_img'          => 'nullable|string',
            'face_with_id_img'  => 'nullable|string',
            'id_card_img'       => 'nullable|string',
            'id_card_number'    => 'nullable|string|max:50',
            'id_card_fullname'  => 'nullable|string|max:255',
            'id_card_birthdate' => 'nullable|date',
            'credit_amount'     => 'sometimes|integer|min:0',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->customerRepository->update($id, $data);
    }

    // Hapus customer
    public function deleteCustomer($id)
    {
        return $this->customerRepository->delete($id);
    }

    // Verifikasi customer
    public function verifyCustomer($id)
    {
        return $this->customerRepository->update($id, [
            'verified' => true,
        ]);
    }

    // Tambah kredit pelanggan
    public function addCredit($id, int $amount)
    {
        $customer = $this->customerRepository->findById($id);
        if (!$customer) return null;

        $newCredit = $customer->credit_amount + $amount;
        return $this->customerRepository->update($id, ['credit_amount' => $newCredit]);
    }

    // Kurangi kredit pelanggan
    public function deductCredit($id, int $amount)
    {
        $customer = $this->customerRepository->findById($id);
        if (!$customer) return null;

        $newCredit = max(0, $customer->credit_amount - $amount);
        return $this->customerRepository->update($id, ['credit_amount' => $newCredit]);
    }
}
