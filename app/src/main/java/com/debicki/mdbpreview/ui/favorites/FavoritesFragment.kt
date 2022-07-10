package com.debicki.mdbpreview.ui.favorites

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentFavoritesBinding
import com.debicki.mdbpreview.ui.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesViewModel by viewModels()

    private val adapter = MovieAdapter { viewModel.onMovieClicked(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movies.layoutManager = LinearLayoutManager(requireContext())
        binding.movies.adapter = adapter

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is FavoriteState.Fetched -> {
                    binding.progress.visibility = GONE
                    binding.empty.visibility = GONE
                    binding.movies.visibility = VISIBLE
                    adapter.submitList(it.movies)
                }
                is FavoriteState.Init -> {
                    binding.progress.visibility = GONE
                    binding.empty.visibility = GONE
                    binding.movies.visibility = GONE
                }
                is FavoriteState.Progress -> {
                    binding.progress.visibility = VISIBLE
                    binding.empty.visibility = GONE
                    binding.movies.visibility = GONE
                }
                is FavoriteState.EmptyList -> {
                    binding.movies.visibility = GONE
                    adapter.submitList(listOf())
                    binding.progress.visibility = GONE
                    binding.empty.visibility = VISIBLE
                }
            }
        }

        viewModel.effect.observe(viewLifecycleOwner) {
            when (it) {
                is FavoriteEffect.OpenDetailsPage -> {
                    val directions = FavoritesFragmentDirections.toDetailFragment(it.movie.imdbID)
                    findNavController().navigate(directions)
                }
            }
        }

        viewModel.fetchData()
    }
}