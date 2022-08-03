package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import javax.inject.Inject

class ValidateSessionUseCase @Inject constructor(
    private val sessionManager: SessionManager
) {

    suspend operator fun invoke() {
        if (sessionManager.isSignedIn().not()) {
            sessionManager.signIn()
        }
    }
}
