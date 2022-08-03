package com.ramoncinp.androidautopresentation.di

import com.ramoncinp.androidautopresentation.data.repository.DeviceIdManager
import com.ramoncinp.androidautopresentation.data.repository.DeviceIdManagerImpl
import com.ramoncinp.androidautopresentation.data.repository.SessionManager
import com.ramoncinp.androidautopresentation.data.repository.SessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {

    @Binds
    abstract fun bindSessionManager(sessionManagerImpl: SessionManagerImpl): SessionManager

    @Binds
    abstract fun bindIdGenerator(idGeneratorImpl: DeviceIdManagerImpl): DeviceIdManager
}
