package com.mfekim.themoviedb.api.v4

import com.mfekim.themoviedb.models.MovieApiResponse
import retrofit2.http.GET

interface MovieV4Api {

    @GET("discover/movie/")
    suspend fun getPopularMovies(): MovieApiResponse
}