package com.ramoncinp.androidautopresentation.auto.service

import android.content.Intent
import androidx.car.app.Session
import com.ramoncinp.androidautopresentation.auto.ui.CheckedStatusScreen

class CheckInSession : Session() {
    override fun onCreateScreen(intent: Intent) = CheckedStatusScreen(carContext)
}
