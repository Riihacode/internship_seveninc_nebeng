<?php

namespace Database\Seeders;

use App\Models\Regency;
use App\Models\District;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class DistrictSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // District::query()->delete();

        // District::query()->insert([
        //     // Kota Yogyakarta (Regency ID 1)
        //     ['id' => 1, 'regency_id' => 1, 'name' => 'Umbulharjo'],
        //     ['id' => 2, 'regency_id' => 1, 'name' => 'Gondokusuman'],
        //     ['id' => 3, 'regency_id' => 1, 'name' => 'Kotagede'],

        //     // Kabupaten Sleman (Regency ID 2)
        //     ['id' => 4, 'regency_id' => 2, 'name' => 'Depok'],
        //     ['id' => 5, 'regency_id' => 2, 'name' => 'Mlati'],

        //     // Kabupaten Bantul (Regency ID 3)
        //     ['id' => 6, 'regency_id' => 3, 'name' => 'Bantul'],
        //     ['id' => 7, 'regency_id' => 3, 'name' => 'Sewon'],

        //     // Kabupaten Magelang (Regency ID 4)
        //     ['id' => 8, 'regency_id' => 4, 'name' => 'Mungkid'],
        //     ['id' => 9, 'regency_id' => 4, 'name' => 'Muntilan'],

        //     // Kabupaten Klaten (Regency ID 5)
        //     ['id' => 10, 'regency_id' => 5, 'name' => 'Klaten Tengah'],
        //     ['id' => 11, 'regency_id' => 5, 'name' => 'Jogonalan'],
        // ]);

        $regencies = Regency::whereIn('name', [
            'Kota Yogyakarta',
            'Kabupaten Sleman',
            'Kabupaten Bantul',
            'Kabupaten Kulon Progo',
            'Kabupaten Gunungkidul',
        ])->get()->keyBy('name');

        if ($regencies->isEmpty()) {
            $this->command->warn('⚠️ Regency belum tersedia. Jalankan RegencySeeder dulu.');
            return;
        }

        $districts = [
            // Kota Yogyakarta
            'Kota Yogyakarta' => [
                'Gedongtengen', 'Jetis', 'Tegalrejo', 'Gondokusuman', 'Umbulharjo', 'Mergangsan', 'Danurejan', 'Pakualaman', 'Gondomanan', 'Ngampilan', 'Wirobrajan', 'Mantrijeron', 'Kraton',
            ],

            // Kabupaten Sleman
            'Kabupaten Sleman' => [
                'Depok', 'Mlati', 'Ngaglik', 'Gamping', 'Godean', 'Sleman', 'Kalasan', 'Berbah', 'Prambanan', 'Ngemplak', 'Tempel', 'Turi', 'Pakem', 'Cangkringan',
            ],

            // Kabupaten Bantul
            'Kabupaten Bantul' => [
                'Bantul', 'Jetis', 'Imogiri', 'Kretek', 'Pundong', 'Pandak', 'Sanden', 'Sedayu', 'Sewon', 'Kasihan', 'Banguntapan', 'Piyungan', 'Dlingo', 'Pleret',
            ],

            // Kabupaten Kulon Progo
            'Kabupaten Kulon Progo' => [
                'Wates', 'Temon', 'Sentolo', 'Pengasih', 'Kokap', 'Nanggulan', 'Kalibawang', 'Girimulyo', 'Lendah', 'Samigaluh',
            ],

            // Kabupaten Gunungkidul
            'Kabupaten Gunungkidul' => [
                'Wonosari', 'Karangmojo', 'Semin', 'Ponjong', 'Rongkop', 'Tepus', 'Tanjungsari', 'Purwosari', 'Paliyan', 'Saptosari', 'Playen', 'Patuk', 'Nglipar', 'Ngawen', 'Gedangsari',
            ],
        ];

        foreach ($districts as $regencyName => $names) {
            $regency = $regencies->get($regencyName);
            if (!$regency) continue;

            foreach ($names as $districtName) {
                District::updateOrCreate(
                    ['name' => $districtName, 'regency_id' => $regency->id],
                    ['regency_id' => $regency->id]
                );
            }
        }

        $this->command->info('✅ DistrictSeeder berhasil dijalankan! Semua kecamatan di DI Yogyakarta berhasil dimasukkan.');
    }
}
