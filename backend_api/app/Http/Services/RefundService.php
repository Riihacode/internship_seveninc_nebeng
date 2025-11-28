<?php

namespace App\Http\Services;

use App\Http\Repositories\GoodsRefundRepository;
use App\Http\Repositories\PasssengerRefundRepository;
use Illuminate\Pagination\LengthAwarePaginator;

class RefundService{

    protected $passengerRepo;
    protected $goodsRepo;

    public function __construct(
        PasssengerRefundRepository $passengerRepo,
        GoodsRefundRepository $goodsRepo
    )
    {
        $this->passengerRepo = $passengerRepo;
        $this->goodsRepo = $goodsRepo;
    }

    public function listAllRefund($perPage = 10, $filters = []){
        $passengerRefund = $this->passengerRepo->getRejected($filters);
        $goodsRefund = $this->goodsRepo->getRejected($filters);

        $passenger = $passengerRefund->map(function($i){
            $i->refund_type = 'passenger';
            return $i;
        });

        $goods = $goodsRefund->map(function($i){
            $i->refund_type = 'goods';
            return $i;
        });

        $merged = collect()
            ->merge($passenger)
            ->merge($goods)
            ->sortBy("created_at")
            ->values();

        // Paginate Manual
        $page = request('page', 1);
        $total = $merged->count();

        return new LengthAwarePaginator(
            $merged->slice(($page - 1) * $perPage, $perPage)->values(),
            $total,
            $perPage,
            $page,
            [
                'path' => request()->url(),
                'query' => request()->query()
            ]
            );
    }

    public function detailRefund($type, $id){
        if($type === 'passenger'){
            return $this->passengerRepo->getRejected()->where('id', $id)->first();
        }
        if($type === 'goods'){
            return $this->goodsRepo->getRejected()->where('id', $id)->first();
        }

        throw new \InvalidArgumentException("Refund type invalid");
    }
}
