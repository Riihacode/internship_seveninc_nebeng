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
        Schema::create('passenger_transactions', function (Blueprint $table) {
            $table->id();

            $table->foreignId('passenger_ride_booking_id')
                ->constrained(
                    table: 'passenger_ride_bookings',
                    indexName: 'passenger_transactions_passenger_ride_booking_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->foreignId('customer_id')
                ->constrained(
                    table: 'customers',
                    indexName: 'passenger_transactions_customer_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->integer('total_amount');

            $table->foreignId('payment_method_id')
                ->constrained(
                    table: 'payment_methods',
                    indexName: 'passenger_transactions_payment_method_id_fk'
                )
                ->cascadeOnUpdate()
                ->restrictOnDelete();

            $table->string('payment_proof_img')->nullable();

            $table->enum('payment_status', ['pending', 'diterima', 'ditolak', 'credited'])
                  ->default('pending');

            $table->integer('credit_used')->default(0);

            $table->timestamp('transaction_date')->useCurrent();
            $table->timestamps();

            // $table->id();

            // $table->foreignId('passenger_ride_booking_id')
            //     ->constrained(
            //         table: 'passenger_ride_bookings',
            //         indexName: 'passenger_transactions_passenger_ride_booking_id_fk'
            //     )
            //     ->cascadeOnUpdate()
            //     ->cascadeOnDelete();

            // $table->foreignId('customer_id')
            //     ->constrained(
            //         table: 'customers',
            //         indexName: 'passenger_transactions_customer_id_fk'
            //     )
            //     ->cascadeOnUpdate()
            //     ->cascadeOnDelete();

            // $table->integer('total_amount');

            // $table->foreignId('payment_method_id')
            //     ->constrained(
            //         table: 'payment_methods',
            //         indexName: 'passenger_transactions_payment_method_id_fk'
            //     )
            //     ->cascadeOnUpdate()
            //     ->restrictOnDelete();

            // $table->string('payment_proof_img')->nullable();

            // $table->enum('payment_status', ['Pending', 'Diterima', 'Ditolak', 'Credited'])
            //       ->default('Pending');

            // $table->integer('credit_used')->default(0);

            // $table->timestamp('transaction_date')->useCurrent();
            // $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('passenger_transactions');
    }
};
