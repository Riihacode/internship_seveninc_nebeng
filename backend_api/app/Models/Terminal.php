<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class Terminal extends Model
{
    //
    use HasFactory;

    protected $table = 'terminals';

    /**
     * Kolom yang bisa diisi secara mass-assignment
     */
    protected $fillable = [
        'name',
        'terminal_type',
        'public_terminal_subtype',
        'province_id',
        'regency_id',
        'district_id',
        'full_address',
        // 'longitude',
        // 'latitude',
        'latitude',
        'longitude',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke Province
     * One Terminal belongs to one Province
     */
    public function province()
    {
        return $this->belongsTo(Province::class, 'province_id');
    }

    /**
     * Relasi ke Regency (Kabupaten/Kota)
     * One Terminal belongs to one Regency
     */
    public function regency()
    {
        return $this->belongsTo(Regency::class, 'regency_id');
    }

    /**
     * Relasi ke District (Kecamatan)
     * One Terminal belongs to one District
     */
    public function district()
    {
        return $this->belongsTo(District::class, 'district_id');
    }


    // Relationship One To Many
    public function departurePassengerRides() {
        return $this->hasMany(PassengerRide::class, 'departure_terminal_id');
    }

    public function arrivalPassengerRides() {
        return $this->hasMany(PassengerRide::class, 'arrival_terminal_id');
    }

    public function departureGoodsRides() {
        return $this->hasMany(GoodsRide::class, 'departure_terminal_id');
    }

    public function arrivalGoodsRides() {
        return $this->hasMany(GoodsRide::class, 'arrival_terminal_id');
    }

    public function departurePassengerPricings() {
        return $this->hasMany(PassengerPricing::class, 'departure_terminal_id');
    }

    public function arrivalPassengerPricings() {
        return $this->hasMany(PassengerPricing::class, 'arrival_terminal_id');
    }

    public function departureGoodsPricings() {
        return $this->hasMany(GoodsPricing::class, 'departure_terminal_id');
    }

    public function arrivalGoodsPricings() {
        return $this->hasMany(GoodsPricing::class, 'arrival_terminal_id');
    }


    /**
     * ========================
     * ACCESSORS & HELPERS
     * ========================
     */

    /**
     * Contoh accessor untuk menampilkan lokasi lengkap.
     */
    public function getLocationAttribute(): string
    {
        $parts = [
            $this->full_address,
            optional($this->district)->name,
            optional($this->regency)->name,
            optional($this->province)->name,
        ];

        return implode(', ', array_filter($parts));
    }

    /**
     * Casts
     * Bisa dipakai jika nanti longitude dan latitude mau disimpan sebagai float
     */
    protected $casts = [
        // 'longitude' => 'string',
        // 'latitude' => 'string',
        'latitude'  => 'float',
        'longitude' => 'float',
    ];
}
