<?php

namespace App\Http\Services;

use App\Http\Repositories\GoodsRideBookingRepository;
use Illuminate\Support\Facades\Validator;
use Illuminate\Validation\ValidationException;

class GoodsRideBookingService
{
    protected $goodsRideBookingRepository;

    public function __construct(GoodsRideBookingRepository $repo)
    {
        $this->goodsRideBookingRepository = $repo;
    }

    // List semua booking
    public function listBookings()
    {
        return $this->goodsRideBookingRepository->getAll();
    }

    // Get by ID
    public function getBooking($id)
    {
        return $this->goodsRideBookingRepository->findById($id);
    }

    // Get by Driver ID
    public function listByDriver($driverId)
    {
        return $this->goodsRideBookingRepository->getByDriver($driverId);
    }

    // Filter by Status
    public function listByStatus($status)
    {
        return $this->goodsRideBookingRepository->getByStatus($status);
    }

    // Create new booking
    public function createBooking(array $data)
    {
        $validator = Validator::make($data, [
            'goods_ride_id' => 'required|exists:goods_rides,id',
            'customer_id' => 'required|exists:customers,id',
            'item_weight' => 'required|numeric|min:1',
            'item_description' => 'nullable|string|max:500',
            'item_img' => 'nullable|string|max:255',
            'total_price' => 'required|numeric|min:0',
            'status' => 'required|string|in:Pending,Diterima,Ditolak',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->goodsRideBookingRepository->create($data);
    }

    // Update booking (misalnya ubah status)
    public function updateBooking($id, array $data)
    {
        $validator = Validator::make($data, [
            'item_weight' => 'sometimes|numeric|min:1',
            'item_description' => 'nullable|string|max:500',
            'item_img' => 'nullable|string|max:255',
            'total_price' => 'sometimes|numeric|min:0',
            'status' => 'sometimes|required|string|in:Pending,Diterima,Ditolak',
        ]);

        if ($validator->fails()) {
            throw new ValidationException($validator);
        }

        return $this->goodsRideBookingRepository->update($id, $data);
    }

    // Hapus booking
    public function deleteBooking($id)
    {
        return $this->goodsRideBookingRepository->delete($id);
    }
}
