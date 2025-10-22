<?php

namespace App\Http\Controllers;

use App\Http\Services\AdministratorService;
use Exception;
use Illuminate\Http\Request;
use Illuminate\Validation\ValidationException;

class AdministratorController extends Controller
{
    protected $administratorService;

    public function __construct(AdministratorService $service){
        $this->administratorService = $service;
    }
    /**
     * Display a listing of the resource.
     */
    public function index(Request $request)
    {
        try {
            if($request->has('user_id')){
                $administrator = $this->administratorService->getAdministratorByUserId($request->query('user_id'));
                return response()->json([
                    'success' => true,
                    'message' => 'Get administrator by user ID successfully',
                    'data' => $administrator
                ], 200);
            } else {
                $administrator = $this->administratorService->listAdministrator();
                return response()->json([
                    'success' => true,
                    'message' => 'Get data successfully',
                    'data' => $administrator
                ], 200);
            }
        } catch (ValidationException $th) {
            return response()->json([
                'success' => false,
                'message' => 'Get data failed',
                'error' => $th->getMessage()
            ]);
        }

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
        try {
            $administrator = $this->administratorService->createAdministrator($request->all());
            return response()->json([
                'success' => true,
                'message' => 'Success Add Administrator',
                'data' => $administrator
            ], 201);
        } catch (ValidationException $th) {
            return response()->json([
                'success' => false,
                'message' => 'Failed to add administrator - validation error',
                'error' => $th->errors()
            ], 422);
        } catch (\Exception $e) {
            return response()->json([
                'success' => false,
                'message' => 'Something went wrong while adding administrator',
                'error' => $e->getMessage()
            ], 422);
        }
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        try {
            $administrator = $this->administratorService->getAdministrator($id);
            return response()->json([
                'success' => 'true',
                'message' => 'success',
                'data' => $administrator
            ]);
        } catch (\Throwable $th) {
            return response()->json([
                'success' => false,
                'message' => 'Something went wrong',
                'error' => $th->getMessage()
            ], 500);

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
        try {
            $administrator = $this->administratorService->updateAdministrator($id,$request->all());
            return response()->json([
                'success' => 'true',
                'message' => 'Data Updated Successfully',
                'data' => $administrator
            ]);
        } catch (\Throwable $th) {
            return response()->json([
                'success' => false,
                'message' => 'Something went wrong',
                'error' => $th->getMessage()
            ], 500);
        }
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        try {
            $administrator = $this->administratorService->deleteAdministrator($id);
            return response()->json([
                'success' => true,
                'message' => 'Data deleted',
                'data' => $administrator
            ]);
        } catch (\Throwable $th) {
            return response()->json([
                'success' => false,
                'message' => 'Something went wrong',
                'error' => $th->getMessage()
            ], 500);
        }
    }

    public function getByUserId($userId){
        try {
            $administrator = $this->administratorService->getAdministratorByUserId($userId);
            return response()->json([
                'success' => 'true',
                'message' => 'success',
                'data' => $administrator
            ]);
        } catch (\Throwable $th) {
            return response()->json([
                'success' => false,
                'message' => 'Something went wrong',
                'error' => $th->getMessage()
            ], 500);
        }
    }
}
