package com.ramoncinp.androidautopresentation.domain.usecases

import com.ramoncinp.androidautopresentation.data.DeviceIdManager
import javax.inject.Inject

class GenerateDeviceIdUseCase @Inject constructor(
    private val deviceIdManager: DeviceIdManager
) {

    operator fun invoke(): String = deviceIdManager.getDeviceId()
}
