<?php

namespace Database\Seeders;

use App\Models\Terminal;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class TerminalSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // Pastikan foreign key ke lokasi administratif sudah tersedia
        $province = DB::table('provinces')->first();
        $regency  = DB::table('regencies')->first();
        $district = DB::table('districts')->first();

        if (!$province || !$regency || !$district) {
            $this->command->warn('⚠️ Data provinsi, kabupaten, atau kecamatan belum ada. Jalankan ProvinceSeeder, RegencySeeder, dan DistrictSeeder dulu.');
            return;
        }

        $terminals = [
            [
                'name' => 'Terminal Giwangan',
                'terminal_type' => 'Public',
                'public_terminal_subtype' => 'Terminal Bis',
                'province_id' => $province->id,
                'regency_id' => $regency->id,
                'district_id' => $district->id,
                'full_address' => 'Jl. Imogiri Timur No.1, Giwangan, Umbulharjo, Yogyakarta',
                'longitude' => '-7.8334',
                'latitude'  => '110.3932',
            ],
            [
                'name' => 'Stasiun Tugu',
                'terminal_type' => 'Public',
                'public_terminal_subtype' => 'Stasiun Kereta',
                'province_id' => $province->id,
                'regency_id' => $regency->id,
                'district_id' => $district->id,
                'full_address' => 'Jl. Mangkubumi No.1, Sosromenduran, Gedongtengen, Yogyakarta',
                'longitude' => '-7.7891',
                'latitude'  => '110.3635',
            ],
            [
                'name' => 'Bandara Yogyakarta International Airport (YIA)',
                'terminal_type' => 'Public',
                'public_terminal_subtype' => 'Bandara',
                'province_id' => $province->id,
                'regency_id' => $regency->id,
                'district_id' => $district->id,
                'full_address' => 'Jl. Wates–Purworejo, Temon, Kulon Progo, Yogyakarta',
                'longitude' => '-7.9052',
                'latitude'  => '110.0564',
            ],
            [
                'name' => 'Pelabuhan Tanjung Adikarto',
                'terminal_type' => 'Public',
                'public_terminal_subtype' => 'Pelabuhan',
                'province_id' => $province->id,
                'regency_id' => $regency->id,
                'district_id' => $district->id,
                'full_address' => 'Pantai Glagah, Kulon Progo, Yogyakarta',
                'longitude' => '-7.9270',
                'latitude'  => '110.0612',
            ],
            [
                'name' => 'Garasi Logistik PT Maju Jaya',
                'terminal_type' => 'Private',
                'public_terminal_subtype' => null,
                'province_id' => $province->id,
                'regency_id' => $regency->id,
                'district_id' => $district->id,
                'full_address' => 'Jl. Kaliurang Km 8, Sleman, Yogyakarta',
                'longitude' => '-7.7261',
                'latitude'  => '110.3779',
            ],
        ];

        foreach ($terminals as $terminal) {
            Terminal::updateOrCreate(
                ['name' => $terminal['name']],
                $terminal
            );
        }

        $this->command->info('✅ TerminalSeeder berhasil dijalankan! Data terminal publik & privat berhasil dimasukkan.');
    }
}
