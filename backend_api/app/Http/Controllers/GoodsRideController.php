<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Repositories\GoodsRideService;
use Illuminate\Validation\ValidationException;

class GoodsRideController extends Controller
{
    //
    protected $goodsRideService;

    public function __construct(GoodsRideService $goodsRideService) {
        $this->goodsRideService = $goodsRideService;
    }


    // GET /api/goods-rides
    public function index()
    {
        $rides = $this->goodsRideService->listGoodsRides();
        return response()->json(['data' => $rides], 200);
    }

    // GET /api/goods-rides/{id}
    public function show($id)
    {
        $ride = $this->goodsRideService->getGoodsRide($id);
        if (!$ride) {
            return response()->json(['message' => 'Goods ride not found'], 404);
        }
        return response()->json(['data' => $ride], 200);
    }

    // POST /api/goods-rides
    public function store(Request $request)
    {
        try {
            $ride = $this->goodsRideService->createGoodsRide($request->all());
            return response()->json(['data' => $ride], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/goods-rides/{id}
    public function update(Request $request, $id)
    {
        try {
            $ride = $this->goodsRideService->updateGoodsRide($id, $request->all());
            return response()->json(['data' => $ride], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // DELETE /api/goods-rides/{id}
    public function destroy($id)
    {
        $deleted = $this->goodsRideService->deleteGoodsRide($id);

        if ($deleted) {
            return response()->json(['message' => 'Goods ride deleted successfully'], 200);
        }

        return response()->json(['message' => 'Goods ride not found'], 404);
    }
}
