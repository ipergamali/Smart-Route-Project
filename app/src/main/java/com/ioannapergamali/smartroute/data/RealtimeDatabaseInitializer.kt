package com.ioannapergamali.smartroute.data

// File: RealtimeDatabaseInitializer.kt

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.tasks.await

class RealtimeDatabaseInitializer
{
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    init
    {
        val settings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build()
        db.firestoreSettings = settings
    }

    /**
     * Initializes Firestore collections by clearing existing data,
     * creating empty collections, and optionally adding initial data.
     */
    suspend fun initializeCollections()
    {
        // Clear all collections
        clearCollection("users")

        clearCollection("vehicles")
        clearCollection("pois")
        clearCollection("routes")

        // Clear all users from Firebase Authentication
        clearAuthenticationUsers()

        // Create collections with templates
        createUsersCollection()
        createVehiclesCollection()
        createPOIsCollection()
        createRoutesCollection()
    }

    /**
     * Clears all users from Firebase Authentication.
     */
    private suspend fun clearAuthenticationUsers()
    {
        try
        {
            val users = db.collection("Authentication").get().await()
            for (document in users.documents)
            {
                val userId = document.getString("uuid")
                if (userId != null)
                {
                    deleteUserFromAuthentication(userId)
                }
            }
            Log.d("Firebase" , "Cleared all users from Authentication.")
        } catch (e : Exception)
        {
            Log.e("Firebase" , "Error clearing Authentication users: ${e.message}")
        }
    }

    /**
     * Deletes a user from Firebase Authentication.
     */
    private suspend fun deleteUserFromAuthentication(userId : String)
    {
        try
        {
            auth.currentUser?.delete()?.await()
            Log.d("Firebase" , "Deleted user from Authentication: $userId")
        } catch (e : Exception)
        {
            Log.e("Firebase" , "Error deleting user from Authentication: ${e.message}")
        }
    }

    /**
     * Clears all documents from a specified Firestore collection.
     */
    private suspend fun clearCollection(collectionName : String)
    {
        try
        {
            val collectionRef = db.collection(collectionName)
            val documents = collectionRef.get().await()
            for (document in documents)
            {
                collectionRef.document(document.id).delete().await()
            }
            Log.d("Firebase" , "Cleared collection: $collectionName")
        } catch (e : Exception)
        {
            Log.e("Firebase" , "Error clearing collection $collectionName: ${e.message}")
        }
    }

    /**
     * Creates Users collection and sub-collections.
     */
    private fun createUsersCollection()
    {
        val userId = "template-user-id"

        val user = hashMapOf(
                "uuid" to null ,
                "username" to null ,
                "name" to null ,
                "surname" to null
        )
        db.collection("users").document(userId).set(user)
                .addOnSuccessListener {
                    Log.d("Firebase" , "Created Users collection template.")
                    createRolesSubCollection(userId)
                    createAddressSubCollection(userId)
                    createContactSubCollection(userId)
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase" , "Error creating Users collection: ${e.message}")
                }
    }

    private fun createRolesSubCollection(userId : String)
    {
        val roles = hashMapOf(
                "uuid" to null ,
                "role" to null ,
                "permissions" to null
        )
        db.collection("users").document(userId).collection("roles").document("template-role-id")
                .set(roles)
                .addOnSuccessListener {
                    Log.d("Firebase" , "Created Roles sub-collection template.")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase" , "Error creating Roles sub-collection: ${e.message}")
                }
    }

    private fun createAddressSubCollection(userId : String)
    {
        val address = hashMapOf(
                "uuid" to null ,
                "city" to null ,
                "street" to null ,
                "number" to null ,
                "postal_code" to null
        )
        db.collection("users").document(userId).collection("address")
                .document("template-address-id").set(address)
                .addOnSuccessListener {
                    Log.d("Firebase" , "Created Address sub-collection template.")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase" , "Error creating Address sub-collection: ${e.message}")
                }
    }

    private fun createContactSubCollection(userId : String)
    {
        val contact = hashMapOf(
                "uuid" to null ,
                "email" to null ,
                "phone_num" to null
        )
        db.collection("users").document(userId).collection("contact")
                .document("template-contact-id").set(contact)
                .addOnSuccessListener {
                    Log.d("Firebase" , "Created Contact sub-collection template.")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase" , "Error creating Contact sub-collection: ${e.message}")
                }
    }

    private fun createVehiclesCollection()
    {
        val vehicle = hashMapOf(
                "uuid" to null ,
                "owner" to null ,
                "type" to null ,
                "description" to null ,
                "capacity" to null ,
                "available" to null
        )
        db.collection("vehicles").document("template-vehicle-id").set(vehicle)
                .addOnSuccessListener {
                    Log.d("Firebase" , "Created Vehicles collection template.")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase" , "Error creating Vehicles collection: ${e.message}")
                }
    }

    private fun createPOIsCollection()
    {
        val poi = hashMapOf(
                "uuid" to null ,
                "name" to null ,
                "description" to null ,
                "coordinates" to mapOf("lat" to null , "lng" to null)
        )
        db.collection("pois").document("template-poi-id").set(poi)
                .addOnSuccessListener {
                    Log.d("Firebase" , "Created POIs collection template.")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase" , "Error creating POIs collection: ${e.message}")
                }
    }

    private fun createRoutesCollection()
    {
        val route = hashMapOf(
                "uuid" to null ,
                "start_point" to null ,
                "end_point" to null ,
                "intermediate_points" to null ,
                "transport_mode" to null ,
                "cost" to null ,
                "duration" to null
        )
        db.collection("routes").document("template-route-id").set(route)
                .addOnSuccessListener {
                    Log.d("Firebase" , "Created Routes collection template.")
                }
                .addOnFailureListener { e ->
                    Log.e("Firebase" , "Error creating Routes collection: ${e.message}")
                }
    }
}