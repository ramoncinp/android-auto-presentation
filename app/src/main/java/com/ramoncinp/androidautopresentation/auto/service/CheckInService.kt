package com.ramoncinp.androidautopresentation.auto.service

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator
import com.ramoncinp.androidautopresentation.auto.ui.CheckedStatusScreen

class CheckInService : CarAppService() {

    override fun onCreateSession() = CheckInSession()

    @SuppressLint("PrivateResource")
    override fun createHostValidator(): HostValidator = if (doesAllowAllHosts()) {
        HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
    } else {
        HostValidator.Builder(applicationContext)
            .addAllowedHosts(androidx.car.app.R.array.hosts_allowlist_sample)
            .build()
    }

    private fun doesAllowAllHosts(): Boolean =
        (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

    inner class CheckInSession : Session() {
        override fun onCreateScreen(intent: Intent) = CheckedStatusScreen(carContext)
    }
}
