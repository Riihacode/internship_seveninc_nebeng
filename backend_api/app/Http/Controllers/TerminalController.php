<?php

namespace App\Http\Controllers;

// use TerminalService;
// use TerminalService;
use Illuminate\Http\Request;
use App\Http\Services\TerminalService;
use Illuminate\Validation\ValidationException;

class TerminalController extends Controller
{
    //
    protected $terminalService;

    public function __construct(TerminalService $terminalService) {
        $this->terminalService = $terminalService;
    }

    public function index() {
        $terminals = $this->terminalService->listTerminals();
        return response()->json(['data' => $terminals], 200);
    }

    public function show($id) {
        try {
            $terminal = $this->terminalService->getTerminal($id);
            return response()->json(['data' => $terminal], 200);
        } catch (\Exception $e) {
            return response()->json(['message' => $e->getMessage()], 404);
        }
    }

    public function store(Request $request) {
        try {
            $terminal = $this->terminalService->createTerminal($request->all());
            return response()->json(['data' =>$terminal], 201);
        } catch (ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    public function update(Request $request, $id) {
        try {
            $terminal = $this->terminalService->updateTerminal($id, $request->all());
            return response()->json(['data' => $terminal], 200);
        } catch(ValidationException $e) {
            return response()->json(['errors' => $e->errors()], 422);
        }
    }

    public function destroy($id) {
        $deleted = $this->terminalService->deleteTerminal($id);
        if ($deleted) {
            return response()->json(['message' => 'Terminal deleted successfully'], 200);
        } else {
            return response()->json(['message' => 'Terminal not found'], 404);
        }
    }
}
