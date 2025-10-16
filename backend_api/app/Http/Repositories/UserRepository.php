<?php

namespace App\Http\Repositories;

use App\Models\User;
use Illuminate\Support\Facades\Hash;

class UserRepository{

    public function createUser(array $data){
        return User::create([
            'name' => $data['name'],
            'username' => $data['username'],
            'email' => $data['email'],
            // 'role' => $data['role'],
            'password' => Hash::make($data['password']),
            'user_type' => $data['user_type'],
        ]);
    }

    public function findByEmailOrUsername(string $login){
        return User::where('email', $login)
            ->orWhere('username', $login)
            ->first();
    }
    public function updateUser(User $user, array $data){
        return $user->update($data);
    }

    public function deleteUser(User $user){
        return $user->delete();
    }

    public function getAllUser(){
        return User::all();
    }

    public function findByIdUser($id){
        return User::findOrFail($id);
    }
}
