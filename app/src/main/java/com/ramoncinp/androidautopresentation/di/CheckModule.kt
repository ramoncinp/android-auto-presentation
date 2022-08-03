package com.ramoncinp.androidautopresentation.di

import com.ramoncinp.androidautopresentation.core.DispatcherProvider
import com.ramoncinp.androidautopresentation.core.DispatcherProviderImpl
import com.ramoncinp.androidautopresentation.data.repository.CheckRepository
import com.ramoncinp.androidautopresentation.data.repository.CheckRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CheckModule {

    @Binds
    abstract fun dispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider

    @Binds
    abstract fun bindCheckRepository(checkRepositoryImpl: CheckRepositoryImpl): CheckRepository
}
