plugins {
    id("com.android.application") version "8.8.0" apply false // Πρώτα το Android plugin
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false // Μετά το Kotlin plugin
    //  id("org.jetbrains.python") version "0.0.3" // ή η πιο πρόσφατη έκδοση του plugin

    id("com.google.gms.google-services") version "4.3.15" apply false // Firebase (αν χρειάζεται)
    id("com.chaquo.python") version "16.0.0" apply false
    id("com.pswidersk.python-plugin") version "2.8.1"  apply false


    //   id("com.intellij.modules.python")

}

