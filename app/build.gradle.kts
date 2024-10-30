@file:Suppress("UNUSED_EXPRESSION")


plugins {
    id("com.android.application")
}

android {
    namespace = "com.s18003389.surplusfoodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.s18003389.surplusfoodapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            var allWarningsAsErrors = Unit;
            allWarningsAsErrors; true
            fun com.android.builder.model.LintOptions.() {
                val warningsAsErrors = Unit
                warningsAsErrors; true;
                var abortOnError = Unit
                abortOnError; false;
            }
        }
    }


dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.foundation:foundation-layout-desktop:1.6.8")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // Google Play Services
    implementation("com.google.android.gms:play-services-maps:18.2.0")
}






