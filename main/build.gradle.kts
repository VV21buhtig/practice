plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ci.nsu.moble.main"
    compileSdk = 36

    defaultConfig {
        applicationId = "ci.nsu.moble.main"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        // Обновленный Test Runner для AndroidX
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // ВАЖНО: Включаем поддержку Compose
    buildFeatures {
        compose = true
    }

    // Настройки для Compose компилятора
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11" // Версия должна совпадать с BOM или быть совместимой
    }
}

dependencies {
    // Удаляем старые support библиотеки, оставляем только AndroidX
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0") // Обновил версию

    // Lifecycle и ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Compose Dependencies
    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    implementation(composeBom)
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Тесты
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx. test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx. test.uiautomator:uiautomator:2.3.0")
    // Для Compose Preview
    debugImplementation("androidx.compose.ui:ui-tooling")
}
