<?php

namespace App\Http\Controllers;

use App\Http\Services\RefundService;
use Illuminate\Http\Request;

class RefundController extends Controller
{
    protected $service;

    public function __construct(RefundService $service)
    {
        $this->service = $service;
    }

    public function index(Request $request)
    {
        $perPage = $request->input('perPage', 10);
        $filters = [
            'status' => $request->query('status'),
            'search' => $request->query('search'),
        ];

        $data = $this->service->listAllRefund($perPage, $filters);

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

    public function show($type, $id){
        return response()->json([
            'success' => true,
            'data' => $this->service->detailRefund($type, $id)
        ]);
    }
}
