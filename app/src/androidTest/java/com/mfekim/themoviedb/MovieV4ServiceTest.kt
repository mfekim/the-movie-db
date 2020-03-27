package com.mfekim.themoviedb

import com.mfekim.themoviedb.models.Movie
import com.mfekim.themoviedb.models.MovieApiResponse
import com.mfekim.themoviedb.services.v4.MovieV4Service
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class MovieV4ServiceTest {
    /**
     * GET an instance of [MovieV4Service]
     * WHEN calling the get popular movies API
     * THEN the result contains at least
     *  - One page ([MovieApiResponse.page] >= 1)
     *  - One [Movie] with an [Movie.originalTitle] in the [MovieApiResponse.results] list
     */
    @Test
    fun getPopularMovies() {
        val movieV4Service = MovieV4Service()

        runBlocking {
            launch {
                val movieApiResponse = movieV4Service.getPopularMovies()

                movieApiResponse.also {
                    assertThat(it.page, greaterThanOrEqualTo(1))
                    assertThat(it.results[0].originalTitle, not(emptyOrNullString()))
                }
            }.join()
        }
    }
}