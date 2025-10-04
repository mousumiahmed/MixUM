pluginManagement {
    repositories {
        google()             // For Android Gradle plugin
        mavenCentral()       // For most dependencies
        gradlePluginPortal() // For Gradle plugins
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()       // Required for Firebase and AndroidX
        mavenCentral() // Required for other libraries
//        maven {
//            url = uri("https://maven-central.storage.apis.com")
//        }
//
//        // Ivy repository
//        ivy {
//            url = uri("https://github.com/ivy-rep/")
//            // Optionally, configure layout
//            // layout("pattern") { ... }
//        }

        // Google repository (for Firebase / AndroidX)
//        google()
    }
}

// Project name
rootProject.name = "MixUM"

// Include app module
include(":app")


 