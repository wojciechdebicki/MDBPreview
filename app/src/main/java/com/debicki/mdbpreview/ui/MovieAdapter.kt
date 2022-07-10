package com.debicki.mdbpreview.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.cancelRequest
import com.debicki.mdbpreview.common.layoutInflater
import com.debicki.mdbpreview.common.load
import com.debicki.mdbpreview.databinding.MovieRowBinding
import com.debicki.mdbpreview.domain.Movie

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
            binding.apply {
                root.setOnClickListener { onMovieClickListener.invoke(movie) }
                title.text = movie.title
                description.text = movie.plot
                rating.text = itemView.resources.getString(R.string.rating_format, movie.imdbRating)
                image.load(movie.poster)
            }
        }

        fun unbind() {
            binding.image.cancelRequest()
            binding.root.setOnClickListener(null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieRowBinding.inflate(parent.layoutInflater(), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: MovieViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }
}