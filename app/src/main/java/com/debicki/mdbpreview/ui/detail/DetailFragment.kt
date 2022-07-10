package com.debicki.mdbpreview.ui.detail

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.ViewExtensions.toVisibility
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getString(MOVIE_ID_KEY) ?: ""

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Fetched -> {
                    Picasso.get().load(it.movie.poster).into(binding.poster)
                    binding.title.text = it.movie.title
                    binding.description.text = it.movie.plot
                    binding.favorite.isChecked = it.isFavorite
                    binding.rating.text = getString(R.string.rating_format, it.movie.imdbRating)
                    updateVisibility(true)
                }
                is State.Init -> {
                    updateVisibility(true)
                    binding.progress.visibility = GONE
                }
                is State.Progress -> {
                    updateVisibility(false)
                }
            }
        }

        binding.favorite.setOnCheckedChangeListener { _, checked ->
            viewModel.favoriteStateChanged(checked)
        }

        viewModel.fetchData(movieId)
    }

    private fun updateVisibility(dataReady: Boolean) {
        binding.title.visibility = dataReady.toVisibility()
        binding.poster.visibility = dataReady.toVisibility()
        binding.description.visibility = dataReady.toVisibility()
        binding.favorite.visibility = dataReady.toVisibility()
        binding.rating.visibility = dataReady.toVisibility()

        binding.progress.visibility = dataReady.not().toVisibility()
    }

    companion object {
        private const val MOVIE_ID_KEY = "movieId"
    }
}