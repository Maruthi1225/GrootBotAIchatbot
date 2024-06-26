import com.android.build.api.variant.BuildConfigField
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-android")

}

android {
    namespace = "com.vdc.generativeai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vdc.generativeai"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    androidComponents {
        onVariants { variant ->
            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())

            variant.buildConfigFields.put(
                "API_KEY", BuildConfigField(
                    "String", "\"${properties.getProperty("API_KEY")}\"", "api key"
                )
            )
        }

    }
    buildFeatures {
        buildConfig = true
    }
}






dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")

    // Icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")
    implementation("androidx.compose.material:material-icons-core:1.5.4")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Image loader
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Gemini AI
    implementation("com.google.ai.client.generativeai:generativeai:0.2.0")
    implementation("com.google.android.material:material:1.11.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.navigation:navigation-common:2.7.7")



    implementation ("com.google.firebase:firebase-auth-ktx:22.3.1")
    //noinspection BomWithoutPlatform
    implementation ("com.google.firebase:firebase-bom:32.7.3")




}
