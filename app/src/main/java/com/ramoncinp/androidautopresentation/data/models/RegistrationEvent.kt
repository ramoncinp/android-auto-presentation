package com.ramoncinp.androidautopresentation.data.models

const val DEVICE_FIELD = "device"

data class RegistrationEvent(
    var id: String? = null,
    val device: String? = null,
    val date: Long? = null,
    val userName: String? = null,
    val registrationType: String? = null
)
