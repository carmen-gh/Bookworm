import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.caminaapps.bookworm.testing"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
}

dependencies {
    implementation(project(":app"))
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.hilt.android)
    implementation(libs.hilt.testing)
    implementation(libs.junit)
    implementation(libs.kotlinx.datetime)
    implementation(libs.room)
    implementation(libs.room.testing)
    implementation(libs.android.test.runner)
}
