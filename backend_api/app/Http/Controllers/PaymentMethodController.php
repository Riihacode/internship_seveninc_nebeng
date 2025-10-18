<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\PaymentMethodService;
use Illuminate\Validation\ValidationException;

class PaymentMethodController extends Controller
{
    //
    protected $paymentMethodService;

    public function __construct(PaymentMethodService $service)
    {
        $this->paymentMethodService = $service;
    }

    // GET /api/payment-methods
    public function index()
    {
        $methods = $this->paymentMethodService->listMethods();
        return response()->json(['data' => $methods], 200);
    }

    // GET /api/payment-methods/{id}
    public function show($id)
    {
        $method = $this->paymentMethodService->getMethod($id);
        if (!$method) {
            return response()->json(['message' => 'Payment method not found'], 404);
        }
        return response()->json(['data' => $method], 200);
    }

    // POST /api/payment-methods
    public function store(Request $request)
    {
        try {
            $method = $this->paymentMethodService->createMethod($request->all());
            return response()->json(['data' => $method], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // PUT /api/payment-methods/{id}
    public function update(Request $request, $id)
    {
        try {
            $method = $this->paymentMethodService->updateMethod($id, $request->all());
            return response()->json(['data' => $method], 200);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    // DELETE /api/payment-methods/{id}
    public function destroy($id)
    {
        $deleted = $this->paymentMethodService->deleteMethod($id);
        if ($deleted) {
            return response()->json(['message' => 'Payment method deleted successfully'], 200);
        }
        return response()->json(['message' => 'Payment method not found'], 404);
    }
}
