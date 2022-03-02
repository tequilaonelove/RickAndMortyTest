plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("androidx.navigation.safeargs.kotlin")
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

    hilt()
    retrofit()
    viewBindingDelegate()
    lifecycle()

    implementation(Deps.COIL)
    implementation(Deps.ANDROID_PAGING_RUNTIME)

    // Navigation Component
    implementation(Deps.ANDROID_NAVIGATION_UI_KTX)
    implementation(Deps.ANDROID_NAVIGATION_FRAGMENT_KTX)

    implementation(Deps.OKHTTP3)
    implementation(Deps.OKHTTP_LOGGER)
    implementation(Deps.KOTLINX_SERIALIZATION)
    implementation(Deps.TIMBER)

}