<?php

namespace App\Http\Services;

use App\Http\Repositories\GoodsRideRepository;
use App\Http\Repositories\PassengerRideRepository;
use InvalidArgumentException;

class RideService{

    protected $passengerRideRepository;
    protected $goodsRideRepository;

    public function __construct(
        PassengerRideRepository $passengerRepo,
        GoodsRideRepository $goodrepo
    ) {
        $this->passengerRideRepository = $passengerRepo;
        $this->goodsRideRepository = $goodrepo;
    }

    public function listAllRides() {
            $passengerRides = $this->passengerRideRepository->getAll();
            $goodsRides = $this->goodsRideRepository->getAll();

            return [
                'passenger_rides' => $passengerRides,
                'goods_rides' => $goodsRides,
            ];
    }

    public function getDetailRide(string $type, $id){
        if($type === 'passenger'){
            return $this->passengerRideRepository->findById($id);
        } else if ($type === 'goods'){
            return $this->goodsRideRepository->findById($id);
        }

        throw new \InvalidArgumentException('Tipe ride tidak valid');
    }

    public function listByDriver($driverId){
        return[
            'passenger_rides' => $this->passengerRideRepository->getByDriver($driverId),
            'goods_rides' => $this->goodsRideRepository->getByDriver($driverId)
        ];
    }

    public function listByStatus($status){
        return[
            'pasengger_rides' => $this->passengerRideRepository->getByStatus($status),
            'goods_rides' => $this->goodsRideRepository->getByStatus($status)
        ];
    }
}
