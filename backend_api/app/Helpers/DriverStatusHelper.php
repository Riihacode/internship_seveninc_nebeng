<?php

namespace App\Helpers;

class DriverStatusHelper{

    public static function getStatus($driver){
        $idCard = $driver->id_card_verified;
        $sim = $driver->driver_licence_verified;
        $skck = $driver->police_certificate_verified;

        $docs = [$idCard, $sim, $skck];

        $countNull = collect($docs)->filter(fn($v) => is_null($v))->count();
        $countTrue = collect($docs)->filter(fn($v) => $v === true)->count();
        $countFalse = collect($docs)->filter(fn($v) => $v === false)->count();

        if ($countNull === 3){
            return 'pending';
        }

        if($countNull > 0){
            return 'pengajuan';
        }

        if($countFalse === 3){
            return 'ditolak';
        }

        if($countTrue > 0 && $countFalse > 0){
            return 'diterima_sebagian';
        }

        if($countTrue === 3){
            return 'terverifikasi';
        }

        return 'pengajuan';
    }
}
