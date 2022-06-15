package com.ramoncinp.androidautopresentation.domain

import com.ramoncinp.androidautopresentation.data.models.RegistrationEvent
import com.ramoncinp.androidautopresentation.domain.states.CheckState

fun RegistrationEvent?.toCheckState(): CheckState {
    return this?.let {
        CheckState.CheckedIn
    } ?: CheckState.CheckedOut
}
