package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.data.models.RegistrationEvent
import com.ramoncinp.androidautopresentation.data.repository.CheckRepository
import com.ramoncinp.androidautopresentation.data.repository.DeviceIdManager
import java.lang.Exception
import javax.inject.Inject

class GetRegistrationEventUseCase @Inject constructor(
    private val checkRepo: CheckRepository,
    private val deviceIdManager: DeviceIdManager
) {

    suspend operator fun invoke(): RegistrationEvent? {
        return try {
            val deviceId = deviceIdManager.getDeviceId()
            return checkRepo.getEvent(deviceId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
