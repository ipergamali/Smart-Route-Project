package com.ioannapergamali.movewise.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirestoreHelper
{
    suspend fun getUserRoleFromDatabase(uid : String) : String
    {
        val firestore = FirebaseFirestore.getInstance()
        return try
        {
            val userDoc = firestore.collection("users").document(uid).get().await()
            userDoc.getString("role") ?: "Passenger"
        } catch (e : Exception)
        {
            "Passenger"
        }
    }
}
