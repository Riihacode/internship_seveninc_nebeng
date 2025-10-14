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
        Schema::create('popular_destinations', function (Blueprint $table) {
            $table->id();

            // Judul atau nama destinasi populer
            $table->string('title');

            // Gambar destinasi (URL / path file)
            $table->string('destination_img');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('popular_destinations');
    }
};
