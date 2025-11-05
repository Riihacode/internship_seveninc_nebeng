<?php

namespace App\Http\Services;

use App\Http\Services\GoodsTransactionService;
use App\Http\Services\PassengerTransactionService;


class TransactionService {
    protected $passengerTransactionService;
    protected $goodsTransactionService;

    public function __construct(
        PassengerTransactionService $passengerService,
        GoodsTransactionService $goodsService,
    ) {
        $this->passengerTransactionService = $passengerService;
        $this->goodsTransactionService = $goodsService;
    }

    public function listTransaction(){
        $goodsTransaction = $this->goodsTransactionService->listTransactions()->map(function ($d){
            $d->transaction_type = 'Goods';
            $d->booking_id = $d->goods_ride_booking_id;
            return $d;
        });
        $passengerTransaction = $this->passengerTransactionService->listTransactions()->map(function ($d){
            $d->transaction_type = 'Passenger';
            $d->booking_id = $d->passenger_ride_booking_id;
            return $d;
        });


        $merged = $goodsTransaction->concat($passengerTransaction);

        return $merged->sortByDesc('created_at')->values();

    }

    public function getTransaction($type, $id){
        if($type === 'Passenger'){
            return $this->passengerTransactionService->getTransaction($id);
        } else if ($type === 'Goods'){
            return $this->goodsTransactionService->getTransaction($id);
        }

        throw new \InvalidArgumentException('Invalid transaction type');
    }

    public function getByBooking($type, $bookingId){
        if($type === 'Passenger'){
            return $this->passengerTransactionService->getByBooking($bookingId);
        } else if ($type === 'Goods'){
            return $this->goodsTransactionService->getByBooking($bookingId);
        }

        throw new \InvalidArgumentException('Invalid transaction type');
    }
}
