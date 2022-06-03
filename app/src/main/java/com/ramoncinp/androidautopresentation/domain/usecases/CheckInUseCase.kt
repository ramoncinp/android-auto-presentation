package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.EMPTY_STRING
import com.ramoncinp.androidautopresentation.data.models.RegistrationEvent
import com.ramoncinp.androidautopresentation.data.models.RegistrationTypes
import com.ramoncinp.androidautopresentation.data.repository.CheckRepository
import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import com.ramoncinp.androidautopresentation.domain.states.CheckInState
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class CheckInUseCase @Inject constructor(
    private val checkRepository: CheckRepository,
    private val getDeviceIdUseCase: GetDeviceIdUseCase,
    private val sessionManager: SessionManager
) {

    suspend operator fun invoke() = flow {
        emit(CheckInState.Loading)
        try {
            val event = createRegistrationEvent()
            checkRepository.checkIn(event)
            emit(CheckInState.Success)
        } catch (e: Exception) {
            emit(CheckInState.Error("Error trying to check in"))
        }
    }

    private fun createRegistrationEvent() = RegistrationEvent(
        EMPTY_STRING,
        getDeviceIdUseCase(),
        Date().time,
        sessionManager.getUserName(),
        RegistrationTypes.MOBILE_REGISTRATION
    )
}
