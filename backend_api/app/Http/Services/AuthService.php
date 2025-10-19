<?php

namespace App\Http\Services;

use App\Http\Repositories\UserRepository;
use Illuminate\Support\Facades\Hash;
use Illuminate\Validation\ValidationException;

class AuthService{
    protected $userRepo;

    public function __construct(UserRepository $userRepo){
        $this->userRepo = $userRepo;
    }

    public function register(array $data){
        $user = $this->userRepo->createUser($data);
        $token = $user->createToken('api-token')->plainTextToken;

        return [
            'user' => $user,
            'token' => $token
        ];
    }

    public function login(array $data){

        $user = $this->userRepo->findByEmailOrUsername($data['userIdentifier']);

        if (! $user || ! Hash::check($data['password'], $user->password)) {
            throw ValidationException::withMessages([
                'login' => ['Invalid credentials.'],
            ]);
        }

        $token = $user->createToken('api-token')->plainTextToken;

        return [
            'user' => $user,
            'token' => $token
        ];
    }

    public function logout($user){
        $user->tokens()->delete();
        return ['message' => 'Logout successfully'];
    }

}
