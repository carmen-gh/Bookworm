buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.

//buildscript {
//    dependencies {
//        classpath(libs.hilt.plugin)
//        classpath(libs.google.service.plugin)
//        classpath(libs.firebase.crashlytics.plugin)
//    }
//}

// Without these suppressions version catalog usage here and in other build
// files is marked red by IntelliJ:
// https://youtrack.jetbrains.com/issue/KTIJ-19369.
@Suppress(
    "DSL_SCOPE_VIOLATION",
    "MISSING_DEPENDENCY_CLASS",
    "UNRESOLVED_REFERENCE_WRONG_RECEIVER",
    "FUNCTION_CALL_EXPECTED"
)
plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.detekt).apply(false)
    alias(libs.plugins.firebase.crashlytics).apply(false)
    alias(libs.plugins.google.service).apply(false)
    alias(libs.plugins.hilt).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.kapt).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.kover).apply(true)
}



tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

koverMerged {
    enable()
    filters {
        classes {
            excludes += listOf(
                "dagger.hilt.internal.aggregatedroot.codegen.*",
                "hilt_aggregated_deps.*",
                "com.caminaapps.bookworm.di.*",
                "com.caminaapps.bookworm.core.ui.theme.*",
                "com.caminaapps.bookworm.core.ui.icon.*",
                "com.caminaapps.bookworm.util.previewParameterProvider.*",
                "*CrashlyticsLogging",
                "*CoroutineScopeExt",
                "*BookwormDispatchers",
                "*ComposableSingletons",
                "*Hilt_*",
                "*BuildConfig",
                ".*_Factory.*",
            )
        }
    }
}
