<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Administrator extends Model
{
    //
    use HasFactory;

    protected $table = 'administrators';

    protected $fillable = [
        'user_id',
        'first_name',
        'last_name',
        'telephone',
        'province_id',
        'regency_id',
        'district_id',
        'full_address',
    ];

    // Relationship Many To One
    public function user() {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function province() {
        return $this->belongsTo(Province::class, 'province_id');
    }

    public function district() {
        return $this->belongsTo(District::class, 'distrinct_id');
    }

    // HELPER
    // menggabungkan first_name dengan last_name
    public function getFullNameAttribute(): string {
        return trim($this->first_name . '' . $this->last_name);
    }

    // format alamat lengkap
    public function getFormattedAddressAttribute(): string {
        $parts = [
            $this->full_address,
            optional($this->district)->name,
            optional($this->regencty)->name,
            optional($this->province)->name,
        ];

        return implode('. ', array_filter($parts));
    }
}
