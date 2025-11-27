<?php

namespace App\Http\Repositories;

use App\Models\GoodsRideBooking;

class GoodsRefundRepository{

    protected $model;

    public function __construct(GoodsRideBooking $booking)
    {
        $this->model = $booking;
    }

    public function getRejected(){
        return $this->model
            ->where('status','ditolak')
            ->with('goodsRide.driver', 'customer', 'goodsTransaction')
            ->orderBy('created_at', 'DESC')
            ->get();
    }
}
