package com.ioannapergamali.smartroute.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ioannapergamali.smartroute.model.User

object UserSession
{
    private const val PREF_NAME = "user_session"
    private const val USER_KEY = "current_user"
    private const val IS_LOGGED_IN_KEY = "is_logged_in"

    private val gson = Gson()
    var currentUser : User? = null
        private set
    var isLoggedIn = false
        private set

    fun setUser(context : Context , user : User)
    {
        currentUser = user
        isLoggedIn = true
        saveSession(context , user)
    }

    fun clearSession(context : Context)
    {
        currentUser = null
        isLoggedIn = false
        getPreferences(context).edit().clear().apply()
    }

    private fun saveSession(context : Context , user : User)
    {
        val sharedPreferences = getPreferences(context)
        sharedPreferences.edit().apply {
            putString(USER_KEY , gson.toJson(user))
            putBoolean(IS_LOGGED_IN_KEY , true)
            apply()
        }
    }

    fun loadSession(context : Context)
    {
        val sharedPreferences = getPreferences(context)
        isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN_KEY , false)
        currentUser = if (isLoggedIn)
        {
            sharedPreferences.getString(USER_KEY , null)?.let {
                gson.fromJson(it , User::class.java)
            }
        }
        else null
    }

    private fun getPreferences(context : Context) : SharedPreferences
    {
        return context.getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE)
    }
}
