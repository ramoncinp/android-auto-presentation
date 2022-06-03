package com.ramoncinp.androidautopresentation.ui.check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ramoncinp.androidautopresentation.R
import com.ramoncinp.androidautopresentation.databinding.FragmentCheckBinding
import com.ramoncinp.androidautopresentation.domain.states.CheckInState
import com.ramoncinp.androidautopresentation.domain.states.CheckState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckFragment : Fragment() {

    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CheckViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.checkButton.setOnClickListener {
            viewModel.check()
        }
    }

    private fun initObservers() {
        viewModel.userName.observe(viewLifecycleOwner) {
            setName(it)
        }

        viewModel.checkState.observe(viewLifecycleOwner) {
            when (it) {
                is CheckState.Loading -> onLoading()
                is CheckState.CheckedIn -> onCheckedIn()
                is CheckState.CheckedOut -> onCheckedOut()
            }
        }

        viewModel.checkInState.observe(viewLifecycleOwner) {
            when (it) {
                is CheckInState.Loading -> onLoading()
                is CheckInState.Success -> onCheckSuccess()
                is CheckInState.Error -> onCheckError(it.message)
            }
        }
    }

    private fun onLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.checkButton.visibility = View.GONE
    }

    private fun onCheckSuccess() {
        viewModel.getCheckState()
    }

    private fun onCheckError(error: String) {
        binding.progressBar.visibility = View.GONE
        binding.checkButton.visibility = View.VISIBLE
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    private fun onCheckedIn() {
        binding.checkButton.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                android.R.color.holo_red_dark
            )
        )
        binding.checkButton.text = getText(R.string.check_out)
        binding.progressBar.visibility = View.GONE
        binding.checkButton.visibility = View.VISIBLE
    }

    private fun onCheckedOut() {
        binding.checkButton.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.primaryDarkColor
            )
        )
        binding.checkButton.text = getText(R.string.check_in)
        binding.progressBar.visibility = View.GONE
        binding.checkButton.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setName(userName: String) {
        val greeting = "Hello, $userName"
        binding.nameText.text = greeting
    }
}
