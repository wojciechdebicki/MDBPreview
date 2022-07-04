package com.debicki.mdbpreview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.debicki.mdbpreview.common.viewBinding
import com.debicki.mdbpreview.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_first) {
    private val binding by viewBinding(FragmentFirstBinding::bind)
    private val viewModel: FirstViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is State.Init -> Toast.makeText(requireContext(), "Init " + it.value, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.testMe()
    }
}