plugins {
    id("com.android.library")
    id("com.google.dagger.hilt.android")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.tellme.data_questions"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

        testInstrumentationRunner = Config.testInstrumentationRunner
        consumerProguardFiles(Config.consumerProguardFiles)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(Config.defaultProguardFilename),
                Config.proguardProFilename
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core-network"))

    implementation(Dependencies.Core.coreKtx)

    kapt(Dependencies.Hilt.kapt)
    implementation(Dependencies.Hilt.hilt)

    implementation(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)

    api(Dependencies.Room.api)
    kapt(Dependencies.Room.kapt)
    implementation(Dependencies.Room.room)
}