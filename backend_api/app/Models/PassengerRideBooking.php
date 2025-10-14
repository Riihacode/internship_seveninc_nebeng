<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class PassengerRideBooking extends Model
{
    //
    use HasFactory;

    protected $table = 'passenger_ride_bookings';

    /**
     * Kolom yang dapat diisi mass-assignment.
     */
    protected $fillable = [
        'passenger_ride_id',
        'customer_id',
        'seats_reserved',
        'total_price',
        'status',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke PassengerRide.
     * Satu booking milik satu ride.
     */
    public function passengerRide()
    {
        return $this->belongsTo(PassengerRide::class, 'passenger_ride_id');
    }

    /**
     * Relasi ke Customer.
     * Satu booking milik satu customer.
     */
    public function customer()
    {
        return $this->belongsTo(Customer::class, 'customer_id');
    }

    /**
     * Relasi ke transaksi (PassengerTransaction)
     * Satu booking bisa punya satu transaksi.
     */
    public function transaction()
    {
        return $this->hasOne(PassengerTransaction::class, 'passenger_ride_booking_id');
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
     * Cek apakah booking sudah diterima.
     */
    public function isAccepted(): bool
    {
        return $this->status === self::STATUS_ACCEPTED;
    }

    /**
     * Cek apakah booking ditolak.
     */
    public function isRejected(): bool
    {
        return $this->status === self::STATUS_REJECTED;
    }

    /**
     * Hitung harga per kursi.
     */
    public function getPricePerSeatAttribute(): int
    {
        return $this->seats_reserved > 0
            ? intdiv($this->total_price, $this->seats_reserved)
            : 0;
    }

    /**
     * Format ringkasan untuk tampilan.
     */
    public function getSummaryAttribute(): string
    {
        return "Booking #{$this->id} - {$this->status} ({$this->seats_reserved} kursi)";
    }
}
