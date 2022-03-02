import org.gradle.api.artifacts.dsl.DependencyHandler

object Base {
    const val compileSdk = 31
    const val minSdk = 23
    const val targetSdk = 31
}

object Deps {

    object Versions {

        object Androidx {
            const val CORE_KTX = "1.7.0"
            const val APP_COMPAT = "1.4.1"
            const val ACTIVITY_KTX = "1.4.0"
            const val FRAGMENT_KTX = "1.4.1"
            const val PREFERENCE_KTX = "1.2.0"
            const val CONSTRAINT_LAYOUT = "2.1.3"
            const val CARDVIEW = "1.0.0"
            const val RECYCLERVIEW = "1.2.1"
            const val LIFECYCLE = "2.4.1"
            const val NAVIGATION = "2.4.1"
            const val PAGING = "3.1.0"
        }

        const val VIEWBINDING_PROPERTYDELEGATE = "1.5.3"

        const val LEAK_CANARY = "2.8.1"
        const val GLIDE = "4.13.0"
        const val COIL = "1.4.0"
        const val ROUNDED_IMAGE_VIEW ="2.3.0"
        const val BLUR_SHADOW_IMAGEVIEW = "2.1"
        const val FIT_BUTTON = "2.0.0"
        const val MATERIAL_ANDROID = "1.5.0"
        const val KOTLINX_SERIALIZATION = "1.3.2"
        const val COROUTINES_ANDROID = "1.6.0"
        const val TIMBER = "4.7.1"

        object Retrofit {
            const val RETROFIT2_RUNTIME = "2.9.0"
            const val RETROFIT2_KOTLINX_SERIALIZATION = "0.8.0"
            const val RETROFIT2_CONVERTER_GSON = "2.9.0"
        }

        const val HILT = "2.38.1"
        const val OKHTTP3 = "5.0.0-alpha.2"
        const val KOTLIN = "1.6.0"

    }

    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    const val FIT_BUTTON = "io.github.nikartm:fit-button:${Versions.FIT_BUTTON}"
    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"

    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    const val KOTLINX_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLINX_SERIALIZATION}"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_ANDROID}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_ANDROID}"

    const val VIEWBINDING_PROPERTYDELEGATE = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.VIEWBINDING_PROPERTYDELEGATE}"
    const val VIEWBINDING_PROPERTYDELEGATE_NOREFLECTION = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.VIEWBINDING_PROPERTYDELEGATE}"

    const val ANDROID_CORE_KTX = "androidx.core:core-ktx:${Versions.Androidx.CORE_KTX}"
    const val ANDROID_APP_COMPAT = "androidx.appcompat:appcompat:${Versions.Androidx.APP_COMPAT}"
    const val ANDROID_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.Androidx.CONSTRAINT_LAYOUT}"
    const val ANDROID_CARDVIEW = "androidx.cardview:cardview:${Versions.Androidx.CARDVIEW}"
    const val ANDROID_RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.Androidx.RECYCLERVIEW}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.MATERIAL_ANDROID}"

    const val ANDROID_ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.Androidx.ACTIVITY_KTX}"
    const val ANDROID_FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.Androidx.FRAGMENT_KTX}"

    const val ANDROID_PAGING_RUNTIME = "androidx.paging:paging-runtime-ktx:${Versions.Androidx.PAGING}"

    const val ANDROID_LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Androidx.LIFECYCLE}"
    const val ANDROID_LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Androidx.LIFECYCLE}"
    const val ANDROID_LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Androidx.LIFECYCLE}"
    const val ANDROID_LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler:${Versions.Androidx.LIFECYCLE}"

    const val ANDROID_NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.Androidx.NAVIGATION}"
    const val ANDROID_NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.Androidx.NAVIGATION}"

    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    const val RETROFIT2_RUNTIME = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.RETROFIT2_RUNTIME}"
    const val RETROFIT2_KOTLINX_SERIALIZATION = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Retrofit.RETROFIT2_KOTLINX_SERIALIZATION}"
    const val RETROFIT2_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit.RETROFIT2_CONVERTER_GSON}"

    const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3}"
    const val OKHTTP_LOGGER = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3}"

}

/**
 * Provide dependencies of bundle extension.
 * Required [kapt]
 * @see Deps.HILT
 * @see Deps.HILT_COMPILER
 * @return The dependencies bundle (implementation).
 */
fun DependencyHandler.hilt() {
    implementation(Deps.HILT)
    kapt(Deps.HILT_COMPILER)
}

/**
 * Provide dependencies of bundle extension.
 * @see Deps.ANDROID_LIFECYCLE_RUNTIME
 * @see Deps.ANDROID_LIFECYCLE_VIEWMODEL
 * @see Deps.ANDROID_LIFECYCLE_LIVEDATA
 * @see Deps.ANDROID_LIFECYCLE_COMPILER
 * @return The dependencies bundle (implementation).
 */
fun DependencyHandler.lifecycle() {
    implementation(Deps.ANDROID_LIFECYCLE_RUNTIME)
    implementation(Deps.ANDROID_LIFECYCLE_VIEWMODEL)
    implementation(Deps.ANDROID_LIFECYCLE_LIVEDATA)
    kapt(Deps.ANDROID_LIFECYCLE_COMPILER)
}

/**
 * Provide dependencies of bundle extension.
 * @see Deps.VIEWBINDING_PROPERTYDELEGATE
 * @see Deps.VIEWBINDING_PROPERTYDELEGATE_NOREFLECTION
 * @return The dependencies bundle (implementation).
 */
fun DependencyHandler.viewBindingDelegate() {
    implementation(Deps.VIEWBINDING_PROPERTYDELEGATE)
    implementation(Deps.VIEWBINDING_PROPERTYDELEGATE_NOREFLECTION)
}

/**
 * Provide dependencies of bundle extension.
 * @see Deps.RETROFIT2_RUNTIME
 * @see Deps.RETROFIT2_CONVERTER_GSON
 * @see Deps.RETROFIT2_KOTLINX_SERIALIZATION
 * @return The dependencies bundle (implementation).
 */
fun DependencyHandler.retrofit() {
    implementation(Deps.RETROFIT2_RUNTIME)
    implementation(Deps.RETROFIT2_CONVERTER_GSON)
    implementation(Deps.RETROFIT2_KOTLINX_SERIALIZATION)
}

/**
 * Provide dependencies of bundle extension.
 * @see Deps.COROUTINES_CORE
 * @see Deps.COROUTINES_ANDROID
 * @return The dependencies bundle (implementation).
 */
fun DependencyHandler.coroutines() {
    implementation(Deps.COROUTINES_CORE)
    implementation(Deps.COROUTINES_ANDROID)
}

fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}