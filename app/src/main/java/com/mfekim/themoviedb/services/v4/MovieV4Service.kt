package com.mfekim.themoviedb.services.v4

import com.mfekim.themoviedb.api.v4.MovieV4Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieV4Service {

    private val movieV4Api: MovieV4Api

    init {
        val client by lazy {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.request()
                        .newBuilder()
                        .addHeader(
                            "Authorization",
                            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxOGUwYWFmMjg2MjJhZTBiOTk2NDUxY2IxZDgxZDJhNSIsInN1YiI6IjVlN2M3YTIxZDg2MWFmMzEyYWI3YjE5YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.yyfctU3T_oLLPk2eXbCG0TbdpodPghk77cW9zo2G_kI"
                        )
                        .build()
                        .let { chain.proceed(it) }
                }
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        }

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/4/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        movieV4Api = retrofit.create(MovieV4Api::class.java)
    }

    suspend fun getPopularMovies() = withContext(Dispatchers.IO) { movieV4Api.getPopularMovies() }
}