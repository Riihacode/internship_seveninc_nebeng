<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class PassengerBookingResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'booking_type' =>'Passenger',
            'total_price'  => $this->total_price,
            'status'  => $this->status,
            'booking_code' => $this->booking_code,
            'created_at'   => $this->created_at,
            'layanan' => $this->passengerRide->vehicle_type ?? null,
            'driver_name' => $this->passengerRide->driver->full_name ?? '-',
            'customer_name' => $this->customer->full_name ?? '-',
            'customer_phone' => $this->customer->telephone ?? '-',
            'driver_phone' => $this->passengerRide->driver->telephone ?? '-',
            'terminal_keberangkatan' => $this->passengerRide->departureTerminal->name ?? '-',
            'terminal_tujuan' => $this->passengerRide->arrivalTerminal->name ?? '-',
            'kota_tujuan' => $this->passengerRide->departureTerminal->regency->name ?? '-',
            'kota_awal' => $this->passengerRide->arrivalTerminal->regency->name ?? '-',
            'booking_id' => $this->id,
            'transaction_code' => $this->passengerTransaction->transaction_code
        ];
    }
}
