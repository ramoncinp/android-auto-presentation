package com.ramoncinp.androidautopresentation.ui.check

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramoncinp.androidautopresentation.data.models.RegistrationTypes
import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import com.ramoncinp.androidautopresentation.domain.states.CheckActionState
import com.ramoncinp.androidautopresentation.domain.states.CheckState
import com.ramoncinp.androidautopresentation.domain.toCheckState
import com.ramoncinp.androidautopresentation.domain.usecases.CheckInUseCase
import com.ramoncinp.androidautopresentation.domain.usecases.CheckOutUseCase
import com.ramoncinp.androidautopresentation.domain.usecases.GetRegistrationEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckViewModel @Inject constructor(
    private val checkInUseCase: CheckInUseCase,
    private val checkOutUseCase: CheckOutUseCase,
    private val sessionManager: SessionManager,
    private val getRegistrationEventUseCase: GetRegistrationEventUseCase
) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName = _userName

    private val _checkState = MutableLiveData<CheckState>(CheckState.Loading)
    val checkState = _checkState

    private val _checkInState = MutableLiveData<CheckActionState>()
    val checkInState = _checkInState

    init {
        getUserName()
        getCheckState()
    }

    private fun getUserName() {
        val name = sessionManager.getUserName()
        _userName.value = name
    }

    fun getCheckState() {
        viewModelScope.launch {
            _checkState.value = getRegistrationEventUseCase().toCheckState()
        }
    }

    fun check() {
        viewModelScope.launch {
            val currentState = _checkState.value

            val flow = if (currentState is CheckState.CheckedIn) {
                checkOutUseCase()
            } else {
                checkInUseCase(RegistrationTypes.MOBILE_REGISTRATION)
            }

            flow.collectLatest {
                _checkInState.value = it
            }
        }
    }
}
