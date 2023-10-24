import org.gradle.api.JavaVersion

object Config {

    const val namespace = "com.tellme"
    const val compileSdkVersion = 34

    const val applicationId = "com.tellme"
    const val minSdkVersion = 26
    const val targetSdkVersion = 34
    const val versionCode = 1
    const val versionName = "1.0"

    const val jvmTarget = "1.8"

    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8
}
