package com.debicki.mdbpreview.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.debicki.mdbpreview.common.ViewGroupExtensions.layoutInflater
import com.debicki.mdbpreview.databinding.MovieRowBinding
import com.debicki.mdbpreview.databinding.MovieSearchRowBinding
import com.debicki.mdbpreview.domain.MovieDescription
import com.squareup.picasso.Picasso

private class MovieSearchDiffCallback : DiffUtil.ItemCallback<MovieDescription>() {
    override fun areItemsTheSame(oldItem: MovieDescription, newItem: MovieDescription): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieDescription, newItem: MovieDescription): Boolean =
        oldItem == newItem
}

class MovieSearchAdapter(private val onMovieClickListener: (MovieDescription) -> Unit) :
    ListAdapter<MovieDescription, MovieSearchAdapter.MovieSearchViewHolder>(MovieSearchDiffCallback()) {

    inner class MovieSearchViewHolder(private val binding: MovieSearchRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieDescription) {
            binding.root.setOnClickListener { onMovieClickListener.invoke(movie) }
            binding.title.text = movie.title
            binding.year.text = movie.year
            Picasso.get().load(movie.poster).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val binding = MovieSearchRowBinding.inflate(parent.layoutInflater())
        return MovieSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}