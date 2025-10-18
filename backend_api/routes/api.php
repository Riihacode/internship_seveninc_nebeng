<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\DistrictController;
use App\Http\Controllers\ProvinceController;
use App\Http\Controllers\RegencyController;
use App\Http\Controllers\TerminalController;

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
