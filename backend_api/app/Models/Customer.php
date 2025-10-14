<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class Customer extends Model
{
    //
    use HasFactory;

    protected $table = 'customers';

    protected $fillable = [
        'user_id',
        'full_name',
        'telephone',
        'full_address',
        'profile_img',
        'verified',
        'face_img',
        'face_with_id_img',
        'id_card_img',
        'id_card_number',
        'id_card_fullname',
        'id_card_birthdate',
        'credit_amount',
    ];

    protected $casts = [
        'verified' => 'boolean',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    // Relasi ke tabel users
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    // Relasi ke passenger bookings
    public function passengerBookings()
    {
        return $this->hasMany(PassengerRideBooking::class, 'customer_id');
    }

    // Relasi ke goods bookings
    public function goodsBookings()
    {
        return $this->hasMany(GoodsRideBooking::class, 'customer_id');
    }

    // Relasi ke transaksi (penumpang dan barang)
    public function passengerTransactions()
    {
        return $this->hasMany(PassengerTransaction::class, 'customer_id');
    }

    public function goodsTransactions()
    {
        return $this->hasMany(GoodsTransaction::class, 'customer_id');
    }

    /**
     * ========================
     * ACCESSORS & HELPERS
     * ========================
     */

    // Mengembalikan status verifikasi dalam format teks
    public function getVerificationStatusAttribute(): string
    {
        return $this->verified ? 'Terverifikasi' : 'Belum Terverifikasi';
    }

    // Menampilkan format saldo kredit
    public function getFormattedCreditAttribute(): string
    {
        return 'Rp' . number_format($this->credit_amount, 0, ',', '.');
    }

    // Helper untuk cek apakah punya KTP lengkap
    public function getHasIdCardAttribute(): bool
    {
        return !empty($this->id_card_number) && !empty($this->id_card_img);
    }
}
