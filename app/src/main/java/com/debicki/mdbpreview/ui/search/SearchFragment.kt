package com.debicki.mdbpreview.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    private val adapter = MovieAdapter()

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

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Init -> binding.progress.visibility = View.GONE
                is State.Progress -> binding.progress.visibility = View.VISIBLE
                is State.Fetched -> {
                    binding.progress.visibility = View.GONE
                    adapter.submitList(it.movies)
                }
            }
        }
    }
}