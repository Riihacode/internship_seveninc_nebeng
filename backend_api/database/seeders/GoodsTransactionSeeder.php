<?php

namespace Database\Seeders;

use Carbon\Carbon;
use App\Models\Customer;
use App\Models\PaymentMethod;
use Illuminate\Database\Seeder;
use App\Models\GoodsRideBooking;
use App\Models\GoodsTransaction;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class GoodsTransactionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        $bookings = GoodsRideBooking::all();
        $customers = Customer::all();
        $methods = PaymentMethod::all();

        if ($bookings->isEmpty() || $customers->isEmpty() || $methods->isEmpty()) {
            $this->command->warn('⚠️ GoodsTransactionSeeder dilewati: data booking/customer/payment_method belum ada.');
            return;
        }

        $statuses = ['Pending', 'Diterima', 'Ditolak', 'Credited'];

        foreach ($bookings as $booking) {
            GoodsTransaction::create([
                'goods_ride_booking_id' => $booking->id,
                'customer_id'           => $booking->customer_id ?? $customers->random()->id,
                'total_amount'          => $booking->total_price ?? rand(50000, 200000),
                'payment_method_id'     => $methods->random()->id,
                'payment_proof_img'     => null,
                'payment_status'        => $statuses[array_rand($statuses)],
                'credit_used'           => rand(0, 1) ? rand(0, 50000) : 0,
                'transaction_date'      => Carbon::now()->subDays(rand(0, 20)),
            ]);
        }

        $this->command->info('✅ GoodsTransactionSeeder berhasil dijalankan!');
    }
}
