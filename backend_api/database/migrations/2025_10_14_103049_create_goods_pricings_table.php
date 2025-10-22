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
        Schema::create('goods_pricings', function (Blueprint $table) {
            $table->id();
            $table->enum('transport_type', ['sendiri', 'umum']);

            // Departure TErminal
            $table->foreignId('departure_terminal_id')
                ->constrained(
                    table: 'terminals',
                    indexName: 'goods_pricing_departure_terminal_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Arrival Terminal
            $table->foreignId('arrival_terminal_id')
                ->constrained(
                    table: 'terminals',
                    indexName: 'goods_pricing_arrival_terminal_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->integer('price_per_kg');
            $table->integer('commission_percentage');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('goods_pricings');
    }
};
