plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("com.chaquo.python") // Chaquopy plugin
    id("com.google.gms.google-services") // Firebase plugin
   // id("com.intellij.modules.python")
}
chaquopy {
    defaultConfig {
        buildPython("C:/Users/joann/AppData/Local/Programs/Python/Python38/python.exe")

        pip {
            install("-r", "requirements.txt")

        }
    }
}

//intellij {
//    plugins.set(listOf("com.intellij.modules.pyth"))
//}


android {
    namespace = "com.ioannapergamali.smartroute"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ioannapergamali.smartroute"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        // NDK Configuration (αν χρησιμοποιείς Native Libraries)
        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64") // or other ABIs
        }


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt") ,
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Συμβατό με Kotlin 1.9.0
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }
    buildToolsVersion = "34.0.0"
    ndkVersion = "28.0.12916984 rc3"
}

dependencies {
    // AndroidX Libraries
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")

    // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.01.00")) // BOM για Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3:1.2.0")

    implementation("androidx.compose.material:material-icons-extended:1.5.1")


    // Firebase
    // Χρησιμοποίησε την BOM για να διαχειριστείς όλες τις Firebase εξαρτήσεις
    implementation(platform("com.google.firebase:firebase-bom:32.0.0")) // Παράδειγμα τελευταίας έκδοσης

    // Δήλωσε τις Firebase βιβλιοθήκες χωρίς έκδοση
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-database-ktx")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    // Αν χρειάζεται το kapt για το Glide:
    // kapt("com.github.bumptech.glide:compiler:4.15.1")

    // Gson
    implementation("com.google.code.gson:gson:2.9.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Testing Libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.01.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Debugging Tools
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // **Προσθήκη της εξάρτησης Grid Impl**
   // runtimeOnly("com.jetbrains.intellij.grid:grid-impl:233.14808.21")
    //  implementation(files("libs/android-gradle-dsl-toml-233.14808.21-sources.jar"))
    //releaseImplementation(files("libs/ae-database-community-233.14808.21-sources.jar"))

    implementation("androidx.compose.compiler:compiler:1.5.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.0") // Εάν χρειάζεσαι και άλλες βιβλιοθήκες για Serializable




}
