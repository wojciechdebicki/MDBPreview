package com.debicki.mdbpreview.ui.favorites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.debicki.mdbpreview.R
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO by navArgs()?
        val movieId = arguments?.getString("movieId") ?: ""

        Toast.makeText(requireContext(), "Passed " + movieId, Toast.LENGTH_SHORT).show()

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_FavoritesFragment_to_SearchFragment)
        }
    }
}