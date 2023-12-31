plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = Config.namespace
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId  = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core-navigation"))
    implementation(project(":core-network"))
    implementation(project(":core-ui"))
    implementation(project(":feature-main"))
    implementation(project(":feature-questions"))

    implementation(platform(Dependencies.Compose.bom))

    implementation(Dependencies.Core.coreKtx)
    implementation(Dependencies.Lifecycle.runtime)
    implementation(Dependencies.Lifecycle.activityCompose)
    implementation(Dependencies.Lifecycle.activity)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material)
    androidTestImplementation(Dependencies.Compose.uiTestJUnit)

    kapt(Dependencies.Hilt.kapt)
    implementation(Dependencies.Hilt.hilt)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.jUnitAssertions)
    androidTestImplementation(Dependencies.Test.espresso)

    debugImplementation(Dependencies.Compose.debugUiTooling)
    debugImplementation(Dependencies.Compose.debugUiTestManifest)

    implementation(Dependencies.Navigation.compose)
}