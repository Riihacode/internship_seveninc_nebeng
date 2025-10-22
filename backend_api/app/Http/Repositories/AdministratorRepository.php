<?php

namespace App\Http\Repositories;

use App\Models\User;
use App\Models\Administrator;
use Illuminate\Support\Facades\DB;

class AdministratorRepository{

    protected $administrator;
    protected $user;

    public function __construct(Administrator $administrator, User $user){
        $this->administrator = $administrator;
        $this->user = $user;
    }

    public function createAdministrator(array $data){
        return DB::transaction(function() use ($data){
            $user = $this->user->create([
            'name' => $data['name'],
            'username' => $data['username'],
            'email' => $data['email'],
            'password' => $data['password'],
            'user_type' => 'administrator',
            'banned' => 0
            ]);

            $administrator = $this->administrator->create([
            'user_id' => $user->id,
            'first_name' => $data['first_name'],
            'last_name' => $data['last_name'],
            'telephone' => $data['telephone'],
            'province_id' => $data['province_id'],
            'regency_id' => $data['regency_id'],
            'district_id' => $data['district_id'],
            'full_address' => $data['full_address'],
            ]);
            return $administrator->load('user');
        });
    }

    public function getAll(){
        return $this->administrator
            ->with('user')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    public function getById($id){
        return $this->administrator
            ->with('user')
            ->find($id);
    }

    public function getByUserId($userId){
        return $this->administrator
            ->where('user_id', $userId)
            ->first();
    }

    public function update($id,$data){
        $administrator = $this->administrator->findOrFail($id);
        $administrator->update($data);
        return $administrator;
    }

    public function delete($id){
        return $this->administrator
            ->findOrFail($id)
            ->delete();
    }

}
