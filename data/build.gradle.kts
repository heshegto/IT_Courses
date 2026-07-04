plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.bandeev.it_courses.data"
    compileSdk {
        version = release(libs.versions.compileSdk.get().toInt()) {
            minorApiLevel = libs.versions.minorApiLevel.get().toInt()
        }
    }

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        val javaVersion = JavaVersion.valueOf(libs.versions.javaVersion.get())
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.test)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
}