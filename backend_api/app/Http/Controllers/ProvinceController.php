<?php

namespace App\Http\Controllers;

use App\Http\Services\ProvinceService;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class ProvinceController extends Controller
{
    //
    protected $provinceService;

    public function __construct(ProvinceService $provinceService) {
        $this->provinceService = $provinceService;
    }

    // GET /api/provinces
    public function index() {
        $data = $this->provinceService->listProvinces();
        return response()->json(['data' => $data], 200);
    }

    //  GET /api/provinces/{id}
    public function show($id) {
        try {
            $province = $this->provinceService->getProvince($id);
            return response()->json(['data' => $province], 200);
        } catch (ModelNotFoundException $e) {
            return response()->json(['message' => 'Province not found'], 404);
        }
    }
}
