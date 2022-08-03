package com.ramoncinp.androidautopresentation.domain.states

sealed class MainAppState {
    object HasName : MainAppState()
    object NoName : MainAppState()
}
