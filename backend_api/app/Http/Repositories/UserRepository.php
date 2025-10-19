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
            'role' => $data['role'],
            'password' => Hash::make($data['password']),
        ]);
    }

    public function findByEmailOrUsername(string $userIdentifier){
        return User::where('email', $userIdentifier)
            ->orWhere('username', $userIdentifier)
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
