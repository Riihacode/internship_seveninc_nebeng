<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Regency extends Model
{
    use HasFactory;
    
    protected $table = 'regencies';

    protected $fillable = [
        'name',
        'province_id'
    ];

    // Relationship Many To One
    public function provinceId(): BelongsTo {
        return $this->belongsTo(Province::class, 'province_id');
    }

    // Relationship One To Many
    public function districts() {
        return $this->hasMany(District::class, 'regency_id');
    }

    public function terminals() {
        return $this->hasMany(Terminal::class, 'regency_id');
    }

    public function administrators() {
        return $this->hasMany(Administrator::class, 'regency_id');
    }

    public function getFormattedNameAttribute(): string {
        return ucwords(strtolower($this->name));
    }
}
