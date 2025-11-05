<?php

namespace App\Http\Controllers;

use App\Http\Services\TransactionService;
use Illuminate\Http\Request;

class TransactionController extends Controller
{
    protected $TransactionService;

    public function __construct(TransactionService $service){
        $this->TransactionService = $service;
    }
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $transaction = $this->TransactionService->listTransaction();
        return response()->json(['data' => $transaction], 200);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show($type, $id)
    {
        try {
            $transaction = $this->TransactionService->getTransaction($type, $id);
            return response()->json(['data' => $transaction], 200);
        } catch (\Throwable $th) {
            return response()->json([
                'success' => false,
                'message' => $th->getMessage()
            ]);
        }
    }

    public function getByBooking($type, $bookingId){
        try {
            $booking = $this->TransactionService->getByBooking($type, $bookingId);
            return response()->json(['data' => $booking], 200);
        } catch (\Throwable $th) {
            return response()->json([
                'success' => false,
                'message' => $th->getMessage()
            ]);
        }
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        //
    }
}
