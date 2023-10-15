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
    alias(libs.plugins.ksp).apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
