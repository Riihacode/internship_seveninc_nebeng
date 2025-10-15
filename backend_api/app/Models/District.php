<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class District extends Model
{
    use HasFactory;
    
    protected $table = 'districts';

    protected $fillable = [
        'name',
        'regency_id'
    ];

    // Relationship Manyt To One
    public function regency() : BelongsTo{
        return $this->belongsTo(Regency::class, 'regency_id');
    }

    //Relationship One To Many
    public function terminals() {
        return $this->hasMany(Terminal::class, 'district_id');
    }

    public function administrators() {
        return $this->hasMnay(Administrator::class, 'district_id');
    }

    // Formatted district name to capital letters
    public function getFormattedNameAttribute(): String {
        return ucwords(strtolower($this->name));
    }
}
