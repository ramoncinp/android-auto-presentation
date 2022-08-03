package com.ramoncinp.androidautopresentation.auto.di

import com.ramoncinp.androidautopresentation.core.DispatcherProvider
import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import com.ramoncinp.androidautopresentation.domain.usecases.*
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AndroidAutoEntryPoint {
    val dispatchers: DispatcherProvider
    val checkUserNameUseCase: CheckUserNameUseCase
    val sessionManager: SessionManager
    val validateSession: ValidateSessionUseCase
    val getRegistrationEvent: GetRegistrationEventUseCase
    val checkInUseCase: CheckInUseCase
    val checkOutUseCase: CheckOutUseCase
}
