import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

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
    alias(libs.plugins.android.application)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.test.logger)
}

apply(plugin = "dagger.hilt.android.plugin")
apply(plugin = "com.google.gms.google-services")
apply(plugin = "com.google.firebase.crashlytics")

android {
    namespace = "com.caminaapps.bookworm"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.caminaapps.bookworm"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            extra["alwaysUpdateBuildId"] = false
            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

// Allow references to generated code (room)
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.accompanist.permission)
    implementation(libs.accompanist.placeholder)
    implementation(libs.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.coil.compose)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.ui)
    implementation(libs.constraintlayout.compose)
    implementation(libs.core)
    implementation(libs.datastore.preferences)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.material)
    implementation(libs.mlkit.barcode.scanning)
    implementation(libs.navigation.compose)
    implementation(libs.okhttp3.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)
    implementation(libs.room)
    implementation(libs.timber)

    ksp(libs.room.compiler)
    kapt(libs.hilt.compiler)

    testImplementation(libs.room.testing)
    testImplementation(libs.junit)
    testImplementation(libs.assertk)

    androidTestImplementation(libs.compose.test)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.android.test.core)
    androidTestImplementation(libs.android.test.ext.junit)
    androidTestImplementation(libs.android.test.rules)
    androidTestImplementation(libs.android.test.runner)

    debugImplementation(libs.compose.test.manifest)
    debugImplementation(libs.compose.tooling)

    detektPlugins(libs.detekt.formatting.plugin)
    detektPlugins(libs.detekt.compose.plugin)
}

detekt {
    config = rootProject.files("config/detekt/detekt.yml")
    buildUponDefaultConfig = false
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")
}

testlogger {
    theme = MOCHA
}
