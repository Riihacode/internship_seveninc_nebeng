<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class Slider extends Model
{
    use HasFactory;
    //

    protected $table = 'sliders';

    /**
     * Kolom yang dapat diisi mass-assignment.
     */
    protected $fillable = [
        'slider_img',
        'is_active',
    ];

    /**
     * ========================
     * CASTS
     * ========================
     */
    protected $casts = [
        'is_active' => 'boolean',
    ];

    /**
     * ========================
     * SCOPES
     * ========================
     */

    /**
     * Ambil hanya slider yang aktif.
     */
    public function scopeActive($query)
    {
        return $query->where('is_active', true);
    }

    /**
     * Ambil hanya slider yang tidak aktif.
     */
    public function scopeInactive($query)
    {
        return $query->where('is_active', false);
    }

    /**
     * ========================
     * HELPERS
     * ========================
     */

    /**
     * Format tampilan status slider.
     */
    public function getStatusLabelAttribute(): string
    {
        return $this->is_active ? 'Aktif' : 'Nonaktif';
    }

    /**
     * Mendapatkan URL penuh gambar slider.
     * (Gunakan helper asset() untuk public path)
     */
    public function getImageUrlAttribute(): string
    {
        return asset('storage/' . $this->slider_img);
    }
}
