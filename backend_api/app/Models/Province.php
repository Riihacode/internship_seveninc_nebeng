<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Province extends Model
{
    use HasFactory;

    protected $table = 'provinces';

    protected $fillable = [
        'name',
    ];

    // RELATIONSHIP

    // relasi one to many ke regencies, terminals, dan administrators
    public function regencies() {
        return $this->hasMany(Regency::class, 'province_id');
    }

    public function terminals() {
        return $this->hasMany(Terminal::class, 'province_id');
    }

    public function administrators() {
        return $this->hasMany(Administrator::class, 'province_id');
    }

    // format nama province menjadi kaapital setiap kata.
    public function getFormattedNameAttribute(): String {
        return ucwords(strtolower($this->name));
    }
}
