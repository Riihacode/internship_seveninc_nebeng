<?php

namespace Database\Seeders;

use App\Models\Province;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class ProvinceSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // Province::truncate();
        // Province::query()->delete();

        // Province::query()->insert([
        //     [
        //         'id'    => 1,
        //         'name'  => 'Daerah Istimewa Yogyakarta'
        //     ],
        //     [
        //         'id'    => 2,
        //         'name'  => 'Jawa Tengah'
        //     ],
        // ]);
        $provinces = [
            'Aceh',
            'Sumatera Utara',
            'Sumatera Barat',
            'Riau',
            'Kepulauan Riau',
            'Jambi',
            'Sumatera Selatan',
            'Bangka Belitung',
            'Bengkulu',
            'Lampung',
            'DKI Jakarta',
            'Jawa Barat',
            'Banten',
            'Jawa Tengah',
            'DI Yogyakarta',
            'Jawa Timur',
            'Bali',
            'Nusa Tenggara Barat',
            'Nusa Tenggara Timur',
            'Kalimantan Barat',
            'Kalimantan Tengah',
            'Kalimantan Selatan',
            'Kalimantan Timur',
            'Kalimantan Utara',
            'Sulawesi Utara',
            'Gorontalo',
            'Sulawesi Tengah',
            'Sulawesi Barat',
            'Sulawesi Selatan',
            'Sulawesi Tenggara',
            'Maluku',
            'Maluku Utara',
            'Papua',
            'Papua Barat',
            'Papua Barat Daya',
            'Papua Tengah',
            'Papua Pegunungan',
            'Papua Selatan',
        ];

        foreach ($provinces as $provinceName) {
            Province::updateOrCreate(['name' => $provinceName]);
        }

        $this->command->info('✅ ProvinceSeeder berhasil dijalankan! Semua provinsi Indonesia telah dimasukkan.');
    }
}
