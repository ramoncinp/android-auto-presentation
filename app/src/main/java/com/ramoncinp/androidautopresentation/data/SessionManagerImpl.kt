package com.ramoncinp.androidautopresentation.data

import android.content.SharedPreferences
import com.ramoncinp.androidautopresentation.EMPTY_STRING
import javax.inject.Inject

private const val USER_NAME = "userName"

class SessionManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SessionManager {

    override fun saveUserName(name: String) {
        sharedPreferences.edit().apply {
            putString(USER_NAME, name)
        }.apply()
    }

    override fun getUserName(): String =
        sharedPreferences.getString(USER_NAME, EMPTY_STRING) ?: EMPTY_STRING
}
