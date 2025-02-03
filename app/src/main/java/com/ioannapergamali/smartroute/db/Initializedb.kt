package com.ioannapergamali.smartroute.db

import android.content.Context
import android.widget.Toast
import com.chaquo.python.Python
import com.chaquo.python.PyObject

object PythonHelper {

    fun runPythonScript(context: Context) {
        val py = Python.getInstance()

        try {
            val pyobj: PyObject = py.getModule("initialize_firestore")
            pyobj.callAttr("initialize_firestore")

            // Χρησιμοποιούμε το context που περάσαμε
            Toast.makeText(context, "Το script εκτελέστηκε με επιτυχία!", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Σφάλμα: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
