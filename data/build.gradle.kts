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
        buildConfigField("String", "BASE_URL", "\"https://drive.usercontent.google.com/\"")
        buildConfigField("String", "PATH_URL", "\"u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download\"")
    }

    buildFeatures {
        buildConfig = true
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
    testImplementation(libs.koin.test)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
}