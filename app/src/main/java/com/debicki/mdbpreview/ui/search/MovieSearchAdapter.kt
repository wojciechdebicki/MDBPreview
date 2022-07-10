package com.debicki.mdbpreview.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debicki.mdbpreview.common.cancelRequest
import com.debicki.mdbpreview.common.layoutInflater
import com.debicki.mdbpreview.common.load
import com.debicki.mdbpreview.databinding.MovieSearchRowBinding
import com.debicki.mdbpreview.domain.MovieDescription

private class MovieSearchDiffCallback : DiffUtil.ItemCallback<MovieDescription>() {
    override fun areItemsTheSame(oldItem: MovieDescription, newItem: MovieDescription): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieDescription, newItem: MovieDescription): Boolean =
        oldItem == newItem
}

class MovieSearchAdapter(private val onMovieClickListener: (MovieDescription) -> Unit) :
    ListAdapter<MovieDescription, MovieSearchAdapter.MovieSearchViewHolder>(MovieSearchDiffCallback()) {

    inner class MovieSearchViewHolder(private val binding: MovieSearchRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieDescription) {
            binding.apply {
                root.setOnClickListener { onMovieClickListener.invoke(movie) }
                title.text = movie.title
                year.text = movie.year
                image.load(movie.poster)
            }
        }

        fun unbind() {
            binding.image.cancelRequest()
            binding.root.setOnClickListener(null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val binding = MovieSearchRowBinding.inflate(parent.layoutInflater(), parent, false)
        return MovieSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: MovieSearchViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }
}