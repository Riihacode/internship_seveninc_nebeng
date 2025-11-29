<?php

namespace App\Http\Repositories;

use App\Models\PassengerRideBooking;

class PasssengerRefundRepository{

    protected $model;

    public function __construct(PassengerRideBooking $booking)
    {
        $this->model = $booking;
    }

    public function getRejected($filters = []){
        $query = $this->model
            ->where('status','ditolak')
            ->with('passengerRide.driver', 'customer', 'passengerTransaction')
            ->orderBy('created_at', 'DESC');

        if(!empty($filters['search'])){
            $search = $filters['search'];

            $query->where(function($q) use ($search) {
                $q->where('booking_code', 'LIKE', "%$search%")
                  ->orWhereHas('customer', function ($qc) use ($search){
                    $qc->where('full_name', 'LIKE', "%$search%");
                  })
                  ->orWhereHas('passengerRide.driver', function ($qd) use ($search){
                    $qd->where('full_name', 'LIKE', "%$search%");
                  })
                  ->orWhereHas('passengerTransaction', function ($qd) use ($search){
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
