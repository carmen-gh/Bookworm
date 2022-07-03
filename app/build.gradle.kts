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
    alias(libs.plugins.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.caminaapps.bookworm"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        namespace = "com.caminaapps.bookworm"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
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
    implementation(libs.core.ktx)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.plugin)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.material)
    implementation(libs.mlkit.barcode.scanning)
    implementation(libs.navigation.compose)
    implementation(libs.okhttp3.logging)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)
    implementation(libs.room.ktx)
    implementation(libs.timber)
    kapt(libs.room.compiler)
    kapt(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.room.testing)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.compose.test)
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
