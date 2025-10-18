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
        Schema::create('customers', function (Blueprint $table) {
            $table->id();

            // Relasi ke tabel users
            $table->foreignId('user_id')
                ->constrained(
                    table: 'users',
                    indexName: 'customers_user_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Data utama customer
            $table->string('full_name');
            $table->string('telephone');
            $table->string('full_address');
            $table->string('profile_img')->nullable();      // Ini wajib dignatik karena tidak boleh null

            // Status verifikasi
            $table->boolean('verified')->default(false);

            // Dokumen wajah & identitas
            $table->string('face_img')->nullable();
            $table->string('face_with_id_img')->nullable();
            $table->string('id_card_img')->nullable();
            $table->string('id_card_number')->nullable();
            $table->string('id_card_fullname')->nullable();
            $table->string('id_card_birthdate')->nullable();

            // Kredit customer (saldo)
            $table->integer('credit_amount')->default(0);

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('customers');
    }
};
