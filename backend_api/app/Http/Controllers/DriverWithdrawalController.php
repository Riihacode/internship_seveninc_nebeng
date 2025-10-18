<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\DriverWithdrawalService;
use Illuminate\Validation\ValidationException;

class DriverWithdrawalController extends Controller
{
    //
    protected $driverWithdrawalService;

    public function __construct(DriverWithdrawalService $service)
    {
        $this->driverWithdrawalService = $service;
    }

    // GET /api/driver-withdrawals
    public function index()
    {
        $withdrawals = $this->driverWithdrawalService->listWithdrawals();
        return response()->json(['data' => $withdrawals], 200);
    }

    // GET /api/driver-withdrawals/{id}
    public function show($id)
    {
        $withdrawal = $this->driverWithdrawalService->getWithdrawal($id);
        if (!$withdrawal) {
            return response()->json(['message' => 'Withdrawal not found'], 404);
        }
        return response()->json(['data' => $withdrawal], 200);
    }

    // GET /api/driver-withdrawals/driver/{driver_id}
    public function byDriver($driverId)
    {
        $withdrawals = $this->driverWithdrawalService->listByDriver($driverId);
        return response()->json(['data' => $withdrawals], 200);
    }

    // GET /api/driver-withdrawals/status/{status}
    public function byStatus($status)
    {
        $withdrawals = $this->driverWithdrawalService->listByStatus($status);
        return response()->json(['data' => $withdrawals], 200);
    }

    // POST /api/driver-withdrawals
    public function store(Request $request)
    {
        try {
            $withdrawal = $this->driverWithdrawalService->createWithdrawal($request->all());
            return response()->json(['data' => $withdrawal], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/driver-withdrawals/{id}
    public function update(Request $request, $id)
    {
        try {
            $withdrawal = $this->driverWithdrawalService->updateWithdrawal($id, $request->all());
            return response()->json(['data' => $withdrawal], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // DELETE /api/driver-withdrawals/{id}
    public function destroy($id)
    {
        $deleted = $this->driverWithdrawalService->deleteWithdrawal($id);
        if ($deleted) {
            return response()->json(['message' => 'Withdrawal deleted successfully'], 200);
        }
        return response()->json(['message' => 'Withdrawal not found'], 404);
    }
}
