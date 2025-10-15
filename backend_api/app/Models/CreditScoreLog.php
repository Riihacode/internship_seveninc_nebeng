<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class CreditScoreLog extends Model
{
    //
    use HasFactory;

    protected $table = 'credit_score_logs';

    /**
     * Tidak ada updated_at di tabel ini.
     */
    public $timestamps = false;

    /**
     * Kolom yang dapat diisi secara mass assignment.
     */
    protected $fillable = [
        'driver_id',
        'action_type',
        'score_change',
        'notes',
        'created_at',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke Driver.
     * Satu log milik satu driver.
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
        'score_change' => 'integer',
        'created_at' => 'datetime',
    ];

    /**
     * Accessor ringkas untuk deskripsi log.
     */
    public function getDescriptionAttribute(): string
    {
        $change = $this->score_change > 0 ? "+{$this->score_change}" : $this->score_change;
        return "{$this->action_type} ({$change} pts)";
    }
}
