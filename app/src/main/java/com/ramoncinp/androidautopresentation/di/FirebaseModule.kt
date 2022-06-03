package com.ramoncinp.androidautopresentation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun providesAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()
}
