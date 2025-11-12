<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class Driver extends Model
{
    //
    use HasFactory;

    protected $table = 'drivers';

    /**
     * Kolom yang bisa diisi secara mass-assignment.
     */
    protected $fillable = [
        'user_id',
        'full_name',
        'telephone',
        'full_address',
        'profile_img',
        'balance',
        'credit_score',
        'total_rating',
        'rating_count',
        'average_rating',

        // ID Card
        'face_img',
        'face_with_id_img',
        'id_card_img',
        'id_card_number',
        'id_card_fullname',
        'id_card_birthdate',
        'id_card_verified',
        'id_card_rejected_reason',

        // Driver License
        'driver_license_number',
        'driver_license_type',
        'driver_license_expired',
        'driver_license_img',
        'driver_license_verified',
        'driver_license_rejected_reason',

        // Police Clearance Certificate (SKCK)
        'police_clearance_certificate_number',
        'police_clearance_certificate_fullname',
        'police_clearance_certificate_expired',
        'police_clearance_certificate_img',
        'police_clearance_verified',
        'police_clearance_rejected_reason',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke User
     * Satu driver dimiliki oleh satu user.
     */
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    /**
     * Relasi ke Vehicle (jika tabel vehicle sudah ada)
     * Satu driver memiliki banyak kendaraan.
     */
    public function vehicles()
    {
        return $this->hasMany(Vehicle::class, 'driver_id');
    }

    /**
     * Relasi ke CreditScoreLog (jika ada)
     * Untuk riwayat perubahan credit score driver.
     */
    public function creditScoreLogs()
    {
        return $this->hasMany(CreditScoreLog::class, 'driver_id');
    }

    /**
     * Relasi ke PassengerRide dan GoodsRide
     * Satu driver bisa punya banyak ride.
     */
    public function passengerRides()
    {
        return $this->hasMany(PassengerRide::class, 'driver_id');
    }

    public function goodsRides()
    {
        return $this->hasMany(GoodsRide::class, 'driver_id');
    }

    /**
     * Relasi ke DriverCommission dan Withdrawals
     */
    public function driverCommissions()
    {
        return $this->hasMany(DriverCommission::class, 'driver_id');
    }

    public function driverWithdrawals()
    {
        return $this->hasMany(DriverWithdrawal::class, 'driver_id');
    }

    /**
     * Relasi ke Rating
     * Satu driver bisa menerima banyak rating dari pelanggan.
     */
    public function ratings()
    {
        return $this->hasMany(Rating::class, 'driver_id');
    }

    /**
     * ========================
     * CASTS & ACCESSORS
     * ========================
     */
    protected $casts = [
        'id_card_verified' => 'boolean',
        'driver_license_verified' => 'boolean',
        'Police_clearance_verified' => 'boolean',
        'balance' => 'integer',
        'credit_score' => 'integer',
    ];

    /**
     * ========================
     * CONSTANTS
     * ========================
     */
    public const VERIFIED_TRUE = true;
    public const VERIFIED_FALSE = false;
    public const VERIFIED_NULL = null;

    /**
     * ========================
     * HELPERS
     * ========================
     */

    /**
     * Cek status verifikasi ID card.
     */
    public function isIdCardVerified(): bool
    {
        return $this->id_card_verified === true;
    }

    public function isDriverLicenseVerified(): bool
    {
        return $this->driver_license_verified === true;
    }

    public function isPoliceClearanceVerified(): bool
    {
        return $this->Police_clearance_verified === true;
    }

    /**
     * Helper untuk menghitung jumlah dokumen diverifikasi.
     */
    public function getVerifiedDocumentCountAttribute(): int
    {
        return collect([
            $this->id_card_verified,
            $this->driver_license_verified,
            $this->Police_clearance_verified,
        ])->filter(fn ($v) => $v === true)->count();
    }

    /**
     * Helper gabungan untuk menampilkan status verifikasi semua dokumen.
     */
    public function getVerificationSummaryAttribute(): array
    {
        return [
            'id_card' => $this->id_card_verified ? 'Verified' : 'Not Verified',
            'driver_license' => $this->driver_license_verified ? 'Verified' : 'Not Verified',
            'police_clearance' => $this->Police_clearance_verified ? 'Verified' : 'Not Verified',
        ];
    }
}
