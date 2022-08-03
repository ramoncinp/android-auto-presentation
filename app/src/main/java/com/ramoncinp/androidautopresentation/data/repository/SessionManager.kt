package com.ramoncinp.androidautopresentation.data.repository

interface SessionManager {
    fun isSignedIn(): Boolean
    suspend fun signIn()
    fun signOut()
    fun saveUserName(name: String)
    fun getUserName(): String
}
