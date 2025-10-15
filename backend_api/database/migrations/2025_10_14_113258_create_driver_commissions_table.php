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
        Schema::create('driver_commissions', function (Blueprint $table) {
            $table->id();

            // Relasi ke tabel drivers
            $table->foreignId('driver_id')
                ->constrained(
                    table: 'drivers',
                    indexName: 'driver_commissions_driver_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Total pendapatan (dalam satuan rupiah)
            $table->integer('income')->default(0);

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('driver_commissions');
    }
};
