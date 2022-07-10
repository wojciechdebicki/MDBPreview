package com.debicki.mdbpreview.ui.search

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.ViewExtensions.hideKeyboard
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()
    private val adapter = MovieSearchAdapter {
        viewModel.onMovieClicked(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movies.layoutManager = LinearLayoutManager(requireContext())
        binding.movies.adapter = adapter

        binding.search.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                binding.search.text?.let {
                    viewModel.onSearch(it.toString())
                }
                binding.search.hideKeyboard()
                return@setOnEditorActionListener true
            }

            false
        }

        binding.clearNotInterested.setOnClickListener {
            viewModel.clearNotInterested()
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Init -> {
                    binding.progress.visibility = GONE
                    binding.clearNotInterested.visibility = VISIBLE
                    binding.movies.visibility = GONE
                }
                is State.Progress -> {
                    binding.progress.visibility = VISIBLE
                    binding.clearNotInterested.visibility = GONE
                    binding.movies.visibility = GONE
                }
                is State.Fetched -> {
                    binding.progress.visibility = GONE
                    binding.clearNotInterested.visibility = GONE
                    adapter.submitList(it.movies)
                    binding.movies.visibility = VISIBLE
                }
                is State.Error -> {
                    binding.progress.visibility = GONE
                    binding.clearNotInterested.visibility = GONE
                    binding.movies.visibility = GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.effect.observe(viewLifecycleOwner) {
            when (it) {
                is Effect.OpenDetailsPage -> {
                    val directions = SearchFragmentDirections.toDetailFragment(it.movie.imdbID)
                    findNavController().navigate(directions)
                }
            }
        }
    }
}