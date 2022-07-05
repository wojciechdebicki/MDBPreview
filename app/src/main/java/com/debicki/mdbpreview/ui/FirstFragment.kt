package com.debicki.mdbpreview.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.debicki.mdbpreview.MovieAdapter
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {
    private val binding by viewBinding(FragmentFirstBinding::bind)
    private val viewModel: FirstViewModel by viewModels()
    private val adapter = MovieAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movies.layoutManager = LinearLayoutManager(requireContext())
        binding.movies.adapter = adapter

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Progress -> binding.progress.visibility = View.VISIBLE
                is State.Fetched -> {
                    binding.progress.visibility = View.GONE
                    adapter.submitList(it.movies)
                }
            }
        }

        viewModel.fetch()
    }
}