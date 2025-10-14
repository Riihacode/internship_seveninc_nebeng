<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class GoodsTransaction extends Model
{
    //
    use HasFactory;

    protected $table = 'goods_transactions';

    /**
     * Kolom yang dapat diisi mass-assignment.
     */
    protected $fillable = [
        'goods_ride_booking_id',
        'customer_id',
        'total_amount',
        'payment_method_id',
        'payment_proof_img',
        'payment_status',
        'credit_used',
        'transaction_date',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke GoodsRideBooking.
     * Satu transaksi milik satu booking barang.
     */
    public function goodsRideBooking()
    {
        return $this->belongsTo(GoodsRideBooking::class, 'goods_ride_booking_id');
    }

    /**
     * Relasi ke Customer.
     */
    public function customer()
    {
        return $this->belongsTo(Customer::class, 'customer_id');
    }

    /**
     * Relasi ke PaymentMethod.
     */
    public function paymentMethod()
    {
        return $this->belongsTo(PaymentMethod::class, 'payment_method_id');
    }

    /**
     * ========================
     * CASTS & CONSTANTS
     * ========================
     */

    protected $casts = [
        'credit_used' => 'integer',
        'transaction_date' => 'datetime',
    ];

    public const STATUS_PENDING = 'Pending';
    public const STATUS_ACCEPTED = 'Diterima';
    public const STATUS_REJECTED = 'Ditolak';
    public const STATUS_CREDITED = 'Credited';

    /**
     * ========================
     * HELPERS
     * ========================
     */

    /**
     * Cek apakah pembayaran sudah diterima.
     */
    public function isAccepted(): bool
    {
        return $this->payment_status === self::STATUS_ACCEPTED;
    }

    /**
     * Cek apakah pembayaran ditolak.
     */
    public function isRejected(): bool
    {
        return $this->payment_status === self::STATUS_REJECTED;
    }

    /**
     * Cek apakah transaksi menggunakan kredit.
     */
    public function isUsingCredit(): bool
    {
        return $this->credit_used > 0;
    }

    /**
     * Format tanggal transaksi dalam format lokal.
     */
    public function getFormattedDateAttribute(): string
    {
        return $this->transaction_date
            ? $this->transaction_date->format('d M Y, H:i')
            : '-';
    }

    /**
     * Ringkasan singkat untuk tampilan.
     */
    public function getSummaryAttribute(): string
    {
        return "Transaksi #{$this->id} ({$this->payment_status}) - Rp{$this->total_amount}";
    }
}
