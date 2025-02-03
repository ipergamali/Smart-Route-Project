package com.ioannapergamali.smartroute.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ioannapergamali.smartroute.data.RealtimeDatabaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DebugScreen()
{
    val context = LocalContext.current

    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp) ,
            verticalArrangement = Arrangement.Center ,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {

            // Κλήση κατά την εκκίνηση της εφαρμογής ή σε Debug Screen
            var i = 1
            CoroutineScope(Dispatchers.IO).launch {
                val initializer = RealtimeDatabaseInitializer()
                initializer.initializeCollections()
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(
                            context ,
                            "Firestore Initialized. Roles and Addresses Added!" ,
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }) {
            Text("Initialize Firestore")
        }
    }
}
