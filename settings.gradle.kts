pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MusicStreamer"
include(":app")
include(":core")
include(":data")
include(":presentation")
include(":domain")
include(":presentation:api_tracks")
include(":presentation:downloaded_tracks")
include(":presentation:playback")
include(":presentation:shared_ui")
