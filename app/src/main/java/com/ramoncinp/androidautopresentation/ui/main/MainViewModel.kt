package com.ramoncinp.androidautopresentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramoncinp.androidautopresentation.EMPTY_STRING
import com.ramoncinp.androidautopresentation.domain.states.MainAppState
import com.ramoncinp.androidautopresentation.domain.usecases.CheckUserNameUseCase
import com.ramoncinp.androidautopresentation.domain.usecases.ValidateSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val LOADING_DELAY = 1000L

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkUserNameUseCase: CheckUserNameUseCase,
    private val validateSessionUseCase: ValidateSessionUseCase
) : ViewModel() {

    private val _mainAppState = MutableLiveData<MainAppState>()
    val mainAppState: LiveData<MainAppState> = _mainAppState

    init {
        getUserName()
    }

    private fun getUserName() {
        viewModelScope.launch {
            delay(LOADING_DELAY)

            val state = when (checkUserNameUseCase()) {
                EMPTY_STRING -> {
                    MainAppState.NoName
                }
                else -> {
                    validateSessionUseCase()
                    MainAppState.HasName
                }
            }

            _mainAppState.postValue(state)
        }
    }
}
