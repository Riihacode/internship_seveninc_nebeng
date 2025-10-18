<?php

namespace App\Http\Controllers;

use App\Http\Services\DistrictService;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class DistrictController extends Controller
{
    //
    protected $districtService;

    public function __construct(DistrictService $districtService) {
        $this->districtService = $districtService;
    }

    public function index() {
        $data = $this->districtService->listDistricts();
        return response()->json(['data' => $data], 200);
    }

    public function show($id) {
        try {
            $district = $this->districtService->getDistrict($id);
            return response()->json(['message' => $district], 200);
        } catch (ModelNotFoundException $e) {
            return response()->json(['message' => 'District not found'], 404);
        }
    }

    public function getByRegency($regencyId) {

        $data = $this->districtService->listByRegency($regencyId);
        return response()->json(['data' => $data], 200);
    }
}
