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
        Schema::create('terminals', function (Blueprint $table) {
            $table->id();
            $table->string('name');

            // Enum sesuai diagram terminal_types dan public_terminal_subtypes
            $table->enum('terminal_type', ['Public', 'Private']);

            $table->enum('public_terminal_subtype', ['Terminal Bis', 'Stasiun Kereta', 'Bandara', 'Pelabuhan'])
                  ->nullable();

            // Foreign keys ke lokasi administratif
            $table->foreignId('province_id')
                ->constrained(
                    table: 'provinces',
                    indexName: 'terminals_province_id_fk'
                )
                ->cascadeOnUpdate()
                ->restrictOnDelete();

            $table->foreignId('regency_id')
                ->constrained(
                    table: 'regencies',
                    indexName: 'terminals_regency_id_fk'
                )
                ->cascadeOnUpdate()
                ->restrictOnDelete();

            $table->foreignId('district_id')
                ->constrained(
                    table: 'districts',
                    indexName: 'terminals_district_id_fk'
                )
                ->cascadeOnUpdate()
                ->restrictOnDelete();

            $table->string('full_address');
            $table->string('longitude');
            $table->string('latitude');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('terminals');
    }
};
