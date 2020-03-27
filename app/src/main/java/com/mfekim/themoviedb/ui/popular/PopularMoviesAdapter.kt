package com.mfekim.themoviedb.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mfekim.themoviedb.R
import com.mfekim.themoviedb.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class PopularMoviesAdapter(private var items: List<Movie>) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularMovieItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieItemViewHolder =
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
            .let { PopularMovieItemViewHolder(it) }

    override fun onBindViewHolder(holder: PopularMovieItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setData(newItems: List<Movie>) {
        items = newItems
        notifyDataSetChanged()
    }

    class PopularMovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.movie_title
        private val imgPoster = itemView.movie_poster

        fun bind(movie: Movie) {
            tvTitle.text = movie.originalTitle

            imgPoster.run {
                Picasso.with(context)
                    .load("http://image.tmdb.org/t/p/w92${movie.posterPath}")
                    .fit()
                    .centerCrop()
                    .into(this)
            }
        }
    }
}

