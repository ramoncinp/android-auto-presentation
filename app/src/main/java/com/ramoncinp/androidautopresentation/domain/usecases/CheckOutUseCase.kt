package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.data.repository.CheckRepository
import com.ramoncinp.androidautopresentation.domain.states.CheckInState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckOutUseCase @Inject constructor(
    private val checkRepository: CheckRepository,
    private val getRegistrationEventUseCase: GetRegistrationEventUseCase
) {

    suspend operator fun invoke() = flow {
        emit(CheckInState.Loading)
        try {
            val event = getRegistrationEventUseCase()
            event?.id?.let {
                checkRepository.checkOut(it)
                emit(CheckInState.Success)
            } ?: emit(CheckInState.Error("Error trying to get the registration info"))
        } catch (e: Exception) {
            emit(CheckInState.Error("Error trying to check in"))
        }
    }
}
