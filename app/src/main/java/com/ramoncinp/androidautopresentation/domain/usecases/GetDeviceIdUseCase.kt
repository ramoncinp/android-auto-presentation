package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.data.repository.DeviceIdManager
import javax.inject.Inject

class GetDeviceIdUseCase @Inject constructor(
    private val deviceIdManager: DeviceIdManager
) {

    operator fun invoke(): String = deviceIdManager.getDeviceId()
}
