apply("./signing.gradle.kts")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "wat.app.taskmanager"
    compileSdk = 34

    applicationVariants.configureEach {
        outputs.configureEach {
            (this as com.android.build.gradle.internal.api.BaseVariantOutputImpl).outputFileName =
                "${project.name}-${defaultConfig.versionName}.apk";
        }
    }
    signingConfigs {
        create("release") {
            @Suppress("UNCHECKED_CAST")
            fun <T> ext(key: String) = rootProject.extra[key] as T
            storeFile = ext("storeFile")
            storePassword = ext("storePassword")
            keyAlias = ext("keyAlias")
            keyPassword = ext("keyPassword")
        }
    }
    defaultConfig {
        applicationId = "wat.app.taskmanager"
        minSdk = 24
        targetSdk = 35
        versionCode = 2
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ndk {
            abiFilters.addAll(setOf("armeabi","x86","armeabi-v7a","x86_64","arm64-v8a"))
        }
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs["release"]
        }
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs["release"]
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":lib-components"))
    implementation(project(":lib-navigator"))
    implementation(project(":lib-protostore"))
    implementation(libs.shizuku.api)
    implementation(libs.shizuku.provider)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui.graphics)
}