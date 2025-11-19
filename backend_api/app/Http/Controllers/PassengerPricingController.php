<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\PassengerPricingService;
use Illuminate\Validation\ValidationException;

class PassengerPricingController extends Controller
{
    //
    protected $service;

    public function __construct(PassengerPricingService $service)
    {
        $this->service = $service;
    }

    public function index()
    {
        return response()->json(['data' => $this->service->list()], 200);
    }

    public function show($id)
    {
        $pricing = $this->service->get($id);
        if (!$pricing) return response()->json(['message' => 'Passenger pricing not found'], 404);
        return response()->json(['data' => $pricing], 200);
    }

    public function store(Request $request)
    {
        try {
            $pricing = $this->service->create($request->all());
            return response()->json(['data' => $pricing], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    public function update(Request $request, $id)
    {
        try {
            $pricing = $this->service->update($id, $request->all());
            return response()->json(['data' => $pricing], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    public function destroy($id)
    {
        $deleted = $this->service->delete($id);
        if (!$deleted) return response()->json(['message' => 'Passenger pricing not found'], 404);
        return response()->json(['message' => 'Passenger pricing deleted'], 200);
    }
}
