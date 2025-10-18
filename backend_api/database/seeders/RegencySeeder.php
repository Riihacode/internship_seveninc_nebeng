<?php

namespace Database\Seeders;

use App\Models\Regency;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class RegencySeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        Regency::query()->delete();

        Regency::query()->insert([
            [
                'id'            => 1,
                'province_id'   => 1,
                'name'          => 'Kota Yogyakarta',
            ],[
                'id'            => 2,
                'province_id'   => 1,
                'name'          => 'Kabupaten Sleman',
            ],
            [
                'id' => 3,
                'province_id' => 1,
                'name' => 'Kabupaten Bantul',
            ],

            // Jawa Tengah Province
            [
                'id' => 4,
                'province_id' => 2,
                'name' => 'Kabupaten Magelang',
            ],
            [
                'id' => 5,
                'province_id' => 2,
                'name' => 'Kabupaten Klaten',
            ],
        ]);
    }
}
