package com.ramoncinp.androidautopresentation.auto.ui

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.ramoncinp.androidautopresentation.EMPTY_STRING
import com.ramoncinp.androidautopresentation.R
import com.ramoncinp.androidautopresentation.auto.utils.autoDependenciesFactory
import com.ramoncinp.androidautopresentation.auto.utils.showLoadingTemplate
import com.ramoncinp.androidautopresentation.core.DispatcherProvider
import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import com.ramoncinp.androidautopresentation.domain.states.CheckState
import com.ramoncinp.androidautopresentation.domain.toCheckState
import com.ramoncinp.androidautopresentation.domain.usecases.CheckUserNameUseCase
import com.ramoncinp.androidautopresentation.domain.usecases.GetRegistrationEventUseCase
import com.ramoncinp.androidautopresentation.domain.usecases.ValidateSessionUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val LOADING_DELAY = 1000L

class CheckedStatusScreen(carContext: CarContext) : Screen(carContext), DefaultLifecycleObserver {

    private lateinit var checkUserNameUseCase: CheckUserNameUseCase
    private lateinit var dispatchers: DispatcherProvider
    private lateinit var sessionManager: SessionManager
    private lateinit var validateSession: ValidateSessionUseCase
    private lateinit var getRegistrationEvent: GetRegistrationEventUseCase

    private var error = EMPTY_STRING
    private var loading = true
    private var userName = EMPTY_STRING
    private var checkedState: CheckState = CheckState.CheckedOut

    init {
        initDependencies()
        lifecycle.addObserver(this)
    }

    private fun initDependencies() {
        val autoDependencies = autoDependenciesFactory(carContext.applicationContext)
        checkUserNameUseCase = autoDependencies.checkUserNameUseCase
        dispatchers = autoDependencies.dispatchers
        sessionManager = autoDependencies.sessionManager
        getRegistrationEvent = autoDependencies.getRegistrationEvent
        validateSession = autoDependencies.validateSession
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        validateUserData()
    }

    override fun onGetTemplate(): Template {
        if (loading) {
            return showLoadingTemplate(carContext)
        } else {
            val message = when {
                error.isNotEmpty() -> error
                else -> "Hello, $userName!"
            }

            val messageTemplate = MessageTemplate.Builder(message)
                .setTitle(carContext.getString(R.string.app_name))
                .setIcon(
                    CarIcon.Builder(
                        IconCompat.createWithResource(
                            carContext,
                            R.drawable.ic_launcher_round
                        )
                    ).build()
                )

            if (error.isEmpty()) messageTemplate.addAction(
                Action.Builder()
                    .setBackgroundColor(getCheckStatusButtonColor(checkedState))
                    .setTitle(getCheckStatusText(checkedState))
                    .setOnClickListener { navigateToActionScreen() }.build()
            )

            return messageTemplate.build()
        }
    }

    private fun getCheckStatusText(checkState: CheckState) =
        if (checkState is CheckState.CheckedIn) {
            carContext.getText(R.string.check_out)
        } else {
            carContext.getText(R.string.check_in)
        }

    private fun getCheckStatusButtonColor(checkState: CheckState) =
        if (checkState is CheckState.CheckedIn) {
            CarColor.RED
        } else {
            CarColor.GREEN
        }

    private fun validateUserData() {
        lifecycleScope.launch {
            CoroutineScope(dispatchers.io).launch {
                delay(LOADING_DELAY)

                if (checkUserNameUseCase().isEmpty()) {
                    error = "Please specify a username in your mobile device"
                } else {
                    userName = sessionManager.getUserName()
                    validateSession()
                    checkedState = getRegistrationEvent().toCheckState()
                }

                loading = false
                invalidate()
            }
        }
    }

    private fun navigateToActionScreen() {
        screenManager.push(CheckScreen(carContext, checkedState))
    }
}
