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
        Province::query()->delete();

        Province::query()->insert([
            [
                'id'    => 1,
                'name'  => 'Daerah Istimewa Yogyakarta'
            ],
            [
                'id'    => 2,
                'name'  => 'Jawa Tengah'
            ],
        ]);
    }
}
