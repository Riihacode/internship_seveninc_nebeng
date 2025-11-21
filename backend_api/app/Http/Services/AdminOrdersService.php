<?php

namespace App\Http\Services;

use App\Http\Resources\GoodsBookingResource;
use App\Http\Resources\PassengerBookingResource;
use App\Http\Resources\PassenggerBookingResource;
use Illuminate\Database\Eloquent\Builder;
use Illuminate\Pagination\LengthAwarePaginator;
use Illuminate\Support\Collection;

/**
 * @return Collection<int, \App\Models\PassengerRideBooking|\App\Models\GoodsRideBooking>
 */


class AdminOrdersService{
    protected $passengersBookingService;
    protected $goodsBookingService;

    public function __construct(
        PassengerRideBookingService $passengerService,
        GoodsRideBookingService $goodsService
    ) {
        $this->passengersBookingService = $passengerService;
        $this->goodsBookingService = $goodsService;
    }

    public function listAllBookings($perPage = 10, $filters = []){

        $passenger = $this->passengersBookingService->listBookings($filters);
        $goods = $this->goodsBookingService->listBookings($filters);

        if($passenger instanceof Builder){
            $passenger = $passenger->get();
        }
        if ($goods instanceof Builder){
            $goods = $goods->get();
        }

        // sambung ke resource
        $passengerData = PassengerBookingResource::collection($passenger)->toArray(request());
        $goodsData = GoodsBookingResource::collection($goods)->toArray(request());

        $merged = collect()
            ->merge($passengerData)
            ->merge($goodsData)
            ->sortByDesc('created_at')
            ->values();

        $page = request('page', 1);
        $total = $merged->count();
        $paginated = new LengthAwarePaginator(
            $merged->slice(($page - 1) * $perPage, $perPage)->values(),
            $total,
            $perPage,
            $page,
        [
                    'path' => request()->url(),
                    'query' => request()->query()
                 ]
        );

        return $paginated;
    }

    public function getBookingDetail($type, $id){
        if($type === 'Passenger'){
            $data = $this->passengersBookingService->getBooking($id);
            if(!$data){
                throw new \Exception('Passenger booking not found');
            }
            return new PassengerBookingResource($data);
        } else if ($type === 'Goods') {
            $data = $this->goodsBookingService->getBooking($id);
            if (!$data){
                throw new \Exception('Goods booking not found');
            }
            return new GoodsBookingResource($data);
        }

        throw new \InvalidArgumentException('Invalid bookings type');
    }

    public function getDetailByCode($type, string $code)  {
        if ($type === 'Passenger'){
            $data = $this->passengersBookingService->getByCode($code);
            if(!$data){
                throw new \Exception('Passenger booking not found');
            }
            return new PassengerBookingResource($data);
        } else if ( $type === 'Goods'){
            $data =  $this->goodsBookingService->getByCode($code);
            if (!$data){
                throw new \Exception('Goods booking not found');
            }
            return new GoodsBookingResource($data);
        }
        throw new \InvalidArgumentException('Invalid Code');
    }
}
