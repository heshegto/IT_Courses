plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.bandeev.core.ui"
    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt()) {
            minorApiLevel = libs.versions.minorApiLevel.get().toInt()
        }
    }

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

}

dependencies {
    implementation(libs.material)
}