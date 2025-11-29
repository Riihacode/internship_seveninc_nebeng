<?php

namespace App\Http\Repositories;

use App\Models\GoodsRideBooking;

class GoodsRefundRepository{

    protected $model;

    public function __construct(GoodsRideBooking $booking)
    {
        $this->model = $booking;
    }

     public function getRejected($filters = []){
        $query = $this->model
            ->where('status','ditolak')
            ->with('goodsRide.driver', 'customer', 'goodsTransaction')
            ->orderBy('created_at', 'DESC');

        if(!empty($filters['search'])){
            $search = $filters['search'];

            $query->where(function($q) use ($search) {
                $q->where('booking_code', 'LIKE', "%$search%")
                  ->orWhereHas('customer', function ($qc) use ($search){
                    $qc->where('full_name', 'LIKE', "%$search%");
                  })
                  ->orWhereHas('goodsRide.driver', function ($qd) use ($search){
                    $qd->where('full_name', 'LIKE', "%$search%");
                  })
                  ->orWhereHas('goodsTransaction', function ($qd) use ($search){
                    $qd->where('transaction_code', 'LIKE', "%$search%");
                  });
            });
        }

        // status
        if(!empty($filters['status'])){
            $query->where('reject_status', $filters['status']);
        }

        return $query->get();
    }
}
