<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class GoodsRide extends Model
{
    protected $table = 'goods_rides';

    protected $fillable = [
        'driver_id',
        'transport_type',
        'public_terminal_subtype',
        'departure_terminal_id',
        'arrival_terminal_id',
        'departure_time',
        'max_weight',
        'weight_reserved',
        'price_per_kg',
        'commission_percentage',
        'ride_status',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    // Satu perjalanan dimiliki oleh satu driver
    public function driver()
    {
        return $this->belongsTo(Driver::class, 'driver_id');
    }

    // Terminal keberangkatan
    public function departureTerminal()
    {
        return $this->belongsTo(Terminal::class, 'departure_terminal_id');
    }

    // Terminal tujuan
    public function arrivalTerminal()
    {
        return $this->belongsTo(Terminal::class, 'arrival_terminal_id');
    }

    // Satu perjalanan memiliki banyak booking barang
    public function goodsRideBooking()
    {
        return $this->hasMany(GoodsRideBooking::class, 'goods_ride_id');
    }

    /**
     * ========================
     * ACCESSORS & HELPERS
     * ========================
     */

    // Hitung total berat tersisa
    public function getRemainingWeightAttribute(): int
    {
        return max(0, $this->max_weight - $this->weight_reserved);
    }

    // Format waktu keberangkatan
    protected $casts = [
        'departure_time' => 'datetime',
    ];
}
