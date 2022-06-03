package com.ramoncinp.androidautopresentation.ui.check

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramoncinp.androidautopresentation.data.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName = _userName

    init {
        getUserName()
    }

    private fun getUserName() {
        val name = sessionManager.getUserName()
        _userName.value = name
    }
}
