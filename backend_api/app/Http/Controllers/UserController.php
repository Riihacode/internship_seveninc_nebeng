<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\UserService;
use Illuminate\Validation\ValidationException;

class UserController extends Controller
{
    protected $service;

    public function __construct(UserService $service)
    {
        $this->service = $service;
    }

    public function index()
    {
        return response()->json(['data' => $this->service->list()], 200);
    }

    public function show($id)
    {
        $user = $this->service->get($id);
        if (!$user) return response()->json(['message' => 'User not found'], 404);
        return response()->json(['data' => $user], 200);
    }

    public function store(Request $request)
    {
        try {
            $user = $this->service->create($request->all());
            return response()->json(['data' => $user], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    public function update(Request $request, $id)
    {
        try {
            $user = $this->service->update($id, $request->all());
            return response()->json(['data' => $user], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    public function destroy($id)
    {
        $deleted = $this->service->delete($id);
        if (!$deleted) return response()->json(['message' => 'User not found'], 404);
        return response()->json(['message' => 'User deleted'], 200);
    }
}
