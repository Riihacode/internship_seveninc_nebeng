<?php

namespace Database\Seeders;

use Carbon\Carbon;
use App\Models\Driver;
use App\Models\Rating;
use App\Models\Customer;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class RatingSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // Ambil semua driver & customer dari database
        $drivers = Driver::all();
        $customers = Customer::all();

        // Jika belum ada data driver atau customer, hentikan
        if ($drivers->isEmpty() || $customers->isEmpty()) {
            $this->command->warn('⚠️ Tidak ada data driver atau customer. Seeder Rating dilewati.');
            return;
        }

        // Contoh data rating dummy
        $feedbacks = [
            'Pelayanan sangat ramah dan cepat!',
            'Perjalanan nyaman, sopan, dan tepat waktu.',
            'Kurang puas, pengemudi terlambat menjemput.',
            'Mobil bersih dan wangi, recommended!',
            'Kurang komunikatif tapi tetap aman sampai tujuan.'
        ];

        // Loop: setiap driver dapat beberapa rating acak
        foreach ($drivers as $driver) {
            $numRatings = rand(3, 8); // jumlah rating per driver
            for ($i = 0; $i < $numRatings; $i++) {
                $customer = $customers->random();

                Rating::create([
                    'driver_id' => $driver->id,
                    'customer_id' => $customer->id,
                    'rating' => rand(3, 5),
                    'feedback' => $feedbacks[array_rand($feedbacks)],
                    'created_at' => Carbon::now()->subDays(rand(0, 60)),
                ]);
            }
        }

        $this->command->info('✅ RatingSeeder berhasil dijalankan: rating acak telah ditambahkan.');
    }
}
