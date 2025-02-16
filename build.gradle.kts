// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.8.0" apply false // Ενημέρωσε την έκδοση αν χρειάζεται
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    id("com.google.gms.google-services") version "4.4.2" apply false
    id("com.chaquo.python") version "16.0.0" apply false
    //alias(libs.plugins.kotlin) apply false
    //  alias(libs.plugins.kapt) apply false

}