<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\DriverController;
use App\Http\Controllers\RatingController;
use App\Http\Controllers\RegencyController;
use App\Http\Controllers\VehicleController;
use App\Http\Controllers\DistrictController;
use App\Http\Controllers\ProvinceController;
use App\Http\Controllers\TerminalController;
use App\Http\Controllers\GoodsRideController;
use App\Http\Controllers\PassengerRideController;
use App\Http\Controllers\CreditScoreLogController;
use App\Http\Controllers\DriverCommissionController;
use App\Http\Controllers\DriverWithdrawalController;
use App\Http\Controllers\GoodsRideBookingController;

Route::post('/register', [AuthController::class, 'register']);
Route::post('/login', [AuthController::class, 'login']);

Route::middleware('auth:sanctum')->group(function() {
    Route::post('/logout', [AuthController::class, 'logout']);
});

// [ TERMINAL ]
Route::prefix('terminals')->group(function () {
    Route::get('/', [TerminalController::class, 'index']);
    Route::get('/{id}', [TerminalController::class, 'show']);
    Route::post('/', [TerminalController::class, 'store']);
    Route::put('/{id}', [TerminalController::class, 'update']);
    Route::delete('/{id}', [TerminalController::class, 'destroy']);
});

// [PROVINCE]
Route::prefix('provinces')->group(function () {
    Route::get('/', [ProvinceController::class, 'index']);
    Route::get('/{id}', [ProvinceController::class, 'show'] );

    // [ REGENCY BY PROVINCE ]
    Route::get('/{province_id}/regencies', [RegencyController::class, 'getByProvince']);
});

// [REGENCY]
Route::prefix('regencies')->group(function(){
    Route::get('/', [RegencyController::class, 'index']);
    Route::get('{id}', [RegencyController::class, 'show']);

    // [ DISTRICT BY REGENCY ]
    Route::get('/{regency_id}/districts', [DistrictController::class, 'getByRegency']);
});

// [DISTRICT]
Route::prefix('districts')->group(function () {
    Route::get('/', [DistrictController::class, 'index']);
    Route::get('/{id}', [DistrictController::class, 'show']);
});

// [GOODS RIDE]
Route::prefix('goods-rides')->group(function () {
    Route::get('/', [GoodsRideController::class, 'index']);
    Route::get('/{id}', [GoodsRideController::class, 'show']);
    Route::post('/', [GoodsRideController::class, 'store']);
    Route::put('/{id}', [GoodsRideController::class, 'update']);
    Route::delete('/{id}', [GoodsRideController::class, 'destroy']);
});

// [ DRIVER ]
Route::prefix('drivers')->group(function () {
    Route::get('/', [DriverController::class, 'index']);
    Route::get('/{id}', [DriverController::class, 'show']);
    Route::post('/', [DriverController::class, 'store']);
    Route::put('/{id}', [DriverController::class, 'update']);
    Route::delete('/{id}', [DriverController::class, 'destroy']);
});

// [ RATING ]
Route::prefix('ratings')->group(function () {
    Route::get('/', [RatingController::class, 'index']);
    Route::get('/{id}', [RatingController::class, 'show']);
    Route::get('/driver/{driver_id}', [RatingController::class, 'showByDriver']);
    Route::post('/', [RatingController::class, 'store']);
    Route::put('/{id}', [RatingController::class, 'update']);
    Route::delete('/{id}', [RatingController::class, 'destroy']);
});

// [ GOODS RIDE BOOKINGS ]
Route::prefix('goods-ride-bookings')->group(function () {
    Route::get('/', [GoodsRideBookingController::class, 'index']);
    Route::get('/{id}', [GoodsRideBookingController::class, 'show']);
    Route::get('/driver/{driver_id}', [GoodsRideBookingController::class, 'byDriver']);
    Route::get('/status/{status}', [GoodsRideBookingController::class, 'byStatus']);
    Route::post('/', [GoodsRideBookingController::class, 'store']);
    Route::put('/{id}', [GoodsRideBookingController::class, 'update']);
    Route::delete('/{id}', [GoodsRideBookingController::class, 'destroy']);
});

// [ PASSENGER RIDE ]
Route::prefix('passenger-rides')->group(function () {
    Route::get('/', [PassengerRideController::class, 'index']);
    Route::get('/{id}', [PassengerRideController::class, 'show']);
    Route::get('/driver/{driver_id}', [PassengerRideController::class, 'byDriver']);
    Route::get('/status/{status}', [PassengerRideController::class, 'byStatus']);
    Route::post('/', [PassengerRideController::class, 'store']);
    Route::put('/{id}', [PassengerRideController::class, 'update']);
    Route::delete('/{id}', [PassengerRideController::class, 'destroy']);
});

// [ CREDIT SCORE LOG ]
Route::prefix('credit-score-logs')->group(function () {
    Route::get('/', [CreditScoreLogController::class, 'index']);
    Route::get('/{id}', [CreditScoreLogController::class, 'show']);
    Route::get('/driver/{driver_id}', [CreditScoreLogController::class, 'byDriver']);
    Route::post('/', [CreditScoreLogController::class, 'store']);
    Route::delete('/{id}', [CreditScoreLogController::class, 'destroy']);
});

// [ DRIVER WITHDRAWALS ]
Route::prefix('driver-withdrawals')->group(function () {
    Route::get('/', [DriverWithdrawalController::class, 'index']);
    Route::get('/{id}', [DriverWithdrawalController::class, 'show']);
    Route::get('/driver/{driver_id}', [DriverWithdrawalController::class, 'byDriver']);
    Route::get('/status/{status}', [DriverWithdrawalController::class, 'byStatus']);
    Route::post('/', [DriverWithdrawalController::class, 'store']);
    Route::put('/{id}', [DriverWithdrawalController::class, 'update']);
    Route::delete('/{id}', [DriverWithdrawalController::class, 'destroy']);
});

// [ DRIVER COMMISSIONS ]
Route::prefix('driver-commissions')->group(function () {
    Route::get('/', [DriverCommissionController::class, 'index']);
    Route::get('/{id}', [DriverCommissionController::class, 'show']);
    Route::get('/driver/{driver_id}', [DriverCommissionController::class, 'byDriver']);
    Route::post('/', [DriverCommissionController::class, 'store']);
    Route::put('/{id}', [DriverCommissionController::class, 'update']);
    Route::delete('/{id}', [DriverCommissionController::class, 'destroy']);
});

// [ VEHICLES ]
Route::prefix('vehicles')->group(function () {
    Route::get('/', [VehicleController::class, 'index']);
    Route::get('/{id}', [VehicleController::class, 'show']);
    Route::get('/driver/{driver_id}', [VehicleController::class, 'byDriver']);
    Route::post('/', [VehicleController::class, 'store']);
    Route::put('/{id}', [VehicleController::class, 'update']);
    Route::patch('/{id}/verify', [VehicleController::class, 'verify']);
    Route::patch('/{id}/reject', [VehicleController::class, 'reject']);
    Route::delete('/{id}', [VehicleController::class, 'destroy']);
});
