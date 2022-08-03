package com.ramoncinp.androidautopresentation.data.repository

import com.ramoncinp.androidautopresentation.data.models.RegistrationEvent

interface CheckRepository {
    suspend fun checkIn(registrationEvent: RegistrationEvent)
    suspend fun getEvent(deviceId: String): RegistrationEvent?
    suspend fun checkOut(registrationEventId: String)
}
