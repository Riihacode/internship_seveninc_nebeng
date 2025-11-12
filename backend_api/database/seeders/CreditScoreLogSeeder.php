<?php

namespace Database\Seeders;

use Illuminate\Support\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class CreditScoreLogSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // Ambil semua ID driver untuk referensi foreign key
        $drivers = DB::table('drivers')->pluck('id')->toArray();

        if (empty($drivers)) {
            $this->command->warn('⚠️  Tidak ada data driver di tabel drivers. Jalankan DriverSeeder terlebih dahulu.');
            return;
        }

        $now = Carbon::now();

        DB::table('credit_score_logs')->insert([
            [
                'driver_id' => $drivers[0],
                'action_type' => 'Order Completed',
                'score_change' => +10,
                'notes' => 'Driver berhasil menyelesaikan perjalanan tepat waktu.',
                'created_at' => $now->copy()->subDays(3),
            ],
            [
                'driver_id' => $drivers[0],
                'action_type' => 'Late Arrival',
                'score_change' => -5,
                'notes' => 'Driver terlambat menjemput penumpang.',
                'created_at' => $now->copy()->subDays(2),
            ],
        ]);

        $this->command->info('✅ CreditScoreLogSeeder berhasil dijalankan!');
    }
}
