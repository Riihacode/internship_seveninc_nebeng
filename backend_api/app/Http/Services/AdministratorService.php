<?php

namespace App\Http\Services;

use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;
use App\Http\Repositories\AdministratorRepository;

class AdministratorService{

    protected $administratorRepository;

    public function __construct(AdministratorRepository $repo){
        $this->administratorRepository = $repo;
    }

    public function listAdministrator(){
        return $this->administratorRepository->getAll();
    }

    public function getAdministrator($id){
        return $this->administratorRepository->getById($id);
    }

    public function getAdministratorByUserId($userId){
        return $this->administratorRepository->getByUserId($userId);
    }

    public function createAdministrator($data){
        $validator = Validator::make($data, [
            'name' => 'required|string|max:255',
            'username' => 'required|string|max:255|unique:users',
            'email' => 'required|string|email|max:255|unique:users',
            'password' => 'required|string|min:8|confirmed',
            'first_name' => 'required|string',
            'last_name' => 'required|string',
            'province_id' => 'required',
            'regency_id' => 'required',
            'district_id' => 'required',
            'telephone' => 'required|string',
            'full_address' => 'required|string|max:255'
        ]);

        if($validator->fails()){
            throw ValidationException::withMessages($validator->errors()->toArray());
        }

        return $this->administratorRepository->createAdministrator($validator->validated());

    }

    public function updateAdministrator($id, $data){
        $validator = Validator::make($data, [
            'first_name' => 'sometimes|string',
            'last_name' => 'sometimes|string',
            'province_id' => 'sometimes',
            'regency_id' => 'sometimes',
            'district_id' => 'sometimes',
            'full_address' => 'sometimes|string|max:255'
        ]);

        if($validator->fails()){
            throw new ValidationException($validator);
        }

        return $this->administratorRepository->update($id, $data);
    }

    public function deleteAdministrator($id){
        return $this->administratorRepository->delete($id);
    }
}
