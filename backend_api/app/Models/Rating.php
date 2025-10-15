<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class Rating extends Model
{
    //
    use HasFactory;

    protected $table = 'ratings';

    /**
     * Karena tabel ini hanya punya created_at.
     */
    public $timestamps = false;

    /**
     * Kolom yang dapat diisi mass-assignment.
     */
    protected $fillable = [
        'driver_id',
        'customer_id',
        'rating',
        'feedback',
        'created_at',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke Driver (yang diberi rating)
     */
    public function driver()
    {
        return $this->belongsTo(Driver::class, 'driver_id');
    }

    /**
     * Relasi ke Customer (yang memberi rating)
     */
    public function customer()
    {
        return $this->belongsTo(Customer::class, 'customer_id');
    }

    /**
     * ========================
     * CASTS & ACCESSORS
     * ========================
     */
    protected $casts = [
        'rating' => 'integer',
        'created_at' => 'datetime',
    ];

    /**
     * ========================
     * HELPERS
     * ========================
     */

    /**
     * Menampilkan label bintang rating (contoh: ⭐⭐⭐⭐☆)
     */
    public function getStarsAttribute(): string
    {
        $full = str_repeat('⭐', $this->rating);
        $empty = str_repeat('☆', 5 - $this->rating);
        return $full . $empty;
    }

    /**
     * Menampilkan ringkasan singkat untuk tampilan admin.
     */
    public function getSummaryAttribute(): string
    {
        return "Rating {$this->rating}/5 {$this->stars}" .
            ($this->feedback ? " — {$this->feedback}" : '');
    }
}
