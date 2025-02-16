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
    var isLoggedIn = false

    /**
     * Sets the current user and saves the session in SharedPreferences.
     */
    fun setUser(context : Context , user : User)
    {
        currentUser = user
        isLoggedIn = true

        val sharedPreferences = getPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString(USER_KEY, gson.toJson(user))
        editor.putBoolean(IS_LOGGED_IN_KEY, true)
        editor.apply()
    }

    /**
     * Clears the user session and removes it from SharedPreferences.
     */
    fun clearSession(context : Context)
    {
        currentUser = null
        isLoggedIn = false

        val sharedPreferences = getPreferences(context)
        val editor = sharedPreferences.edit()
        editor.remove(USER_KEY)
        editor.putBoolean(IS_LOGGED_IN_KEY, false)
        editor.apply()
    }

    /**
     * Loads the user session from SharedPreferences.
     */
    fun loadSession(context : Context)
    {
        val sharedPreferences = getPreferences(context)
        isLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN_KEY , false)
        val userJson = sharedPreferences.getString(USER_KEY, null)

        if (isLoggedIn && userJson != null)
        {
            currentUser = gson.fromJson(userJson, User::class.java)
        } else {
            currentUser = null
        }
    }

    /**
     * Gets the SharedPreferences instance.
     */
    private fun getPreferences(context : Context) : SharedPreferences
    {
        return context.getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE)
    }
}
