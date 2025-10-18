<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\PassengerRideService;
use Illuminate\Validation\ValidationException;

class PassengerRideController extends Controller
{
    //
    protected $passengerRideService;

    public function __construct(PassengerRideService $service)
    {
        $this->passengerRideService = $service;
    }

    // GET /api/passenger-rides
    public function index()
    {
        $rides = $this->passengerRideService->listRides();
        return response()->json(['data' => $rides], 200);
    }

    // GET /api/passenger-rides/{id}
    public function show($id)
    {
        $ride = $this->passengerRideService->getRide($id);
        if (!$ride) {
            return response()->json(['message' => 'Passenger ride not found'], 404);
        }
        return response()->json(['data' => $ride], 200);
    }

    // GET /api/passenger-rides/driver/{driver_id}
    public function byDriver($driverId)
    {
        $rides = $this->passengerRideService->listByDriver($driverId);
        return response()->json(['data' => $rides], 200);
    }

    // GET /api/passenger-rides/status/{status}
    public function byStatus($status)
    {
        $rides = $this->passengerRideService->listByStatus($status);
        return response()->json(['data' => $rides], 200);
    }

    // POST /api/passenger-rides
    public function store(Request $request)
    {
        try {
            $ride = $this->passengerRideService->createRide($request->all());
            return response()->json(['data' => $ride], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/passenger-rides/{id}
    public function update(Request $request, $id)
    {
        try {
            $ride = $this->passengerRideService->updateRide($id, $request->all());
            return response()->json(['data' => $ride], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // DELETE /api/passenger-rides/{id}
    public function destroy($id)
    {
        $deleted = $this->passengerRideService->deleteRide($id);
        if ($deleted) {
            return response()->json(['message' => 'Passenger ride deleted successfully'], 200);
        }
        return response()->json(['message' => 'Passenger ride not found'], 404);
    }
}
