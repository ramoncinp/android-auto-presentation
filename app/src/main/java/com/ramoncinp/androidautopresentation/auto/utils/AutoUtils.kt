package com.ramoncinp.androidautopresentation.auto.utils

import android.content.Context
import androidx.car.app.CarContext
import androidx.car.app.CarToast
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import com.ramoncinp.androidautopresentation.R
import com.ramoncinp.androidautopresentation.auto.di.AndroidAutoEntryPoint
import dagger.hilt.android.EntryPointAccessors

fun autoDependenciesFactory(context: Context) = EntryPointAccessors.fromApplication(
    context,
    AndroidAutoEntryPoint::class.java
)

fun showLoadingTemplate(carContext: CarContext) = PaneTemplate.Builder(
    Pane.Builder().setLoading(true).build()
).setTitle(carContext.getString(R.string.app_name)).build()

fun showToast(carContext: CarContext, message: String) {
    CarToast.makeText(
        carContext,
        message,
        CarToast.LENGTH_LONG
    ).show()
}
