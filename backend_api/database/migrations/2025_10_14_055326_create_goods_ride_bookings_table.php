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
        Schema::create('goods_ride_bookings', function (Blueprint $table) {
            $table->id();

            // Relasi ke goods_rides
            $table->foreignId('goods_ride_id')
                ->constrained(
                    table: 'goods_rides',
                    indexName: 'goods_ride_bookings_goods_ride_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Relasi ke customers
            $table->foreignId('customer_id')
                ->constrained(
                    table: 'customers',
                    indexName: 'goods_ride_bookings_customer_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Detail barang
            $table->integer('item_weight'); // dalam kg
            $table->string('item_description');
            $table->string('item_img');

            // Harga total untuk semua barang
            $table->integer('total_price');

            // Status booking
            $table->enum('status', ['Pending', 'Diterima', 'Ditolak'])
                  ->default('Pending');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('goods_ride_bookings');
    }
};
