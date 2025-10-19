<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\GoodsRideBookingService;
use Illuminate\Validation\ValidationException;
// use League\Config\Exception\ValidationException;

class GoodsRideBookingController extends Controller
{
    //
     protected $goodsRideBookingService;

    public function __construct(GoodsRideBookingService $service)
    {
        $this->goodsRideBookingService = $service;
    }

    // GET /api/goods-ride-bookings
    public function index()
    {
        $bookings = $this->goodsRideBookingService->listBookings();
        return response()->json(['data' => $bookings], 200);
    }

    // GET /api/goods-ride-bookings/{id}
    public function show($id)
    {
        $booking = $this->goodsRideBookingService->getBooking($id);
        if (!$booking) {
            return response()->json(['message' => 'Booking not found'], 404);
        }
        return response()->json(['data' => $booking], 200);
    }

    // GET /api/goods-ride-bookings/driver/{driver_id}
    public function byDriver($driverId)
    {
        $bookings = $this->goodsRideBookingService->listByDriver($driverId);
        return response()->json(['data' => $bookings], 200);
    }

    // GET /api/goods-ride-bookings/status/{status}
    public function byStatus($status)
    {
        $bookings = $this->goodsRideBookingService->listByStatus($status);
        return response()->json(['data' => $bookings], 200);
    }

    // POST /api/goods-ride-bookings
    public function store(Request $request)
    {
        try {
            $booking = $this->goodsRideBookingService->createBooking($request->all());
            return response()->json(['data' => $booking], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/goods-ride-bookings/{id}
    public function update(Request $request, $id)
    {
        try {
            $booking = $this->goodsRideBookingService->updateBooking($id, $request->all());
            return response()->json(['data' => $booking], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // DELETE /api/goods-ride-bookings/{id}
    public function destroy($id)
    {
        $deleted = $this->goodsRideBookingService->deleteBooking($id);
        if ($deleted) {
            return response()->json(['message' => 'Booking deleted successfully'], 200);
        }
        return response()->json(['message' => 'Booking not found'], 404);
    }
}
