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
        Schema::create('administrators', function (Blueprint $table) {
            $table->id();

            $table->foreignId('user_id')
                ->constrained(
                    table: 'users',
                    indexName: 'administrator_user_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->string('first_name');
            $table->string('last_name');
            $table->string('telephone');

            $table->foreignId('province_id')
                ->constrained(
                    table: 'provinces',
                    indexName: 'administrator_province_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->foreignId('regency_id')
                ->constrained(
                    table: 'regencies',
                    indexName: 'administrator_regency_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->foreignId('district_id')
                ->constrained(
                    table: 'districts',
                    indexName: 'administrator_district_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();
            
            $table->string('full_address');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('administrators');
    }
};
