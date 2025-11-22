<?php

namespace App\Http\Repositories;

use App\Models\GoodsRideBooking;

class GoodsRideBookingRepository
{
    protected $model;

    public function __construct(GoodsRideBooking $goodsRideBooking)
    {
        $this->model = $goodsRideBooking;
    }

    // Ambil semua booking beserta relasi
    public function getAll($filters = [])
    {
        $query = $this->model
            ->with(['goodsRide.driver', 'customer', 'goodsTransaction'])
            ->orderBy('created_at', 'DESC');

        // search
        if(!empty($filters['search'])){
            $search = $filters['search'];

            $query->where(function($q) use ($search) {
                $q->where('booking_code', 'LIKE', "%$search%")
                  ->orWhereHas('customer', function ($qc) use ($search){
                    $qc->where('full_name', 'LIKE', "%$search%");
                  })
                  ->orWhereHas('driver', function ($qd) use ($search){
                    $qd->where('full_name', 'LIKE', "%$search%");
                  });
            });
        }

        // status
        if(!empty($filters['status'])){
            $query->where('status', $filters['status']);
        }

        return $query->get();
    }


    public function countByDate($date){
        return $this->model->whereDate('created_at', $date)->count();
    }

    // Ambil booking berdasarkan ID
    public function findById($id)
    {
        return $this->model
            ->with(['goodsRide.driver', 'customer', 'goodsTransaction'])
            ->find($id);
    }

    // Ambil booking berdasarkan driver_id
    public function getByDriver($driverId)
    {
        return $this->model
            ->whereHas('goodsRide', fn($q) => $q->where('driver_id', $driverId))
            ->with(['goodsRide', 'customer'])
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Filter berdasarkan status
    public function getByStatus($status)
    {
        return $this->model
            ->where('status', $status)
            ->with(['goodsRide', 'customer'])
            ->orderBy('created_at', 'DESC')
            ->get();
    }

    // Tambah booking
    public function create(array $data)
    {
        return $this->model->create($data);
    }

    // Update booking
    public function update($id, array $data)
    {
        $booking = $this->model->findOrFail($id);
        $booking->update($data);
        return $booking;
    }

    public function getByCode(string $code){
        return $this->model->where('booking_code', $code)->firstOrFail();
    }

    // Hapus booking
    public function delete($id)
    {
        $booking = $this->model->findOrFail($id);
        return $booking->delete();
    }

    public function paginate($perPage = 10, $filters = []){
        $query = $this->model
            ->with(['goodsRide.driver', 'customer', 'goodsTransaction'])
            ->orderBy('created_at', 'DESC');

            // filter
            if(!empty($filters['status'])) {
                $query->where('payment_status', $filters['status']);
            }

            return $query->paginate($perPage);
    }
}
