<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class PopularDestination extends Model
{
    //
    use HasFactory;

    protected $table = 'popular_destinations';

    /**
     * Kolom yang dapat diisi mass-assignment.
     */
    protected $fillable = [
        'title',
        'destination_img',
    ];

    /**
     * ========================
     * ACCESSORS / MUTATORS
     * ========================
     */

    /**
     * Mendapatkan URL penuh gambar destinasi.
     */
    public function getImageUrlAttribute(): string
    {
        return asset('storage/' . $this->destination_img);
    }

    /**
     * Capitalize judul destinasi secara otomatis saat diakses.
     */
    public function getFormattedTitleAttribute(): string
    {
        return ucwords(strtolower($this->title));
    }
}
