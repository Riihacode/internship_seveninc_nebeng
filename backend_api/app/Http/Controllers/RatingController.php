<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\RatingService;
use Illuminate\Validation\ValidationException;

class RatingController extends Controller
{
    //
    protected $ratingService;

    public function __construct(RatingService $ratingService)
    {
        $this->ratingService = $ratingService;
    }

    // GET /api/ratings
    public function index()
    {
        $ratings = $this->ratingService->listRatings();
        return response()->json(['data' => $ratings], 200);
    }

    // GET /api/ratings/{id}
    public function show($id)
    {
        $rating = $this->ratingService->getRating($id);
        if (!$rating) {
            return response()->json(['message' => 'Rating not found'], 404);
        }
        return response()->json(['data' => $rating], 200);
    }

    // GET /api/ratings/driver/{driver_id}
    public function showByDriver($driverId)
    {
        $ratings = $this->ratingService->getRatingsByDriver($driverId);
        return response()->json(['data' => $ratings], 200);
    }

    // POST /api/ratings
    public function store(Request $request)
    {
        try {
            $rating = $this->ratingService->createRating($request->all());
            return response()->json(['data' => $rating], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/ratings/{id}
    public function update(Request $request, $id)
    {
        try {
            $rating = $this->ratingService->updateRating($id, $request->all());
            return response()->json(['data' => $rating], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // DELETE /api/ratings/{id}
    public function destroy($id)
    {
        $deleted = $this->ratingService->deleteRating($id);
        if ($deleted) {
            return response()->json(['message' => 'Rating deleted successfully'], 200);
        }
        return response()->json(['message' => 'Rating not found'], 404);
    }
}
