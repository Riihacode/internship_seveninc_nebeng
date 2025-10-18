<?php

namespace Database\Seeders;

use App\Models\District;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DistrictSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        District::query()->delete();

        District::query()->insert([
            // Kota Yogyakarta (Regency ID 1)
            ['id' => 1, 'regency_id' => 1, 'name' => 'Umbulharjo'],
            ['id' => 2, 'regency_id' => 1, 'name' => 'Gondokusuman'],
            ['id' => 3, 'regency_id' => 1, 'name' => 'Kotagede'],

            // Kabupaten Sleman (Regency ID 2)
            ['id' => 4, 'regency_id' => 2, 'name' => 'Depok'],
            ['id' => 5, 'regency_id' => 2, 'name' => 'Mlati'],

            // Kabupaten Bantul (Regency ID 3)
            ['id' => 6, 'regency_id' => 3, 'name' => 'Bantul'],
            ['id' => 7, 'regency_id' => 3, 'name' => 'Sewon'],

            // Kabupaten Magelang (Regency ID 4)
            ['id' => 8, 'regency_id' => 4, 'name' => 'Mungkid'],
            ['id' => 9, 'regency_id' => 4, 'name' => 'Muntilan'],

            // Kabupaten Klaten (Regency ID 5)
            ['id' => 10, 'regency_id' => 5, 'name' => 'Klaten Tengah'],
            ['id' => 11, 'regency_id' => 5, 'name' => 'Jogonalan'],
        ]);
    }
}
