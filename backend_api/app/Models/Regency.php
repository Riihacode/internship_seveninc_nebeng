<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasMany;

class Regency extends Model
{
    protected $fillable = [
        'name',
        'province_id'
    ];

    public function province(): BelongsTo {
        return $this->belongsTo(Province::class);
    }

    public function distric(): HasMany{
        return $this->hasMany(District::class);
    }
}
