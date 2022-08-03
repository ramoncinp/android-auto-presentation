package com.ramoncinp.androidautopresentation.domain.states

sealed class CheckActionState {
    object Loading : CheckActionState()
    object Success : CheckActionState()
    class Error(val message: String) : CheckActionState()
}
