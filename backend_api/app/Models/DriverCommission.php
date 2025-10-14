<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class DriverCommission extends Model
{
    //
    //
    use HasFactory;

    protected $table = 'driver_commissions';

    /**
     * Kolom yang dapat diisi mass-assignment.
     */
    protected $fillable = [
        'driver_id',
        'income',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke Driver.
     * Satu komisi milik satu driver.
     */
    public function driver()
    {
        return $this->belongsTo(Driver::class, 'driver_id');
    }

    /**
     * ========================
     * CASTS & ACCESSORS
     * ========================
     */

    protected $casts = [
        'income' => 'integer',
        'created_at' => 'datetime',
        'updated_at' => 'datetime',
    ];

    /**
     * ========================
     * HELPERS
     * ========================
     */

    /**
     * Format pendapatan ke format rupiah.
     */
    public function getFormattedIncomeAttribute(): string
    {
        return 'Rp' . number_format($this->income, 0, ',', '.');
    }

    /**
     * Ringkasan singkat untuk tampilan.
     */
    public function getSummaryAttribute(): string
    {
        return "Komisi #{$this->id} | {$this->formatted_income} ({$this->created_at->format('d M Y')})";
    }
}
