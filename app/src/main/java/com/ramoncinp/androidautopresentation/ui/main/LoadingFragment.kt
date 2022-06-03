package com.ramoncinp.androidautopresentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ramoncinp.androidautopresentation.databinding.FragmentLoadingBinding
import com.ramoncinp.androidautopresentation.domain.states.MainAppState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoadingFragment : Fragment() {

    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCollectors()
    }

    private fun initCollectors() {
        viewModel.mainAppState.observe(viewLifecycleOwner) { onMainAppState(it) }
    }

    private fun onMainAppState(mainAppState: MainAppState) {
        when (mainAppState) {
            is MainAppState.HasName -> navigateToCheckPage()
            else -> navigateToTypeNamePage()
        }
    }

    private fun navigateToTypeNamePage() {
        findNavController().navigate(
            LoadingFragmentDirections.actionLoadingFragmentToInputNameFragment()
        )
    }

    private fun navigateToCheckPage() {
        findNavController().navigate(
            LoadingFragmentDirections.actionLoadingFragmentToCheckFragment()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
