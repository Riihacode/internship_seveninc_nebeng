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
        Schema::create('driver_withdrawals', function (Blueprint $table) {
            $table->id();

            // Relasi ke tabel drivers
            $table->foreignId('driver_id')
                ->constrained(
                    table: 'drivers',
                    indexName: 'driver_withdrawals_driver_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Jumlah penarikan saldo (Rp)
            $table->integer('withdrawal_amount');

            // Informasi rekening tujuan
            $table->string('bank_name');
            $table->string('account_name');
            $table->string('account_number');

            // Status penarikan
            $table->enum('status', ['Pending', 'Diterima', 'Ditolak'])
                  ->default('Pending');

            // Alasan penolakan (jika ditolak)
            $table->string('rejected_reason')->nullable();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('driver_withdrawals');
    }
};
