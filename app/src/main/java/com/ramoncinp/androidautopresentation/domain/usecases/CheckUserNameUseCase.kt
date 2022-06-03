package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.EMPTY_STRING
import com.ramoncinp.androidautopresentation.data.SessionManager
import javax.inject.Inject

class CheckUserNameUseCase @Inject constructor(
    private val sessionManager: SessionManager
) {

    operator fun invoke() = sessionManager.getUserName() ?: EMPTY_STRING
}
