package com.guide.currencyconverter.Retrofit

import com.guide.currencyconverter.Common.Variables
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

object CacheOkHttpClient {
    val CACHE_SIZE = 1 * 1024 * 1024.toLong() // 1 MB

    val cacheDir =
        File(System.getProperty("java.io.tmpdir"), "cache_file")

    val cache = Cache(cacheDir, CACHE_SIZE)

    val client: OkHttpClient
    get() = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (Variables.isNetworkConnected)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 60 * 60 * 4 * 1).build()
            else
                request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            chain.proceed(request)
        }
        .build()
}