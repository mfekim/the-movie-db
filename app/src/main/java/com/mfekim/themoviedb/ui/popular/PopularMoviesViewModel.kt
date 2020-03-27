package com.mfekim.themoviedb.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mfekim.themoviedb.models.MovieApiResponse
import com.mfekim.themoviedb.services.v4.MovieV4Service

class PopularMoviesViewModel : ViewModel() {

    private val _popularMovies by lazy {
        liveData {
            try {
                emit(Result.success(fetchPopularMovies()))
            } catch (ioException: Exception) {
                emit(Result.failure(ioException))
            }
        }
    }

    val popularMovies: LiveData<Result<MovieApiResponse>>
        get() = _popularMovies

    private val movieV4Service by lazy { MovieV4Service() }

    private suspend fun fetchPopularMovies() = movieV4Service.getPopularMovies()
}

