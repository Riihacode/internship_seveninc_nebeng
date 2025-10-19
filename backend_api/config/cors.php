<?php

return [

    'paths' => ['api/*', 'sanctum/csrf-cookie', 'login', 'logout'],

    'allowed_methods' => ['*'],

    // alamat frontend
    'allowed_origins' => [env('FRONTEND_URL'), 'http://localhost:5173'],

    'allowed_origins_patterns' => [],

    'allowed_headers' => ['*'],

    'exposed_headers' => [],

    'max_age' => 0,

    // true untuk Sanctum
    'supports_credentials' => true,
];
