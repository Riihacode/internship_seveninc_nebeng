<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class GoodsRideBooking extends Model
{
    //
    use HasFactory;

    protected $table = 'goods_ride_bookings';

    /**
     * Kolom yang dapat diisi mass-assignment.
     */
    protected $fillable = [
        'goods_ride_id',
        'customer_id',
        'item_weight',
        'item_description',
        'item_img',
        'total_price',
        'status',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke GoodsRide
     * Satu booking milik satu perjalanan barang.
     */
    public function goodsRideId()
    {
        return $this->belongsTo(GoodsRide::class, 'goods_ride_id');
    }

    /**
     * Relasi ke Customer
     * Satu booking milik satu pelanggan.
     */
    public function customerId()
    {
        return $this->belongsTo(Customer::class, 'customer_id');
    }

    /**
     * Relasi ke transaksi (GoodsTransaction)
     * Satu booking bisa punya satu transaksi pembayaran.
     */
    public function goodsTransactions()
    {
        return $this->hasOne(GoodsTransaction::class, 'goods_ride_booking_id');
    }

    /**
     * ========================
     * CASTS & CONSTANTS
     * ========================
     */

    public const STATUS_PENDING = 'Pending';
    public const STATUS_ACCEPTED = 'Diterima';
    public const STATUS_REJECTED = 'Ditolak';

    /**
     * ========================
     * HELPERS
     * ========================
     */

    /**
     * Apakah booking sudah diterima?
     */
    public function isAccepted(): bool
    {
        return $this->status === self::STATUS_ACCEPTED;
    }

    /**
     * Apakah booking ditolak?
     */
    public function isRejected(): bool
    {
        return $this->status === self::STATUS_REJECTED;
    }

    /**
     * Hitung harga per kilogram barang.
     */
    public function getPricePerKgAttribute(): int
    {
        return $this->item_weight > 0
            ? intdiv($this->total_price, $this->item_weight)
            : 0;
    }

    /**
     * Deskripsi singkat untuk tampilan.
     */
    public function getSummaryAttribute(): string
    {
        return "Booking #{$this->id} - {$this->status} ({$this->item_weight}kg)";
    }
}
