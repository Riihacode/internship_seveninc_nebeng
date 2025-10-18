<?php

namespace App\Http\Services;

// use Dotenv\Exception\ValidationException;
// use TerminalRepository;
use Illuminate\Support\Facades\Validator;
use App\Http\Repositories\TerminalRepository;
use Illuminate\Validation\ValidationException;

class TerminalService {
    protected $terminalRepository;

    public function __construct(TerminalRepository $terminalRepository) {
        $this->terminalRepository = $terminalRepository;
    }

    public function listTerminals() {
        return $this->terminalRepository->getAll();
    }

    public function getTerminal($id) {
        $terminal = $this->terminalRepository->findById($id);

        if(!$terminal) {
            throw new \Exception('Terminal not found');
        }

        return $terminal;
    }

    public function createTerminal(array $data) {
        $validator = Validator::make($data, [
            'name'                      => 'required|string|max:255',
            'terminal_type'             => 'required|string',
            'public_terminal_subtype'   => 'nullable|string',
            'province_id'               => 'required|exists:provinces,id',
            'regency_id'                => 'required|exists:regencies,id',
            'district_id'               => 'required|exists:districts,id',
            'full_address'              => 'required|string',
            'longitude'                 => 'nullable|string',
            'latitude'                  => 'nullable|string',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return  $this->terminalRepository->create($data);
    }


    public function updateTerminal($id, array $data) {
        $validator = Validator::make($data, [
            'name'                      => 'sometimes|required|string|max:255',
            'terminal_type'             => 'required|string',
            'public_terminal_subtype'   => 'nullable|string',
            'povince_id'                => 'required|exists:provinces,id',
            'regency_id'                => 'required|exists:regencies,id',
            'district_id'               => 'required|exists:districts,id',
            'full_address'              => 'required|string',
            'longitude'                 => 'nullable|string',
            'latitude'                  => 'nullable|string',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->terminalRepository->update($id, $data);
    }

    public function deleteTerminal($id) {
        return $this->terminalRepository->delete($id);
    }
}
