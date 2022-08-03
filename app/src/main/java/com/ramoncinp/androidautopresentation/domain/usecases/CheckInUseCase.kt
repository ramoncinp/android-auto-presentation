package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.EMPTY_STRING
import com.ramoncinp.androidautopresentation.data.models.RegistrationEvent
import com.ramoncinp.androidautopresentation.data.models.RegistrationTypes
import com.ramoncinp.androidautopresentation.data.repository.CheckRepository
import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import com.ramoncinp.androidautopresentation.domain.states.CheckActionState
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class CheckInUseCase @Inject constructor(
    private val checkRepository: CheckRepository,
    private val getDeviceIdUseCase: GetDeviceIdUseCase,
    private val sessionManager: SessionManager
) {

    suspend operator fun invoke(registrationType: String) = flow {
        emit(CheckActionState.Loading)
        try {
            val event = createRegistrationEvent(registrationType)
            checkRepository.checkIn(event)
            emit(CheckActionState.Success)
        } catch (e: Exception) {
            emit(CheckActionState.Error("Error trying to check in"))
        }
    }

    private fun createRegistrationEvent(registrationType: String) = RegistrationEvent(
        EMPTY_STRING,
        getDeviceIdUseCase(),
        Date().time,
        sessionManager.getUserName(),
        registrationType
    )
}
