<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\DriverCommissionService;
use Illuminate\Validation\ValidationException;

class DriverCommissionController extends Controller
{
    //
    protected $driverCommissionService;

    public function __construct(DriverCommissionService $service)
    {
        $this->driverCommissionService = $service;
    }

    // GET /api/driver-commissions
    public function index()
    {
        $commissions = $this->driverCommissionService->listCommissions();
        return response()->json(['data' => $commissions], 200);
    }

    // GET /api/driver-commissions/{id}
    public function show($id)
    {
        $commission = $this->driverCommissionService->getCommission($id);
        if (!$commission) {
            return response()->json(['message' => 'Commission not found'], 404);
        }
        return response()->json(['data' => $commission], 200);
    }

    // GET /api/driver-commissions/driver/{driver_id}
    public function byDriver($driverId)
    {
        $commissions = $this->driverCommissionService->listByDriver($driverId);
        return response()->json(['data' => $commissions], 200);
    }

    // POST /api/driver-commissions
    public function store(Request $request)
    {
        try {
            $commission = $this->driverCommissionService->createCommission($request->all());
            return response()->json(['data' => $commission], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/driver-commissions/{id}
    public function update(Request $request, $id)
    {
        try {
            $commission = $this->driverCommissionService->updateCommission($id, $request->all());
            return response()->json(['data' => $commission], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // DELETE /api/driver-commissions/{id}
    public function destroy($id)
    {
        $deleted = $this->driverCommissionService->deleteCommission($id);
        if ($deleted) {
            return response()->json(['message' => 'Commission deleted successfully'], 200);
        }
        return response()->json(['message' => 'Commission not found'], 404);
    }
}
