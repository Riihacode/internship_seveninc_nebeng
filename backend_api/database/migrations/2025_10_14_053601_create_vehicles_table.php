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
        Schema::create('vehicles', function (Blueprint $table) {
            $table->id();

            // Relasi ke tabel drivers
            $table->foreignId('driver_id')
                ->constrained(
                    table: 'drivers',
                    indexName: 'vehicles_driver_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->string('registration_number');  // Nomor polisi / STNK
            $table->string('registration_year');
            $table->string('registration_expired'); // lebih baik: date() kalau mau format tanggal
            $table->string('registration_img');

            $table->string('vehicle_name');
            $table->string('vehicle_color');

            // Enum jenis kendaraan
            $table->enum('vehicle_type', ['motor', 'mobil']);

            $table->string('vehicle_img');
            $table->boolean('vehicle_verified')->default(false);
            $table->string('vehicle_rejected_reason')->nullable();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('vehicles');
    }
};
