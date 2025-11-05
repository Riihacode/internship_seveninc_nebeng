<?php

namespace App\Http\Services;

class OrdersService{
    protected $passengersBookingService;
    protected $goodsBookingService;

    public function __construct(
        PassengerRideBookingService $passengerService,
        GoodsRideBookingService $goodsService
    ) {
        $this->passengersBookingService = $passengerService;
        $this->goodsBookingService = $goodsService;
    }

    public function listAllBookings(){

        $passengerBookings = $this->passengersBookingService->listBookings()->map(function($b){
            $b->booking_type ='Passenger';
            $b->layanan = $b->passengerRide->vehicle_type ?? null;
            $b->driver_name = $b->passengerRide->driver->full_name ?? '-';
            $b->customer_name = $b->customer->full_name ?? '-';
            $b->driver_phone = $b->passengerRide->driver->telephone ?? '-';
            $b->terminal_keberangkatan = $b->passengerRide->departureTerminal->name ?? '-';
            $b->terminal_tujuan = $b->passengerRide->arrivalTerminal->name ?? '-';
            $b->kota_tujuan = $b->passengerRide->departureTerminal->regency->name ?? '-';
            $b->kota_awal = $b->passengerRide->arrivalTerminal->regency->name ?? '-';
            $b->booking_id = $b->id;
            return $b;
        });

        $goodsBookings = $this->goodsBookingService->listBookings()->map(function($b){
            $b->booking_type ='Goods';
            $b->layanan = $b->goodsRide->transport_type ?? null;
            $b->driver_name = $b->goodsRide->driver->full_name ?? '-';
            $b->customer_name = $b->customer->full_name ?? '-';
            $b->driver_phone = $b->goodsRide->driver->telephone;
            $b->terminal_keberangkatan = $b->goodsRide->departureTerminal->name ?? '-';
            $b->terminal_tujuan = $b->goodsRide->arrivalTerminal->name?? '-';
            $b->kota_tujuan = $b->goodsRide->arrivalTerminal->regency->name ?? '-';
            $b->kota_awal = $b->goodsRide->departureTerminal->regency->name ?? '-';
            $b->booking_id = $b->id;
            return $b;
        });

        $merged = $goodsBookings->concat($passengerBookings);

        return $merged->sortByDesc('created_at')->values();
    }

    public function getBookingDetail($type, $id){
        if($type === 'Passenger'){
            return $this->passengersBookingService->getBooking($id);
        } else if ($type === 'Goods') {
            return $this->goodsBookingService->getBooking($id);
        }

        throw new \InvalidArgumentException('Invalid bookings type');
    }

    public function getDetailByCode($type, string $code)  {
        if ($type === 'Pasengger'){
            return $this->passengersBookingService->getByCode($code);
        } else if ( $type === 'Goods'){
            return $this->goodsBookingService->getByCode($code);
        }
        throw new \InvalidArgumentException('Invalid Code');
    }
}
