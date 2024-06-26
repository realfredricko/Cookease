import java.util.Properties
import java.io.File
import java.io.FileInputStream

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
  id("dagger.hilt.android.plugin")
   id("com.google.devtools.ksp")
    //id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}
// Function to load local properties
fun gradleLocalProperties(rootDir: File): Properties {
    val properties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { fileInputStream ->
            properties.load(fileInputStream)
        }
    }
    return properties
}
android {
    namespace = "com.example.cookease"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cookease"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        //Groovy
//def localPropertiesFile = rootProject.file("local.properties")
//def localProperties = new Properties()
//localProperties.load(new FileInputStream(localPropertiesFile))
       // buildConfigField ("String","SPOONACULAR_API_KEY",localProperties['spoonacularApiKey'])

        //kotlin
        val properties:Properties = gradleLocalProperties(rootDir)
        buildConfigField ("String","SPOONACULAR_API_KEY","\"${properties.getProperty("SPOONACULAR_API_KEY")}\"")
    }

    android {
        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Splash screen
   implementation(libs.androidx.core.splashscreen)

//    //Retrofit
 implementation (libs.retrofit)
implementation (libs.converter.gson)
implementation (libs.okhttp)

//    //Coil for image loading
implementation(libs.coil.compose)

//    //Kotlin Extensions and Coroutines support for room
   implementation(libs.androidx.room.ktx)

//    //Room
   implementation(libs.room.ktx)
  ksp(libs.androidx.room.compiler)
 implementation(libs.androidx.room.runtime.android)
 implementation(libs.androidx.room.paging)

   //Paging
   implementation(libs.androidx.paging.compose.android)
implementation(libs.androidx.paging.runtime)

//    //Navigation
  implementation(libs.androidx.navigation.runtime)
 implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.hilt.navigation)
//
//    //dagger-hilt
   implementation(libs.hilt.android)
  ksp(libs.hilt.compiler)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.room:room-coroutines:2.1.0-alpha04")

  implementation(libs.androidx.foundation.android)
 implementation(libs.androidx.material.icons.extended)
    //Secrets gradle plugin

}
