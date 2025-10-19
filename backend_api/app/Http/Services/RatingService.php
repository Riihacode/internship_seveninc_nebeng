<?php

namespace App\Http\Services;

use App\Http\Repositories\RatingRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

class RatingService
{
    protected $ratingRepository;

    public function __construct(RatingRepository $ratingRepository)
    {
        $this->ratingRepository = $ratingRepository;
    }

    // List semua rating
    public function listRatings()
    {
        return $this->ratingRepository->getAll();
    }

    // Get rating by ID
    public function getRating($id)
    {
        return $this->ratingRepository->findById($id);
    }

    // Get rating berdasarkan driver
    public function getRatingsByDriver($driverId)
    {
        return $this->ratingRepository->getByDriverId($driverId);
    }

    // Tambah rating baru
    public function createRating(array $data)
    {
        $validator = Validator::make($data, [
            'driver_id' => 'required|exists:drivers,id',
            'customer_id' => 'required|exists:customers,id',
            'rating' => 'required|integer|min:1|max:5',
            'feedback' => 'nullable|string|max:500',
            'created_at' => 'nullable|date',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->ratingRepository->create($data);
    }

    // Update rating
    public function updateRating($id, array $data)
    {
        $validator = Validator::make($data, [
            'rating' => 'sometimes|required|integer|min:1|max:5',
            'feedback' => 'nullable|string|max:500',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->ratingRepository->update($id, $data);
    }

    // Hapus rating
    public function deleteRating($id)
    {
        return $this->ratingRepository->delete($id);
    }
}
