plugins {
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.tellme.feature_questions"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.compose_compiler_version
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core-navigation"))
    implementation(project(":core-ui"))
    implementation(project(":data-questions"))

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Kotlin.collections)

    kapt(Dependencies.Hilt.kapt)
    implementation(Dependencies.Hilt.hilt)

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