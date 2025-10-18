<?php

namespace App\Http\Repositories;

use App\Models\User;
use Illuminate\Support\Facades\Hash;

class UserRepository{

    protected $model;

    public function __construct(User $user) {
        $this->model = $user;
    }

    // public function createUser(array $data){
    //     return User::create([
    //         'name' => $data['name'],
    //         'username' => $data['username'],
    //         'email' => $data['email'],
    //         // 'role' => $data['role'],
    //         'password' => Hash::make($data['password']),
    //         'user_type' => $data['user_type'],
    //     ]);
    // }
    public function createUser(array $data) {
        return $this->model->create([
            'name'      => $data['name'],
            'username'  => $data ['username'],
            'email'     => $data['email'],
            'password'  => $data['password'],
            'user_type' => $data['user_type'],
        ]);
    }

    // public function findByEmailOrUsername(string $login){
    //     return User::where('email', $login)
    //         ->orWhere('username', $login)
    //         ->first();
    // }

    public function findByEmailOrUsername(string $login) {
        return $this->model
            ->where('email', $login)
            ->orWhere('username', $login)
            ->first();
    }

    public function updateUser(User $user, array $data){
        return $user->update($data);
    }

    public function deleteUser(User $user){
        return $user->delete();
    }

    // public function getAllUser(){
    //     return User::all();
    // }

    public function getAllUser() {
        return $this->model->all();
    }

    // public function findByIdUser($id){
    //     return User::findOrFail($id);
    // }

    public function findByIdUser($id) {
        return $this->model->findOrFail($id);
    }
}
