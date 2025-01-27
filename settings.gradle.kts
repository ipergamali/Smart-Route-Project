pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()


    }

}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://chaquo.com/maven/") } // Αποθετήριο Chaquopy
    }
//
//    versionCatalogs {
//        create("libs") {
//            from(files("gradle/libs.versions.toml")) // Only one call to `from`
//        }
//    }
}

rootProject.name = "SmartRoute"
include(":app")
