plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    id("signing")
    id("com.gradleup.nmcp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.spruceid.mobile.sdk"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions { jvmTarget = "1.8" }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions { kotlinCompilerExtensionVersion = "1.5.15" }
}

dependencies {
    api(project(":MobileSdkRs"))
    //noinspection GradleCompatible
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    /* Begin UI dependencies */
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.camera:camera-camera2:1.3.2")
    implementation("androidx.camera:camera-lifecycle:1.3.2")
    implementation("androidx.camera:camera-view:1.3.2")
    implementation("com.google.zxing:core:3.5.1")
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")
    implementation("androidx.camera:camera-mlkit-vision:1.3.0-alpha06")
    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")
    /* End UI dependencies */
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    implementation("com.google.android.play:integrity:1.4.0")
    implementation("org.bitbucket.b_c:jose4j:0.9.6")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.json:json:20230618")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")

    // DC-API dependencies
    val androidxCredentialsVersion = "1.0.0-alpha01"
    implementation("androidx.credentials:credentials:1.5.0")
    implementation("androidx.credentials.registry:registry-digitalcredentials-preview:$androidxCredentialsVersion")
    implementation("androidx.credentials.registry:registry-digitalcredentials-mdoc:$androidxCredentialsVersion")
    implementation("androidx.credentials.registry:registry-provider:$androidxCredentialsVersion")
    implementation("androidx.credentials.registry:registry-provider-play-services:$androidxCredentialsVersion")
    implementation("com.google.android.gms:play-services-identity-credentials:16.0.0-alpha06")
}

configurations.all {
    resolutionStrategy {
        force("com.google.android.gms:play-services-identity-credentials:16.0.0-alpha06")
    }
}
