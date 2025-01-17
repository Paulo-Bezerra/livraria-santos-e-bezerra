plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  kotlin("plugin.serialization") version "1.9.10"
}

android {
  namespace = "com.imd.livrariasantosebezerra"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.imd.livrariasantosebezerra"
    minSdk = 26
    targetSdk = 33
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
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }

  buildFeatures {
    viewBinding = true
  }
}

dependencies {

  // ktor http cliente:
  val ktorVersion = "2.3.2"
  implementation("io.ktor:ktor-client-android:$ktorVersion")
  implementation("io.ktor:ktor-client-logging:$ktorVersion")
  implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
  implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
  implementation("org.slf4j:slf4j-android:1.7.36")

  // Kotlin Json
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

  implementation("com.github.bumptech.glide:glide:4.16.0")
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}