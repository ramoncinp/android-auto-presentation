package com.ramoncinp.androidautopresentation.ui.name

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramoncinp.androidautopresentation.EMPTY_STRING
import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InputNameViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _canSubmitName = MutableLiveData(false)
    val canSubmitName = _canSubmitName

    private val _inputNameReady = MutableLiveData(false)
    val inputNameReady = _inputNameReady

    var inputName = EMPTY_STRING

    fun onInputNameChanged(name: String) {
        inputName = name
        _canSubmitName.value = name.isNotEmpty()
    }

    fun submitName() {
        sessionManager.saveUserName(inputName)
        _inputNameReady.value = true
    }
}
