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
        Schema::create('goods_transactions', function (Blueprint $table) {
            $table->id();

            // Relasi ke goods_ride_bookings
            $table->foreignId('goods_ride_booking_id')
                ->constrained(
                    table: 'goods_ride_bookings',
                    indexName: 'goods_transactions_goods_ride_booking_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Relasi ke customers
            $table->foreignId('customer_id')
                ->constrained(
                    table: 'customers',
                    indexName: 'goods_transactions_customer_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            // Total biaya transaksi
            $table->integer('total_amount');

            // Relasi ke metode pembayaran
            $table->foreignId('payment_method_id')
                ->constrained(
                    table: 'payment_methods',
                    indexName: 'goods_transactions_payment_method_id_fk'
                )
                ->cascadeOnUpdate()
                ->restrictOnDelete();

            // Bukti pembayaran
            $table->string('payment_proof_img')->nullable();

            // Status pembayaran
            $table->enum('payment_status', ['pending', 'diterima', 'ditolak', 'credited'])
                  ->default('pending');

            // Jumlah kredit yang digunakan (switch dari boolean)
            $table->integer('credit_used')->default(0);

            // Tanggal transaksi
            $table->timestamp('transaction_date')->useCurrent();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('goods_transactions');
    }
};
