package com.debicki.mdbpreview.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO by navArgs()?
        val movieId = arguments?.getString("movieId") ?: ""

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Fetched -> Toast.makeText(
                    requireContext(),
                    it.movie.title + " " + it.isFavorite,
                    Toast.LENGTH_SHORT
                ).show()
                is State.Init -> {
                }
                is State.Progress -> {
                }
            }
        }

        viewModel.fetchData(movieId)

        binding.buttonSecond.setOnClickListener {
            viewModel.toggle()
        }
    }
}