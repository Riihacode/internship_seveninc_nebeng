<?php

namespace Database\Seeders;

use Carbon\Carbon;
use App\Models\User;
use App\Models\Customer;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class CustomerSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        // Ambil semua user yang memiliki role customer
        $users = User::where('user_type', 'customer')->get();

        if ($users->isEmpty()) {
            $this->command->warn('⚠️ CustomerSeeder dilewati: belum ada user dengan user_type = customer.');
            return;
        }

        $customers = [
            [
                'full_name'   => 'Rizky Maulana',
                'telephone'   => '081234111222',
                'full_address'=> 'Jl. Kaliurang KM 8, Sleman, Yogyakarta',
            ],
            [
                'full_name'   => 'Nisa Amalia',
                'telephone'   => '081278889900',
                'full_address'=> 'Jl. Wonosari No. 12, Bantul, Yogyakarta',
            ],
            [
                'full_name'   => 'Fajar Pratomo',
                'telephone'   => '081355667788',
                'full_address'=> 'Jl. Adisutjipto No. 55, Yogyakarta',
            ],
            [
                'full_name'   => 'Dian Sari',
                'telephone'   => '082112224455',
                'full_address'=> 'Jl. Gejayan No. 9, Sleman, Yogyakarta',
            ],
            [
                'full_name'   => 'Hendra Saputra',
                'telephone'   => '081399887766',
                'full_address'=> 'Jl. Magelang KM 4, Sleman, Yogyakarta',
            ],
        ];

        foreach ($customers as $index => $data) {
            $user = $users[$index % $users->count()]; // Rotasi user customer

            Customer::updateOrCreate(
                ['user_id' => $user->id],
                [
                    'user_id'        => $user->id,
                    'full_name'      => $data['full_name'],
                    'telephone'      => $data['telephone'],
                    'full_address'   => $data['full_address'],
                    'profile_img'    => null,
                    'verified'       => rand(0, 1),
                    'credit_amount'  => rand(0, 300000),

                    // Data ID Card
                    'id_card_number' => 'ID' . rand(10000000, 99999999),
                    'id_card_fullname' => $data['full_name'],
                    'id_card_birthdate' => Carbon::today()
                        ->subYears(rand(20, 50))
                        ->subDays(rand(0, 365))
                        ->toDateString(),
                    'id_card_img' => null,
                    'face_img' => null,
                    'face_with_id_img' => null,
                ]
            );
        }

        $this->command->info('✅ CustomerSeeder berhasil dijalankan!');
    }
}
