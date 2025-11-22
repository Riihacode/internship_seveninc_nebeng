<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class GoodsBookingResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'booking_type' => 'Goods',
            'total_price'  => $this->total_price,
            'status'  => $this->status,
            'booking_code' => $this->booking_code,
            'created_at'   => $this->created_at,
            'layanan' => $this->goodsRide->transport_type ?? null,
            'driver_name' => $this->goodsRide->driver->full_name ?? '-',
            'customer_name' => $this->customer->full_name ?? '-',
            'customer_phone' => $this->customer->telephone ?? '-',
            'driver_phone' => $this->goodsRide->driver->telephone,
            'terminal_keberangkatan' => $this->goodsRide->departureTerminal->name ?? '-',
            'terminal_tujuan' => $this->goodsRide->arrivalTerminal->name?? '-',
            'kota_tujuan' => $this->goodsRide->arrivalTerminal->regency->name ?? '-',
            'kota_awal' => $this->goodsRide->departureTerminal->regency->name ?? '-',
            'booking_id' => $this->id,
            'transaction_code' => $this->goodsTransaction->transaction_code
        ];
    }
}
