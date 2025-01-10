package com.ioannapergamali.movewise.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.ioannapergamali.movewise.model.Admin
import com.ioannapergamali.movewise.model.Driver
import com.ioannapergamali.movewise.model.Passenger
import com.ioannapergamali.movewise.model.User
import com.ioannapergamali.movewise.utils.UserSession
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
}
