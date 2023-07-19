import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.detekt)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.service)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.ksp)
    alias(libs.plugins.test.logger)
    alias(libs.plugins.about.libraries)
}

android {
    namespace = "com.caminaapps.bookworm"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.caminaapps.bookworm"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.caminaapps.bookworm.testing.BookwormTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/LICENSE.md")
            excludes.add("META-INF/LICENSE.txt")
            excludes.add("META-INF/license.txt")
            excludes.add("META-INF/LICENSE-notice.md")
            excludes.add("META-INF/NOTICE")
            excludes.add("META-INF/NOTICE.txt")
            excludes.add("META-INF/notice.txt")
            excludes.add("META-INF/*.kotlin_module")
        }
    }

    lint {
        xmlReport = true
        sarifReport = true
        checkDependencies = true
    }
}

// Allow references to generated code (room)
kapt {
    correctErrorTypes = true
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.activity.compose)
    implementation(libs.appcompat)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.coil.compose)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons)
    implementation(libs.compose.material3.window.sizes)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.ui)
    implementation(libs.constraintlayout.compose)
    implementation(libs.core)
    implementation(libs.datastore.preferences)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.app.check)
    implementation(libs.firebase.crashlytics)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.lifecycle.compose)
    implementation(libs.navigation.compose)
    implementation(libs.okhttp3.logging)
    implementation(libs.play.services.code.scanner)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)
    implementation(libs.room)
    implementation(libs.timber)
    implementation(platform(libs.compose.bom))
    implementation(platform(libs.firebase.bom))

    kapt(libs.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.assertk)
    testImplementation(libs.hilt.testing)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.mockk.android)
    testImplementation(libs.room.testing)
    testImplementation(libs.turbine)

    kaptTest(libs.hilt.testing)

    androidTestImplementation(libs.android.test.core)
    androidTestImplementation(libs.android.test.ext.junit)
    androidTestImplementation(libs.android.test.rules)
    androidTestImplementation(libs.android.test.runner)
    androidTestImplementation(libs.assertk)
    androidTestImplementation(libs.compose.test)
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.hilt.testing)
    androidTestImplementation(libs.mockk.agent)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(platform(libs.compose.bom))

    kaptAndroidTest(libs.hilt.testing)

    debugImplementation(libs.compose.test.manifest)
    debugImplementation(libs.compose.tooling)

    detektPlugins(libs.detekt.compose.plugin)
    detektPlugins(libs.detekt.formatting.plugin)

    testImplementation(project(":testing"))
    androidTestImplementation(project(":testing"))
}

detekt {
    autoCorrect = true
    config.setFrom(rootProject.files("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    ignoredBuildTypes = listOf("release")
}

tasks.detekt.configure {
    reports {
        sarif.required.set(true)
    }
}

koverReport {
    androidReports("debug") {
        filters {
            excludes {
                classes(
                    "*Fragment",
                    "*Fragment\$*",
                    "*Activity",
                    "*Activity\$*",
                    "*.databinding.*",
                    "*.BuildConfig",
                    "dagger.hilt.internal.aggregatedroot.codegen.*",
                    "hilt_aggregated_deps.*",
                    "com.caminaapps.bookworm.di.*",
                    "com.caminaapps.bookworm.core.ui.theme.*",
                    "com.caminaapps.bookworm.core.ui.icon.*",
                    "com.caminaapps.bookworm.util.previewParameterProvider.*",
                    "com.caminaapps.bookworm.*.*_Impl*",
                    "com.caminaapps.bookworm.*.*_Factory*",
                    "*CrashlyticsLogging",
                    "*CoroutineScopeExt*",
                    "*BookwormDispatchers",
                    "*ComposableSingletons*",
                    "*_HiltModules*",
                    "*Hilt_*",
                    "*BuildConfig",
                    ".*_Factory.*",
                    "*_MembersInjector",
                    "*AppDatabase"
                )
                annotatedBy("*Composable*")
            }
        }
        html {
            onCheck = true
        }
        xml {
            onCheck = true
        }
        verify {
            onCheck = true
        }
    }
}

testlogger {
    theme = MOCHA
}

aboutLibraries {
    registerAndroidTasks = false
}
