plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight")
    id("kotlin-parcelize")
}

kotlin {
    android()
    jvm("desktop")
    ios()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            linkerOpts("-lsqlite3")
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.ui)
                api(compose.foundation)
                api(compose.material)
                implementation(deps.koin.core)
                implementation(deps.kotlinxDateTime)
                implementation(deps.sqlDelight.runtime)
                api(deps.decompose)
                implementation(deps.decompose.extensions)
                implementation("io.ktor:ktor-client-core:2.2.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.3")
                implementation("io.ktor:ktor-client-content-negotiation:2.2.3")
                implementation("io.ktor:ktor-client-logging:2.2.3")
                implementation("ch.qos.logback:logback-classic:1.2.11")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                api("io.github.qdsfdhvh:image-loader:1.3.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(deps.koin.android)
                implementation(deps.sqlDelight.android)
                implementation("io.ktor:ktor-client-android:2.2.3")
            }
        }
//        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependencies {
                implementation(deps.sqlDelight.native)
                implementation("io.ktor:ktor-client-darwin:2.2.4")
                implementation("io.ktor:ktor-client-ios:2.2.1")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(deps.sqlDelight.sqlite)
                api(deps.coroutinesSwing)
                implementation("io.ktor:ktor-client-java:2.2.3")
            }
        }
    }
}

sqldelight {
    databases {
        create("UnsplashDatabase") {
            packageName.set("com.bouyahya.unsplash_multiplatform")
        }
    }
}

android {
    namespace = "com.bouyahya.unsplash_multiplatform"
    compileSdkVersion(deps.versions.compileSdk.get().toInt())
    defaultConfig {
        minSdk = deps.versions.minSdk.get().toInt()
        targetSdk = deps.versions.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16
    }
}