<?php

namespace Database\Seeders;

use App\Models\PaymentMethod;
use Illuminate\Database\Seeder;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;

class PaymentMethodSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        //
        $methods = [
            [
                'bank_name'      => 'Bank BCA',
                'account_name'   => 'PT Nebeng Indonesia',
                'account_number' => '1234567890',
            ],
            [
                'bank_name'      => 'Bank BRI',
                'account_name'   => 'CV Mitra Transportasi',
                'account_number' => '0023445566',
            ],
            [
                'bank_name'      => 'Bank Mandiri',
                'account_name'   => 'PT Layanan Digital Nusantara',
                'account_number' => '9876543210',
            ],
            [
                'bank_name'      => 'GoPay',
                'account_name'   => 'Nebeng Digital Wallet',
                'account_number' => '081234567890',
            ],
            [
                'bank_name'      => 'OVO',
                'account_name'   => 'Nebeng OVO Official',
                'account_number' => '081298765432',
            ],
            [
                'bank_name'      => 'DANA',
                'account_name'   => 'Nebeng Cashless',
                'account_number' => '081223344556',
            ],
            [
                'bank_name'      => 'ShopeePay',
                'account_name'   => 'Nebeng Shopee Wallet',
                'account_number' => '081377889900',
            ],
        ];

        foreach ($methods as $method) {
            PaymentMethod::updateOrCreate(
                ['account_number' => $method['account_number']],
                $method
            );
        }

        $this->command->info('✅ PaymentMethodSeeder berhasil dijalankan! Metode pembayaran default telah dibuat.');
    }
}
