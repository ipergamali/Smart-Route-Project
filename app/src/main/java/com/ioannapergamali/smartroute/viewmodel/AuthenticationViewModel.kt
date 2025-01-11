package com.ioannapergamali.smartroute.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.ioannapergamali.smartroute.model.Admin
import com.ioannapergamali.smartroute.model.Driver
import com.ioannapergamali.smartroute.model.Passenger
import com.ioannapergamali.smartroute.model.User
import com.ioannapergamali.smartroute.model.UserAddress
import com.ioannapergamali.smartroute.utils.UserSession
import kotlinx.coroutines.launch

class AuthenticationViewModel(application : Application) : AndroidViewModel(application)
{

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val context = application.applicationContext

    fun loginUser(
            email : String ,
            password : String ,
            onLoginSuccess : (FirebaseUser) -> Unit ,
            onLoginFailure : (String) -> Unit
    )
    {
        auth.signInWithEmailAndPassword(email , password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        val firebaseUser = auth.currentUser
                        if (firebaseUser != null)
                        {
                            fetchUserAndRole(firebaseUser.uid , firebaseUser) { user , error ->
                                if (user != null)
                                {
                                    viewModelScope.launch {
                                        UserSession.setUser(context , user)
                                        onLoginSuccess(firebaseUser)
                                    }
                                }
                                else
                                {
                                    onLoginFailure(error ?: "Failed to fetch user role.")
                                }
                            }
                        }
                        else
                        {
                            onLoginFailure("User not found.")
                        }
                    }
                    else
                    {
                        onLoginFailure(task.exception?.message ?: "Login failed.")
                    }
                }
    }

    private fun fetchUserAndRole(
            userId : String ,
            firebaseUser : FirebaseUser ,
            callback : (User? , String?) -> Unit
    )
    {
        firestore.collection("users").document(userId).get()
                .addOnSuccessListener { document ->
                    val role = document.getString("role") ?: "Passenger"
                    val user = createUserBasedOnRole(role , firebaseUser)
                    callback(user , null)
                }
                .addOnFailureListener { exception ->
                    Log.e("FetchRole" , "Error fetching user role: ${exception.message}")
                    callback(null , exception.message)
                }
    }

    private fun createUserBasedOnRole(role : String , firebaseUser : FirebaseUser) : User
    {
        val email = firebaseUser.email ?: "unknown@example.com"
        return when (role)
        {
            "Driver" -> Driver(firebaseUser.uid , email)
            "Admin"  -> Admin(firebaseUser.uid , email)
            else     -> Passenger(firebaseUser.uid , email)
        }
    }
    fun getUserRole(email : String , onResult : (String) -> Unit)
    {
        // Παράδειγμα χρήσης βάσης δεδομένων (Firebase)
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("users")
                .whereEqualTo("email" , email)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty)
                    {
                        val role = documents.first().getString("role") ?: "Passenger"
                        onResult(role) // Επιστροφή ρόλου
                    }
                    else
                    {
                        onResult("Passenger") // Default ρόλος αν δεν βρεθεί χρήστης
                    }
                }
                .addOnFailureListener {
                    onResult("Passenger") // Default ρόλος αν υπάρξει σφάλμα
                }
    }

    fun getUserData(email : String , onResult : (User?) -> Unit)
    {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("users")
                .whereEqualTo("email" , email)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty)
                    {
                        val data = documents.documents[0]
                        val role = data.getString("role") ?: "Passenger"
                        val name = data.getString("name") ?: ""
                        val surname = data.getString("surname") ?: ""
                        val city = data.getString("city") ?: ""
                        val streetName = data.getString("streetName") ?: ""
                        val streetNum = data.getLong("streetNum")?.toInt() ?: 0
                        val postalCode = data.getLong("postalCode")?.toInt() ?: 0
                        val phone = data.getString("phone") ?: ""
                        val username = data.getString("username") ?: ""
                        val password = data.getString("password") ?: ""

                        val address = UserAddress(
                                city = city ,
                                streetName = streetName ,
                                streetNum = streetNum ,
                                postalCode = postalCode
                        )

                        val user = when (role)
                        {
                            "Admin"  -> Admin(
                                    id = data.id ,
                                    name = name ,
                                    surname = surname ,
                                    address = address ,
                                    phoneNum = phone ,
                                    username = username ,
                                    password = password
                            )

                            "Driver" -> Driver(
                                    id = data.id ,
                                    name = name ,
                                    surname = surname ,
                                    address = address ,
                                    phoneNum = phone ,
                                    username = username ,
                                    password = password
                            )

                            else     -> Passenger(
                                    id = data.id ,
                                    name = name ,
                                    surname = surname ,
                                    address = address ,
                                    phoneNum = phone ,
                                    username = username ,
                                    password = password
                            )
                        }

                        onResult(user)
                    }
                    else
                    {
                        onResult(null) // Αν ο χρήστης δεν βρεθεί
                    }
                }
                .addOnFailureListener {
                    onResult(null) // Σε περίπτωση αποτυχίας
                }
    }


}
