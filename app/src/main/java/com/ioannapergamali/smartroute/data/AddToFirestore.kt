package com.ioannapergamali.smartroute.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun createUser(
        auth : FirebaseAuth ,
        firestore : FirebaseFirestore ,
        email : String ,
        password : String ,
        name : String ,
        surname : String ,
        username : String ,
        phoneNum : String ,
        city : String ,
        street : String ,
        number : String ,
        postalCode : String ,
        role : String ,
        onSuccess : () -> Unit ,
        onFailure : (String) -> Unit
)
{
    auth.createUserWithEmailAndPassword(email , password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    // Add main user document
                    val userMap = hashMapOf(
                            "uuid" to userId ,
                            "name" to name ,
                            "surname" to surname ,
                            "username" to username
                    )

                    firestore.collection("users").document(userId).set(userMap)
                            .addOnSuccessListener {
                                Log.d("Firebase" , "User document created successfully.")

                                // Add sub-collections
                                addRoleSubCollection(firestore , userId , role)
                                addAddressSubCollection(
                                        firestore ,
                                        userId ,
                                        city ,
                                        street ,
                                        number ,
                                        postalCode
                                )
                                addContactSubCollection(firestore , userId , email , phoneNum)

                                // Add user to Authentication collection
                                addAuthenticationEntry(firestore , userId , email)

                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                Log.e("Firebase" , "Error saving user data: ${e.message}")
                                onFailure("Error saving user data: ${e.message}")
                            }
                }
                else
                {
                    Log.e("Firebase" , "Error creating user: ${task.exception?.message}")
                    onFailure(task.exception?.message ?: "Error creating user.")
                }
            }
}

private fun addRoleSubCollection(firestore : FirebaseFirestore , userId : String , role : String)
{
    val roleMap = hashMapOf(
            "uuid" to userId ,
            "role" to role ,
            "permissions" to listOf<String>()
    )

    firestore.collection("users").document(userId).collection("roles").document("role-id")
            .set(roleMap)
            .addOnSuccessListener {
                Log.d("Firebase" , "Role sub-collection created successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase" , "Error creating role sub-collection: ${e.message}")
            }
}

private fun addAddressSubCollection(
        firestore : FirebaseFirestore ,
        userId : String ,
        city : String ,
        street : String ,
        number : String ,
        postalCode : String
)
{
    val addressMap = hashMapOf(
            "uuid" to userId ,
            "city" to city ,
            "street" to street ,
            "number" to number ,
            "postal_code" to postalCode
    )

    firestore.collection("users").document(userId).collection("address").document("address-id")
            .set(addressMap)
            .addOnSuccessListener {
                Log.d("Firebase" , "Address sub-collection created successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase" , "Error creating address sub-collection: ${e.message}")
            }
}

private fun addContactSubCollection(
        firestore : FirebaseFirestore ,
        userId : String ,
        email : String ,
        phoneNum : String
)
{
    val contactMap = hashMapOf(
            "uuid" to userId ,
            "email" to email ,
            "phone_num" to phoneNum
    )

    firestore.collection("users").document(userId).collection("contact").document("contact-id")
            .set(contactMap)
            .addOnSuccessListener {
                Log.d("Firebase" , "Contact sub-collection created successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase" , "Error creating contact sub-collection: ${e.message}")
            }
}

private fun addAuthenticationEntry(firestore : FirebaseFirestore , userId : String , email : String)
{
    val authMap = hashMapOf(
            "uuid" to userId ,
            "email" to email
    )

    firestore.collection("Authentication").document(userId).set(authMap)
            .addOnSuccessListener {
                Log.d("Firebase" , "User added to Authentication collection successfully.")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase" , "Error adding user to Authentication collection: ${e.message}")
            }
}
