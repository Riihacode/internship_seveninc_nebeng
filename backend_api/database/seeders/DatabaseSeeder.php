<?php

namespace Database\Seeders;

use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        // User::factory(10)->create();

        // User::factory()->create([
        //     'name' => 'Test User',
        //     'email' => 'test@example.com',
        // ]);

        $this->call([
            // AuthSeeder::class,
            // DriverSeeder::class,
            // CustomerSeeder::class,
            // ProvinceSeeder::class,
            // RegencySeeder::class,
            // DistrictSeeder::class,
            // RatingSeeder::class,
            // GoodsRideSeeder::class,
            // PassengerRideSeeder::class,
            // PassengerRideBookingSeeder::class,
            // GoodsRideBookingSeeder::class,
            // GoodsTransactionSeeder::class,
            // PaymentMethodSeeder::class,
            // PassengerTransactionSeeder::class,

            // ProvinceSeeder::class,
            // RegencySeeder::class,
            // DistrictSeeder::class,
            // TerminalSeeder::class,

            // AuthSeeder::class,
            // CustomerSeeder::class,
            // DriverSeeder::class,

            // PassengerRideSeeder::class,
            // PassengerRideBookingSeeder::class,

            // GoodsRideSeeder::class,
            // GoodsRideBookingSeeder::class,



            // PaymentMethodSeeder::class,
            // PassengerTransactionSeeder::class,
            // GoodsTransactionSeeder::class,
            // RatingSeeder::class,

            ProvinceSeeder::class,
            RegencySeeder::class,
            DistrictSeeder::class,
            TerminalSeeder::class,

            AuthSeeder::class,
            CustomerSeeder::class,
            DriverSeeder::class,

            PassengerRideSeeder::class,        // <== harus duluan
            PassengerRideBookingSeeder::class, // <== baru booking

            GoodsRideSeeder::class,
            GoodsRideBookingSeeder::class,

            PaymentMethodSeeder::class,
            PassengerTransactionSeeder::class,
            GoodsTransactionSeeder::class,
            RatingSeeder::class,

            VehicleSeeder::class,
            CreditScoreLogSeeder::class,
        ]);
    }
}
