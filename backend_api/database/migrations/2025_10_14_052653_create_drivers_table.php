<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('drivers', function (Blueprint $table) {
            $table->id();

            $table->foreignId('user_id')
                ->constrained(
                    table: 'users',
                    indexName: 'drivers_user_id_fk'
                )
                ->cascadeOnUpdate()
                ->cascadeOnDelete();

            $table->string('full_name');
            $table->string('telephone');
            $table->string('full_address');
            $table->string('profile_img')->nullable();      // ini wajib diganti karena tidak boleh null
            $table->integer('balance')->default(0);
            $table->integer('credit_score')->default(0);
            $table->integer('total_rating')->default(0);
            $table->integer('rating_count')->default(0);
            $table->float('average_rating')->default(0);

            // ====== ID CARD ======
            $table->string('face_img')->nullable();
            $table->string('face_with_id_img')->nullable();
            $table->string('id_card_img')->nullable();
            $table->string('id_card_number')->nullable();
            $table->string('id_card_fullname')->nullable();
            $table->string('id_card_birthdate')->nullable();
            $table->boolean('id_card_verified')->nullable();
            $table->string('id_card_rejected_reason')->nullable();

            // ====== DRIVER LICENSE ======
            $table->string('driver_license_number')->nullable();
            $table->string('driver_license_type')->nullable();
            $table->string('driver_license_expired')->nullable();
            $table->string('driver_license_img')->nullable();
            $table->boolean('driver_license_verified')->nullable();
            $table->string('driver_license_rejected_reason')->nullable();

            // ====== POLICE CLEARANCE CERTIFICATE (SKCK) ======
            $table->string('police_clearance_certificate_number')->nullable();
            $table->string('police_clearance_certificate_fullname')->nullable();
            $table->string('police_clearance_certificate_expired')->nullable();
            $table->string('police_clearance_certificate_img')->nullable();
            $table->boolean('police_clearance_verified')->nullable();
            $table->string('police_clearance_rejected_reason')->nullable();

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('drivers');
    }
};
