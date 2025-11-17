<?php

namespace App\Http\Services;

use App\Http\Repositories\UserRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;
use App\Models\User;

class UserService
{
    protected $repo;

    public function __construct(UserRepository $repo)
    {
        $this->repo = $repo;
    }

    public function list()
    {
        return $this->repo->getAllUser();
    }

    public function get($id)
    {
        return $this->repo->findUserById($id);
    }

    public function create(array $data)
    {
        $validator = Validator::make($data, [
            'name'      => 'required|string|max:255', // DIPERTAHANKAN
            'username'  => 'required|string|unique:users,username',
            'email'     => 'required|email|unique:users,email',
            'password'  => 'required|string|min:6',
            'user_type' => 'required|in:superadmin,admin,finance,customer,driver',
            'banned'    => 'nullable|boolean',
        ]);

        if ($validator->fails()) throw new ValidationException($validator);
        return $this->repo->createUser($data);
    }

    public function update($id, array $data)
    {
        $user = User::find($id);
        if (!$user) throw ValidationException::withMessages(['user' => 'User not found']);

        $validator = Validator::make($data, [
            'name'      => 'sometimes|string|max:255', // DIPERTAHANKAN
            'username'  => 'sometimes|string|unique:users,username,' . $id,
            'email'     => 'sometimes|email|unique:users,email,' . $id,
            'password'  => 'sometimes|string|min:6',
            'user_type' => 'sometimes|in:superadmin,admin,finance,customer,driver',
            'banned'    => 'sometimes|boolean',
        ]);

        if ($validator->fails()) throw new ValidationException($validator);
        return $this->repo->updateUser($user, $data);
    }

    public function delete($id)
    {
        $user = User::find($id);
        if (!$user) return false;
        return $this->repo->deleteUser($user);
    }
}
