<?php

namespace App\Http\Repositories;

use App\Models\Rating;

class RatingRepository
{
    protected $model;

    public function __construct(Rating $rating)
    {
        $this->model = $rating;
    }

    // Ambil semua rating beserta driver & customer
    public function getAll()
    {
        return $this->model
            ->with(['driver', 'customer'])
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Ambil rating berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with(['driver', 'customer'])
            ->find($id);
    }

    // Ambil semua rating untuk satu driver
    public function getByDriverId($driverId)
    {
        return $this->model
            ->where('driver_id', $driverId)
            ->with('customer')
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Tambah rating baru
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update rating
    public function update($id, array $data)
    {
        $rating = $this->model->findOrFail($id);
        $rating->update($data);
        return $rating;
    }

    // Hapus rating
    public function delete($id)
    {
        $rating = $this->model->findOrFail($id);
        return $rating->delete();
    }
}
