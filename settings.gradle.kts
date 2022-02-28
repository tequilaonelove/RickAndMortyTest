enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://jitpack.io"))
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://jitpack.io"))
    }
}

include(":app")
