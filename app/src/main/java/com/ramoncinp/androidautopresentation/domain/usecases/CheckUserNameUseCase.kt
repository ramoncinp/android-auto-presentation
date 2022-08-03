package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import javax.inject.Inject

class CheckUserNameUseCase @Inject constructor(
    private val sessionManager: SessionManager
) {

    operator fun invoke() = sessionManager.getUserName()
}
