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
        Schema::create('ratings', function (Blueprint $table) {
            $table->id();

            // Relasi ke driver
            $table->foreignId('driver_id')
                ->constrained(
                    table: 'drivers',
                    indexName: 'ratings_driver_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Relasi ke customer
            $table->foreignId('customer_id')
                ->constrained(
                    table: 'customers',
                    indexName: 'ratings_customer_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Nilai rating (misal 1–5)
            $table->integer('rating');

            // Feedback opsional dari customer
            $table->string('feedback')->nullable();

            // Waktu pembuatan rating
            $table->timestamp('created_at')->useCurrent();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('ratings');
    }
};
