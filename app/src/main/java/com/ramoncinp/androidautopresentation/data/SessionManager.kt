package com.ramoncinp.androidautopresentation.data

interface SessionManager {
    fun saveUserName(name: String)
    fun getUserName(): String
}
