<?php

namespace App\Http\Controllers;

use App\Http\Services\AdminOrdersService;
use Illuminate\Http\Request;

class AdminOrdersController extends Controller
{
    protected $ordersService;

    public function __construct(AdminOrdersService $service){
        $this->ordersService = $service;
    }
    /**
     * Display a listing of the resource.
     */
    public function index(Request $request)
    {
        // $orders = $this->ordersService->listAllBookings();
        // return response()->json(['data' => $orders], 200);

        $perPage = $request->input('perPage', 10);
        $filters = [
            'status' => $request->query('status'),
            'search' => $request->query('search'),
        ];

        $data = $this->ordersService->listAllBookings($perPage, $filters);

        return response()->json([
            'success' => true,
            'data' => $data->items(),
            'meta' => [
            'current_page' => $data->currentPage(),
            'last_page' => $data->lastPage(),
            'per_page' => $data->perPage(),
            'total' => $data->total(),
            ],
            'links' => [
                'next_page_url' => $data->nextPageUrl(),
                'prev_page_url' => $data->previousPageUrl(),
                'first' => $data->url(1),
                'last' => $data->url($data->lastPage()),
            ],

    ]);
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
            $booking = $this->ordersService->getBookingDetail($type, $id);
            return response()->json(['data' => $booking], 200);
        } catch (\Throwable $th) {
            return response()->json([
                'success' => false,
                'message' => $th->getMessage()
            ]);
        }

    }

    public function getByCode($type, $code){
        try {
            $booking = $this->ordersService->getBookingDetail($type, $code);
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
