<?php

namespace Database\Seeders;

use Carbon\Carbon;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class VehicleSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // Pastikan sudah ada driver di tabel drivers
        $drivers = DB::table('drivers')->pluck('id')->toArray();

        if (empty($drivers)) {
            $this->command->warn('⚠️  Tidak ada data driver di tabel drivers. Jalankan DriverSeeder terlebih dahulu.');
            return;
        }

        // Ambil waktu sekarang untuk kolom created_at / updated_at
        $now = Carbon::now();

        DB::table('vehicles')->insert([
            [
                'driver_id' => $drivers[0],
                'registration_number' => 'AB1234CD',
                'registration_year' => 2022,
                'registration_expired' => Carbon::now()->addYear(),
                'registration_img' => 'vehicle_docs/ab1234cd_stnk.jpg',
                'vehicle_name' => 'Honda Beat Street',
                'vehicle_color' => 'Hitam',
                'vehicle_type' => 'Motor',
                'vehicle_img' => 'vehicles/beat_street_black.jpg',
                'vehicle_verified' => true,
                'vehicle_rejected_reason' => null,
                'created_at' => $now,
                'updated_at' => $now,
            ]
        ]);

         $this->command->info('✅ VehicleSeeder berhasil dijalankan! Data vehicle berhasil dimasukkan.');
    }
}
