buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Deps.Versions.HILT}")
        classpath(kotlin("serialization", version = Deps.Versions.KOTLIN))
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Deps.Versions.Androidx.NAVIGATION}")
        // NOTE: Do not place your application dependencies here; they belong
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}