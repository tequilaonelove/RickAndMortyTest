plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {

    compileSdk = Base.compileSdk

    defaultConfig {
        applicationId = "com.example.rickandmortytest"
        minSdk = Base.minSdk
        targetSdk = Base.targetSdk
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isShrinkResources = false
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(Deps.ANDROID_MATERIAL)
    implementation(Deps.ANDROID_CONSTRAINT_LAYOUT)
    implementation(Deps.ANDROID_CARDVIEW)
    implementation(Deps.ANDROID_RECYCLERVIEW)
    implementation(Deps.ANDROID_APP_COMPAT)
    implementation(Deps.ANDROID_CORE_KTX)

    implementation(Deps.TIMBER)

}