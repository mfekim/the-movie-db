package com.mfekim.themoviedb.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mfekim.themoviedb.R
import com.mfekim.themoviedb.models.MovieApiResponse
import kotlinx.android.synthetic.main.fragment_popular_movies.fragment_popular_movies_recycler_view as recyclerView

class PopularMoviesFragment : Fragment() {

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()

    private lateinit var adapter: PopularMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_popular_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularMoviesViewModel.popularMovies
            .observe(viewLifecycleOwner, Observer {
                updateUi(it)
            })
    }

    private fun updateUi(apiResponse: Result<MovieApiResponse>) {
        try {
            apiResponse.getOrThrow().also {
                if (::adapter.isInitialized) {
                    adapter.setData(it.results)
                } else {
                    adapter = PopularMoviesAdapter(it.results)
                    recyclerView.adapter = adapter
                }
            }
        } catch (e: Exception) {
            // TODO
        }
    }
}
