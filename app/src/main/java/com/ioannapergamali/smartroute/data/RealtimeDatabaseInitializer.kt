package com.ioannapergamali.smartroute.data

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

object RealtimeDatabaseInitializer
{

    private val database = FirebaseDatabase.getInstance().reference

    /**
     * Initializes all required Realtime Database tables/collections without data (empty tables).
     */
    suspend fun initializeEmptyRealtimeDatabase()
    {
        try
        {
            Log.d("RealtimeInit" , "Starting Realtime Database initialization...")

            createEmptyUsersNode()
            Log.d("RealtimeInit" , "Users node initialized")

            createEmptySessionsNode()
            Log.d("RealtimeInit" , "Sessions node initialized")

            createEmptyRolesNode()
            Log.d("RealtimeInit" , "Roles node initialized")

            createEmptyAuthenticationNode()
            Log.d("RealtimeInit" , "Authentication node initialized")

            Log.d("RealtimeInit" , "Realtime Database initialization completed successfully")
        } catch (e : Exception)
        {
            Log.e("RealtimeInit" , "Realtime Database initialization failed: ${e.message}")
        }
    }

    private suspend fun createEmptyUsersNode()
    {
        val usersNode = database.child("users")

        val dummyUser = mapOf(
                "id" to "dummy" ,
                "name" to "Dummy" ,
                "surname" to "User" ,
                "email" to "dummy@example.com" ,
                "role" to "Passenger" ,
                "phone" to "" ,
                "address" to "" ,
                "username" to "dummy" ,
                "password" to ""
        )
        usersNode.child(dummyUser["id"] as String).setValue(dummyUser).await()
        usersNode.child(dummyUser["id"] as String).removeValue().await()
        Log.d("RealtimeInit" , "Users node created successfully.")
    }

    private suspend fun createEmptySessionsNode()
    {
        val sessionsNode = database.child("sessions")

        val dummySession = mapOf(
                "id" to "dummy" ,
                "userId" to "dummy" ,
                "startTime" to System.currentTimeMillis() ,
                "isActive" to false
        )
        sessionsNode.child(dummySession["id"] as String).setValue(dummySession).await()
        sessionsNode.child(dummySession["id"] as String).removeValue().await()
        Log.d("RealtimeInit" , "Sessions node created successfully.")
    }

    private suspend fun createEmptyRolesNode()
    {
        val rolesNode = database.child("roles")

        val dummyRole = mapOf(
                "roleName" to "DummyRole"
        )
        rolesNode.child("dummy").setValue(dummyRole).await()
        rolesNode.child("dummy").removeValue().await()
        Log.d("RealtimeInit" , "Roles node created successfully.")
    }

    private suspend fun createEmptyAuthenticationNode()
    {
        val authNode = database.child("authentication")

        val dummyAuth = mapOf(
                "userId" to "dummy" ,
                "email" to "dummy@example.com" ,
                "passwordHash" to "hashed_password" , // Συμβολική τιμή
                "lastLogin" to System.currentTimeMillis() ,
                "isVerified" to false
        )
        authNode.child(dummyAuth["userId"] as String).setValue(dummyAuth).await()
        authNode.child(dummyAuth["userId"] as String).removeValue().await()
        Log.d("RealtimeInit" , "Authentication node created successfully.")
    }
}
