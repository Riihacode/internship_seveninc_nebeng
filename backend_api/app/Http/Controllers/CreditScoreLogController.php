<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\CreditScoreLogService;
use Illuminate\Validation\ValidationException;

class CreditScoreLogController extends Controller
{
    //
    protected $creditScoreLogService;

    public function __construct(CreditScoreLogService $service)
    {
        $this->creditScoreLogService = $service;
    }

    // GET /api/credit-score-logs
    public function index()
    {
        $logs = $this->creditScoreLogService->listLogs();
        return response()->json(['data' => $logs], 200);
    }

    // GET /api/credit-score-logs/{id}
    public function show($id)
    {
        $log = $this->creditScoreLogService->getLog($id);
        if (!$log) {
            return response()->json(['message' => 'Credit score log not found'], 404);
        }
        return response()->json(['data' => $log], 200);
    }

    // GET /api/credit-score-logs/driver/{driver_id}
    public function byDriver($driverId)
    {
        $logs = $this->creditScoreLogService->listByDriver($driverId);
        return response()->json(['data' => $logs], 200);
    }

    // POST /api/credit-score-logs
    public function store(Request $request)
    {
        try {
            $log = $this->creditScoreLogService->createLog($request->all());
            return response()->json(['data' => $log], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/credit-score-logs/{id}
    public function update(Request $request, $id)
    {
        try {
            $existing = $this->creditScoreLogService->getLog($id);
            if (!$existing) {
                return response()->json(['message' => 'Credit score log not found'], 404);
            }

            $updated = $this->creditScoreLogService->updateLog($id, $request->all());

            return response()->json([
                'message' => 'Credit score log updated successfully',
                'data' => $updated,
            ], 200);

        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        } catch (\Exception $e) {
            return response()->json([
                'message' => 'Terjadi kesalahan saat mengupdate credit score log',
                'error' => $e->getMessage(),
            ], 500);
        }
    }

    // DELETE /api/credit-score-logs/{id}
    public function destroy($id)
    {
        $deleted = $this->creditScoreLogService->deleteLog($id);
        if ($deleted) {
            return response()->json(['message' => 'Credit score log deleted successfully'], 200);
        }
        return response()->json(['message' => 'Credit score log not found'], 404);
    }
}
