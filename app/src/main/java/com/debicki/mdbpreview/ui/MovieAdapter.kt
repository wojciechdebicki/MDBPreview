package com.debicki.mdbpreview.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debicki.mdbpreview.common.ViewGroupExtensions.layoutInflater
import com.debicki.mdbpreview.databinding.MovieRowBinding
import com.debicki.mdbpreview.domain.Movie
import com.squareup.picasso.Picasso

private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
}

class MovieAdapter(private val onMovieClickListener: (Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    inner class MovieViewHolder(private val binding: MovieRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.root.setOnClickListener { onMovieClickListener.invoke(movie) }
            binding.title.text = movie.title
            binding.description.text = movie.plot
            binding.rating.text = movie.imdbRating
            Picasso.get().load(movie.poster).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieRowBinding.inflate(parent.layoutInflater())
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}