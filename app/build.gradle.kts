plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias (libs.plugins.safeargs)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlin.parcelize)

}

android {
    namespace = "com.example.craveyard"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.craveyard"
        minSdk = 23
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
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        dataBinding=true
    }
}

dependencies {

    //nav component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //retrofit
    implementation (libs.gson)
    implementation(libs.retrofit)
    implementation (libs.converter.gson)

    //room
    implementation(libs.room)
    implementation(libs.room.runtime)
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.databinding.common)
    implementation(libs.firebase.firestore.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)


    //coroutines

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //glide
    implementation (libs.glide)

    //lottie
    implementation (libs.lottie)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Firebase
    implementation(libs.fireBase)
    implementation(libs.analytics)
    implementation(libs.auth)
    implementation(libs.firestore)




}