package com.ramoncinp.androidautopresentation.ui.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ramoncinp.androidautopresentation.databinding.FragmentInputNameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputNameFragment : Fragment() {

    private var _binding: FragmentInputNameBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InputNameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputNameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.setNameEt.addTextChangedListener {
            viewModel.onInputNameChanged(it.toString())
        }

        binding.submitButton.setOnClickListener {
            viewModel.submitName()
        }
    }

    private fun initObservers() {
        viewModel.canSubmitName.observe(viewLifecycleOwner) {
            binding.submitButton.isEnabled = it
        }

        viewModel.inputNameReady.observe(viewLifecycleOwner) { navigate ->
            if (navigate) onInputNameReady()
        }
    }

    private fun onInputNameReady() {
        findNavController().navigate(
            InputNameFragmentDirections.actionInputNameFragmentToLoadingFragment()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
