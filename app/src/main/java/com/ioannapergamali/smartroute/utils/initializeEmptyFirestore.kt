package com.ioannapergamali.smartroute.utils

//import com.ioannapergamali.smartroute.data.FirestoreDatabaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun initializeEmptyFirestore()
{
    // Κλήση κατά την εκκίνηση της εφαρμογής ή σε Debug Screen
    CoroutineScope(Dispatchers.IO).launch {
        //   FirestoreDatabaseInitializer.initializeEmptyRealtimeDatabase()
    }
}
