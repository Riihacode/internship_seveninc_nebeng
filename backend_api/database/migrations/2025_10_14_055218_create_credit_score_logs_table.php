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
        Schema::create('credit_score_logs', function (Blueprint $table) {
            $table->id();

            // Relasi ke tabel drivers
            $table->foreignId('driver_id')
                ->constrained(
                    table: 'drivers',
                    indexName: 'credit_score_logs_driver_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->string('action_type'); // contoh: "Completed Ride", "Late Arrival", "Complaint"
            $table->integer('score_change'); // lebih baik integer daripada string untuk operasi numerik
            $table->string('notes')->nullable();

            $table->timestamp('created_at')->useCurrent();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('credit_score_logs');
    }
};
