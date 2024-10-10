plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.github.pvoid.vecka"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.pvoid.vecka"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("com.google.android.gms:play-services-wearable:18.0.0")
    implementation("androidx.wear:wear-complications-provider:1.0.0-alpha17")
}