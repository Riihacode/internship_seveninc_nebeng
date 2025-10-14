<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('notifications', function (Blueprint $table) {
            $table->id();

            // Relasi ke tabel users
            $table->foreignId('user_id')
                ->constrained(
                    table: 'users',
                    indexName: 'notifications_user_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Pesan notifikasi
            $table->string('message');

            // Status terbaca / belum
            $table->boolean('is_read')->default(false);

            // Waktu pembuatan notifikasi
            $table->timestamp('created_at')->useCurrent();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('notifications');
    }
};
