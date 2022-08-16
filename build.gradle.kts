buildscript {
    dependencies {
        classpath(libs.hilt.plugin)
        classpath(libs.google.service.plugin)
        classpath(libs.firebase.crashlytics.plugin)
    }
}

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
    alias(libs.plugins.android).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.detekt).apply(false)
    alias(libs.plugins.kotlin.kapt).apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
