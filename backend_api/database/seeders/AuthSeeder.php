<?php

namespace Database\Seeders;

use App\Models\User;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\Hash;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class AuthSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        $users = [
            [
                'name' => 'Super Admin',
                'username' => 'superadmin',
                'email' => 'superadmin@example.com',
                'user_type' => 'superadmin',
                'password' => Hash::make('password123'), // 🔐 default password
            ],
            [
                'name' => 'Admin Utama',
                'username' => 'admin1',
                'email' => 'admin1@example.com',
                'user_type' => 'admin',
                'password' => Hash::make('password123'),
            ],
            [
                'name' => 'Driver Budi',
                'username' => 'driver1',
                'email' => 'driver1@example.com',
                'user_type' => 'driver',
                'password' => Hash::make('password123'),
            ],
            [
                'name' => 'Customer Nisa',
                'username' => 'customer1',
                'email' => 'customer1@example.com',
                'user_type' => 'customer',
                'password' => Hash::make('password123'),
            ],
            [
                'name' => 'Terminal Jogja',
                'username' => 'terminal1',
                'email' => 'terminal1@example.com',
                'user_type' => 'terminal',
                'password' => Hash::make('password123'),
            ],
        ];

        foreach ($users as $userData) {
            User::updateOrCreate(
                ['email' => $userData['email']],
                [
                    'name'      => $userData['name'],
                    'username'  => $userData['username'],
                    'email'     => $userData['email'],
                    'user_type' => $userData['user_type'],
                    'password'  => $userData['password'],
                    'banned'    => false,
                ]
            );
        }

        $this->command->info('✅ UserSeeder berhasil dijalankan! Akun default untuk Auth siap digunakan.');
    }
}
