<?php

namespace Database\Seeders;

use App\Models\Regency;
use App\Models\Province;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class RegencySeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // Regency::query()->delete();

        // Regency::query()->insert([
        //     [
        //         'id'            => 1,
        //         'province_id'   => 1,
        //         'name'          => 'Kota Yogyakarta',
        //     ],[
        //         'id'            => 2,
        //         'province_id'   => 1,
        //         'name'          => 'Kabupaten Sleman',
        //     ],
        //     [
        //         'id' => 3,
        //         'province_id' => 1,
        //         'name' => 'Kabupaten Bantul',
        //     ],

        //     // Jawa Tengah Province
        //     [
        //         'id' => 4,
        //         'province_id' => 2,
        //         'name' => 'Kabupaten Magelang',
        //     ],
        //     [
        //         'id' => 5,
        //         'province_id' => 2,
        //         'name' => 'Kabupaten Klaten',
        //     ],
        // ]);
        // Ambil provinsi yang akan digunakan
        $province = Province::where('name', 'DI Yogyakarta')->first();

        if (!$province) {
            $this->command->warn('⚠️ Province "DI Yogyakarta" belum ada. Jalankan ProvinceSeeder dulu.');
            return;
        }

        $regencies = [
            'Kota Yogyakarta',
            'Kabupaten Sleman',
            'Kabupaten Bantul',
            'Kabupaten Kulon Progo',
            'Kabupaten Gunungkidul',
        ];

        foreach ($regencies as $name) {
            Regency::updateOrCreate(
                ['name' => $name, 'province_id' => $province->id],
                ['province_id' => $province->id]
            );
        }

        $this->command->info('✅ RegencySeeder berhasil dijalankan! Data kabupaten/kota DI Yogyakarta berhasil dimasukkan.');
    }
}
