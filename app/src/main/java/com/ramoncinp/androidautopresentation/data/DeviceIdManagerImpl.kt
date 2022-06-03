package com.ramoncinp.androidautopresentation.data

import android.content.SharedPreferences
import com.ramoncinp.androidautopresentation.EMPTY_STRING
import java.util.UUID
import javax.inject.Inject

private const val DEVICE_ID = "device_id"

class DeviceIdManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : DeviceIdManager {

    override fun getDeviceId(): String = if (isIdGenerated()) {
        getUuid()
    } else {
        generateId().also { saveUuid(it) }
    }

    private fun generateId(): String {
        val newId = UUID.randomUUID().toString()
        saveUuid(newId)
        return newId
    }

    private fun getUuid() = sharedPreferences.getString(DEVICE_ID, EMPTY_STRING) ?: EMPTY_STRING

    private fun saveUuid(id: String) {
        sharedPreferences.edit().apply {
            putString(DEVICE_ID, id)
        }.apply()
    }

    private fun isIdGenerated(): Boolean = sharedPreferences.contains(DEVICE_ID)
}
