<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\PassengerPricing;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class PassengerPricingSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        $this->command->info('⚠️ PassengerPricingSeeder dijalankan!');

        PassengerPricing::insert([
            [
                'vehicle_type' => 'Motor',
                'departure_terminal_id' => 1,
                'arrival_terminal_id' => 2,
                'price_per_seat' => 25000,
                'commision_percentage' => 10,
            ],
            [
                'vehicle_type' => 'Mobil',
                'departure_terminal_id' => 1,
                'arrival_terminal_id' => 3,
                'price_per_seat' => 50000,
                'commision_percentage' => 12,
            ],
            [
                'vehicle_type' => 'Mobil',
                'departure_terminal_id' => 2,
                'arrival_terminal_id' => 3,
                'price_per_seat' => 70000,
                'commision_percentage' => 15,
            ],
        ]);

        $this->command->info('✅ PassengerRideBookingSeeder berhasil dijalankan!');
    }
}
