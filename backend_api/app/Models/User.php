<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

class User extends Authenticatable
{
    // =======================================================
    //                          DEFAULT
    // =======================================================
    
    // /** @use HasFactory<\Database\Factories\UserFactory> */
    // use HasFactory, Notifiable;

    // /**
    //  * The attributes that are mass assignable.
    //  *
    //  * @var list<string>
    //  */
    // protected $fillable = [
    //     'name',
    //     'email',
    //     'password',
    //     'phone_number',
    //     'user_type',
    //     'banned',
    // ];

    // /**
    //  * The attributes that should be hidden for serialization.
    //  *
    //  * @var list<string>
    //  */
    // protected $hidden = [
    //     'password',
    //     'remember_token',
    // ];

    // /**
    //  * Get the attributes that should be cast.
    //  *
    //  * @return array<string, string>
    //  */
    // protected function casts(): array
    // {
    //     return [
    //         'email_verified_at' => 'datetime',
    //         'password' => 'hashed',
    //     ];
    // }

    use HasFactory, Notifiable;

    protected $table = 'users';

    protected $fillable = [
        'name',
        'email',
        'password',
        'user_type',
        'banned',
    ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var list<string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    protected $casts = [
        'banned' => 'boolean',
    ];

    // Relationship
    // Relationship One To One
    public function administrators() {
        return $this->hasOne(Administrator::class, 'user_id');
    }

    public function customers() {
        return $this->hasOne(Customer::class, 'user_id');
    }

    public function drivers() {
        return $this->hasOne(Driver::class, 'user_id');
    }

    // Relationship One To Many
    public function notifications() {
        return $this->hasMany(Notification::class, 'user_id');
    }

    // HELPER
    public function getIsActiveAttribute(): bool {
        return !$this->banned;
    }

    public function isRole(string $role): bool {
        return strtolower($this->user_type) === strtolower($role);
    }   

    public function getRoleLabelAttribute(): string
    {
        return match ($this->user_type) {
            'Admin'    => '🛠️ Admin',
            'Finance'  => '💰 Finance',
            'Customer' => '🧍 Customer',
            'Driver'   => '🚗 Driver',
            default    => '👤 User',
        };
    }
}
