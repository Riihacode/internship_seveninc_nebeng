<?php

namespace App\Http\Controllers;

use App\Http\Services\RegencyService;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class RegencyController extends Controller
{
    //
    protected $regencyService;

    public function __construct(RegencyService $regencyService) {
        $this->regencyService = $regencyService;
    }

    // GET /api/regencies
    public function index() {
        $data = $this->regencyService->listRegencies();
        return response()->json(['data' => $data], 200);
    }

    // GET /api/regencies/{id}
    public function show($id) {
        try {
            $data = $this->regencyService->getRegency($id);
            return response()->json(['data' => $data], 200);
        } catch (ModelNotFoundException $e) {
            return response()->json(['message' => $e], 404);
        }
    }

    // GET /api/provinces/{province_id}/regencies
    public function getByProvince($provinceId) {
        $data = $this->regencyService->getRegency($provinceId);
        return response()->json(['data' => $data], 200);
    }
}
