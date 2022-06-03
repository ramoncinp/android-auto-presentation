package com.ramoncinp.androidautopresentation.domain.states

sealed class CheckInState {
    object Loading : CheckInState()
    object Success : CheckInState()
    class Error(val message: String) : CheckInState()
}
