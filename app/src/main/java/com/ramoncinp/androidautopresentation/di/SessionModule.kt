package com.ramoncinp.androidautopresentation.di

import com.ramoncinp.androidautopresentation.data.DeviceIdManager
import com.ramoncinp.androidautopresentation.data.DeviceIdManagerImpl
import com.ramoncinp.androidautopresentation.data.SessionManager
import com.ramoncinp.androidautopresentation.data.SessionManagerImpl
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
