package com.example.nebeng.core.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
//    private const val BASE_URL = "http://127.0.0.1:8000/"
//    private const val BASE_URL = "http://0.0.0.0:8000/"

//    private const val BASE_URL = "http://192.168.1.10:8000/"    // only this that worked out
//    private const val BASE_URL = "http://10.200.21.117:8000/"
//private const val BASE_URL = "http://10.208.49.51:8000/"
    private const val BASE_URL = "http://192.168.1.14:8000/"
//    private const val BASE_URL = "http://10.154.151.51:8000/"
    // BASE URL untuk wifi public seven inc (harus pakai hotspot smartphone dan php artisan serve ke ip local 0.0.0.0 agar bisa diakses API-Nya (aga beda emang))
//    private const val BASE_URL = "http://172.26.218.51:8000/"

//    private const val BASE_URL = "http://10.200.21.117:8000/"
//    private const val BASE_URL = "http://10.87.45.51:8000/"
//    private const val BASE_URL = "http://10.122.43.51:8000/"
//    private const val BASE_URL = "http://10.141.197.51:8000/"
//    private const val BASE_URL = "http://10.72.126.51:8000/"

//    private const val BASE_URL = "http://10.32.194.51:8000/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    private val okHttpClient by lazy {
//        OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            // bisa tambahkan interceptor tanpa ubah file ini
//            .build()
//    }
//
//    fun createRetrofit(): Retrofit = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
}