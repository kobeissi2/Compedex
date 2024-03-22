val composeVersion = "1.6.4"

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp").version("1.9.23-1.0.19")
}
android {
    namespace = "com.kobeissidev.jetpackcomposepokedex"
}

android.run {
    compileSdk = 31

    defaultConfig.run {
        applicationId("com.kobeissidev.jetpackcomposepokedex")
        minSdk = 24
        targetSdk = compileSdk
        versionCode(1)
        versionName("1.0.0")
        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        javaCompileOptions.annotationProcessorOptions.arguments.putAll(
            mapOf(
                "room.exportSchema" to "false",
                "room.incremental" to "true",
                "room.expandProjection" to "true"
            )
        )
    }

    buildTypes.run {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_11)
        targetCompatibility(JavaVersion.VERSION_11)
    }

    composeOptions.kotlinCompilerExtensionVersion = composeVersion
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    buildFeatures.compose = true
}

dependencies {
    // Temp workaround for hilt + room bug
    api("androidx.room:room-runtime:2.6.1")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51")
    // Used for hiltViewModel() in Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // Jetpack Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    // implementations support for setContent in activities
    implementation("androidx.activity:activity-compose:1.8.2")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    // Material Design
    implementation("androidx.compose.material:material:$composeVersion")
    // Material design icons
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    // Accompanist to supplement Jetpack Compose
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")
    // Navigation in Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.34.0")
    // Used for image loading in Compose
    implementation("io.coil-kt:coil-compose:2.6.0")
    // Retrofit + Moshi
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.moshi:moshi:1.15.1")
    // Kotlinx Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    // Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    // Paging Compose
    implementation("androidx.paging:paging-compose:3.2.1")
    // Swipe to Refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.34.0")
    // Pager for creating tabbed layout
    implementation("com.google.accompanist:accompanist-pager:0.34.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.34.0")
    // Color extraction from image
    implementation("androidx.palette:palette-ktx:1.0.0")
    // Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    kapt("com.google.dagger:hilt-android-compiler:2.51")
    ksp("androidx.room:room-compiler:2.6.1")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")
}