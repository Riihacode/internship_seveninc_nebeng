<?php

namespace App\Http\Resources;

use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;

class UserResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'id'                     => $this->id,
            'name'                   => $this->name,
            'username'               => $this->username,
            'email'                  => $this->email,
            'user_type'              => $this->user_type,
            'banned'                 => (bool) $this->banned,
            'created_at'             => $this->created_at,
            'customer' => $this->when(
                $this->user_type === 'customer',
                fn() => [

                'full_name'          => $this->customer->full_name,
                'full_address'       => $this->customer->full_address,
                'verified'           => (bool) $this->customer->verified,
                'telephone'          => $this->customer->telephone,
                'created_at'         => $this->customer->created_at,
            ]
            ) ,
            'driver' => $this->when(
                $this->user_type === 'driver',
                fn() => [

                'full_name'          => $this->driver->full_name,
                'full_address'       => $this->driver->full_address,
                'verified'           => (bool) $this->driver->verified,
                'telephone'          => $this->driver->telephone,
                'created_at'         => $this->driver->created_at,
            ]
            ) ,
        ];
    }
}
