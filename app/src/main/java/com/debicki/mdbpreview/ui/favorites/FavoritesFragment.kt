package com.debicki.mdbpreview.ui.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is FavoriteState.Fetched -> Toast.makeText(requireContext(), "Fetched " + it.movies.size, Toast.LENGTH_SHORT)
                    .show()
                is FavoriteState.Init -> Toast.makeText(requireContext(), "Init ", Toast.LENGTH_SHORT)
                    .show()
                is FavoriteState.Progress -> {}
            }
        }

        viewModel.fetchData()
    }
}