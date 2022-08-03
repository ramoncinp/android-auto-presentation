package com.ramoncinp.androidautopresentation.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ramoncinp.androidautopresentation.data.models.DEVICE_FIELD
import com.ramoncinp.androidautopresentation.data.models.RegistrationEvent
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val REGISTRATIONS_COLLECTION = "registrations"

class CheckRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : CheckRepository {

    override suspend fun checkIn(registrationEvent: RegistrationEvent) {
        getCollection().add(registrationEvent).await()
    }

    override suspend fun getEvent(deviceId: String): RegistrationEvent? {
        val result = getCollection().whereEqualTo(
            DEVICE_FIELD, deviceId
        ).get().await().first()

        return try {
            result.toObject(RegistrationEvent::class.java).also { it.id = result.id }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun checkOut(registrationEventId: String) {
        getCollection().document(registrationEventId).delete().await()
    }

    private fun getCollection(): CollectionReference =
        firebaseFirestore.collection(REGISTRATIONS_COLLECTION)
}
