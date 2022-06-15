package com.ramoncinp.androidautopresentation.auto.ui

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Template
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.ramoncinp.androidautopresentation.R
import com.ramoncinp.androidautopresentation.auto.utils.autoDependenciesFactory
import com.ramoncinp.androidautopresentation.auto.utils.showToast
import com.ramoncinp.androidautopresentation.core.DispatcherProvider
import com.ramoncinp.androidautopresentation.data.models.RegistrationTypes
import com.ramoncinp.androidautopresentation.domain.states.CheckActionState
import com.ramoncinp.androidautopresentation.domain.states.CheckState
import com.ramoncinp.androidautopresentation.domain.usecases.CheckInUseCase
import com.ramoncinp.androidautopresentation.domain.usecases.CheckOutUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class CheckScreen(
    carContext: CarContext,
    private val checkState: CheckState
) : Screen(carContext), DefaultLifecycleObserver {

    private lateinit var checkInUseCase: CheckInUseCase
    private lateinit var checkOutUseCase: CheckOutUseCase
    private lateinit var dispatchers: DispatcherProvider

    init {
        initDependencies()
        lifecycle.addObserver(this)
    }

    private fun initDependencies() {
        val autoDependencies = autoDependenciesFactory(carContext.applicationContext)
        checkInUseCase = autoDependencies.checkInUseCase
        checkOutUseCase = autoDependencies.checkOutUseCase
        dispatchers = autoDependencies.dispatchers
    }

    override fun onGetTemplate(): Template {
        return MessageTemplate.Builder(getActionMessage())
            .setLoading(true)
            .setTitle(carContext.getString(R.string.app_name))
            .build()
    }

    private fun getActionMessage() = if (checkState is CheckState.CheckedOut) {
        "Checking in"
    } else {
        "Checking out"
    }

    private fun getActionResultMessage() = if (checkState is CheckState.CheckedOut) {
        "Check in"
    } else {
        "Check out"
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        executeAction()
    }

    private fun executeAction() {
        lifecycleScope.launch {
            CoroutineScope(dispatchers.main).launch {
                val flow = if (checkState is CheckState.CheckedOut) {
                    checkInUseCase(RegistrationTypes.AUTO_REGISTRATION)
                } else {
                    checkOutUseCase()
                }

                flow.collectLatest {
                    when (it) {
                        is CheckActionState.Success -> {
                            showToast(carContext, "${getActionResultMessage()} successful")
                            screenManager.pop()
                        }
                        is CheckActionState.Error -> {
                            showToast(carContext, it.message)
                            screenManager.pop()
                        }
                        else -> Timber.v("Other state")
                    }
                }
            }
        }
    }
}
