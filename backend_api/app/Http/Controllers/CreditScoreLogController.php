<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;
use App\Http\Repositories\CreditScoreLogRepository;

class CreditScoreLogController extends Controller
{
    //
    protected $creditScoreLogRepository;

    public function __construct(CreditScoreLogRepository $repo)
    {
        $this->creditScoreLogRepository = $repo;
    }

    // Ambil semua log
    public function listLogs()
    {
        return $this->creditScoreLogRepository->getAll();
    }

    // Ambil log berdasarkan ID
    public function getLog($id)
    {
        return $this->creditScoreLogRepository->findById($id);
    }

    // Ambil log berdasarkan driver
    public function listByDriver($driverId)
    {
        return $this->creditScoreLogRepository->getByDriverId($driverId);
    }

    // Tambah log baru
    public function createLog(array $data)
    {
        $validator = Validator::make($data, [
            'driver_id' => 'required|exists:drivers,id',
            'action_type' => 'required|string|max:255',
            'score_change' => 'required|integer',
            'notes' => 'nullable|string|max:500',
            'created_at' => 'nullable|date',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->creditScoreLogRepository->create($data);
    }

    // Hapus log
    public function deleteLog($id)
    {
        return $this->creditScoreLogRepository->delete($id);
    }
}
