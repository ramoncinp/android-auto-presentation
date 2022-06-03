package com.ramoncinp.androidautopresentation.data.repository

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.ramoncinp.androidautopresentation.EMPTY_STRING
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val USER_NAME = "userName"

class SessionManagerImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val sharedPreferences: SharedPreferences
) : SessionManager {

    override fun isSignedIn(): Boolean = firebaseAuth.currentUser != null

    override suspend fun signIn() {
        firebaseAuth.signInAnonymously().await()
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun saveUserName(name: String) {
        sharedPreferences.edit().apply {
            putString(USER_NAME, name)
        }.apply()
    }

    override fun getUserName(): String =
        sharedPreferences.getString(USER_NAME, EMPTY_STRING) ?: EMPTY_STRING
}
