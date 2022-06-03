package com.ramoncinp.androidautopresentation.domain.states

sealed class CheckState {
    object CheckedIn : CheckState()
    object CheckedOut : CheckState()
    object Loading : CheckState()
}
