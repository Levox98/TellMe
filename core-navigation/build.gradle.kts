plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.tellme.core_navigation"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.sourceCompatibility
        targetCompatibility = Config.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {

    implementation(project(":core"))

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Kotlin.collections)

    implementation(platform(Dependencies.Compose.bom))
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.iconsExtended)
    implementation(Dependencies.Compose.util)

    implementation(Dependencies.Lifecycle.viewmodel)

    implementation(Dependencies.Navigation.compose)
    implementation(Dependencies.Navigation.hilt)
}