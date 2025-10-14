<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

class Notification extends Model
{
    //
    use HasFactory;

    protected $table = 'notifications';

    /**
     * Tidak ada kolom updated_at.
     */
    public $timestamps = false;

    /**
     * Kolom yang bisa diisi mass-assignment.
     */
    protected $fillable = [
        'user_id',
        'message',
        'is_read',
        'created_at',
    ];

    /**
     * ========================
     * RELATIONSHIPS
     * ========================
     */

    /**
     * Relasi ke User penerima notifikasi.
     */
    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    /**
     * ========================
     * CASTS & CONSTANTS
     * ========================
     */
    protected $casts = [
        'is_read' => 'boolean',
        'created_at' => 'datetime',
    ];

    /**
     * ========================
     * HELPERS
     * ========================
     */

    /**
     * Tandai notifikasi sebagai sudah dibaca.
     */
    public function markAsRead(): void
    {
        if (!$this->is_read) {
            $this->is_read = true;
            $this->save();
        }
    }

    /**
     * Format waktu dibuat ke format human readable.
     */
    public function getFormattedDateAttribute(): string
    {
        return $this->created_at
            ? $this->created_at->diffForHumans()
            : '-';
    }

    /**
     * Format singkat untuk tampilan.
     */
    public function getSummaryAttribute(): string
    {
        $status = $this->is_read ? '✓ Dibaca' : '• Belum dibaca';
        return "{$this->message} ({$status}, {$this->formatted_date})";
    }
}
