buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1") // AGP 8.8
        classpath("com.chaquo.python:gradle:16.0.0")      // Chaquopy 16.0.0
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://chaquo.com/maven/") } // Chaquopy
    }
}
