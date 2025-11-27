<?php

namespace App\Http\Repositories;

use App\Models\PassengerRideBooking;

class PasssengerRefundRepository{

    protected $model;

    public function __construct(PassengerRideBooking $booking)
    {
        $this->model = $booking;
    }

    public function getRejected(){
        return $this->model
            ->where('status','ditolak')
            ->with('passengerRide.driver', 'customer', 'passengerTransaction')
            ->orderBy('created_at', 'DESC')
            ->get();
    }
}
