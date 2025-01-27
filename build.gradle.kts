plugins {
    id("com.android.application") version "8.1.1" apply false // Πρώτα το Android plugin
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false // Μετά το Kotlin plugin

    id("com.google.gms.google-services") version "4.3.15" apply false // Firebase (αν χρειάζεται)
    id("com.chaquo.python") version "16.0.0" apply false // Το Chaquopy εφαρμόζεται μετά
}
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://chaquo.com/maven") } // Αποθετήριο Chaquopy
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1") // Android Gradle Plugin
        classpath("com.chaquo.python:gradle:16.0.0") // Chaquopy Plugin
    }
}
