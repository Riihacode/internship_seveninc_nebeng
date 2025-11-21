<?php

namespace App\Http\Controllers;

use App\Http\Resources\CustomerAdminResource;
use Illuminate\Http\Request;
use App\Http\Services\CustomerServiceAdmin;
use Illuminate\Validation\ValidationException;

class CustomerAdminController extends Controller
{
    //
    protected $customerService;

    public function __construct(CustomerServiceAdmin $service)
    {
        $this->customerService = $service;
    }

    // GET /api/customers
    public function index(Request $request)
    {

        $perPage = $request->query('perPage', 10);
        $filters = [
            'status' => $request->query('status'),
            'search' => $request->query('search'),
        ];

        $customers = $this->customerService->listCustomers($perPage, $filters);
        $data = CustomerAdminResource::collection($customers);
        return response()->json([
            'success' => true,
            'data' => $data->items(),
            'meta' => [
            'current_page' => $customers->currentPage(),
            'last_page' => $customers->lastPage(),
            'per_page' => $customers->perPage(),
            'total' => $customers->total(),
            ],
            'links' => [
                'next_page_url' => $customers->nextPageUrl(),
                'prev_page_url' => $customers->previousPageUrl(),
                'first' => $customers->url(1),
                'last' => $customers->url($customers->lastPage()),
            ],
        ]);
    }

    // GET /api/customers/{id}
    public function show($id)
    {
        $customer = $this->customerService->getCustomer($id);
        if (!$customer) {
            return response()->json(['message' => 'Customer not found'], 404);
        }
        return response()->json(['data' => $customer], 200);
    }

    // GET /api/customers/user/{user_id}
    public function byUser($userId)
    {
        $customer = $this->customerService->getCustomerByUser($userId);
        if (!$customer) {
            return response()->json(['message' => 'Customer not found'], 404);
        }
        return response()->json(['data' => $customer], 200);
    }

    // POST /api/customers
    public function store(Request $request)
    {
        try {
            $customer = $this->customerService->createCustomer($request->all());
            return response()->json(['data' => $customer], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/customers/{id}
    public function update(Request $request, $id)
    {
        try {
            $customer = $this->customerService->updateCustomer($id, $request->all());
            return response()->json(['data' => $customer], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PATCH /api/customers/{id}/verify
    public function verify(Request $request,$id)
    {
        $request->validate([
            'status' => 'required',
        ]);

        try {
            $status = $request->input('status');
            $customer = $this->customerService->verifyCustomer($id,$status);
            return response()->json([
                'message' => 'Status Changed', 'data' => $customer
            ], 200);
        } catch (\Throwable $th) {
            return response()->json(['errors' => $th->getMessage()], 422);
        }

    }

    // PATCH /api/customers/{id}/add-credit
    public function addCredit(Request $request, $id)
    {
        $request->validate(['amount' => 'required|integer|min:1000']);
        $customer = $this->customerService->addCredit($id, $request->amount);
        return response()->json(['message' => 'Credit added successfully', 'data' => $customer], 200);
    }

    // PATCH /api/customers/{id}/deduct-credit
    public function deductCredit(Request $request, $id)
    {
        $request->validate(['amount' => 'required|integer|min:1000']);
        $customer = $this->customerService->deductCredit($id, $request->amount);
        return response()->json(['message' => 'Credit deducted successfully', 'data' => $customer], 200);
    }

    // DELETE /api/customers/{id}
    public function destroy($id)
    {
        $deleted = $this->customerService->deleteCustomer($id);
        if ($deleted) {
            return response()->json(['message' => 'Customer deleted successfully'], 200);
        }
        return response()->json(['message' => 'Customer not found'], 404);
    }
}
