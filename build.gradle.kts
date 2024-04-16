import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    kotlin("jvm") version "1.9.21"
    id("io.freefair.lombok") version "8.6"
    java
    idea
    id("com.google.cloud.tools.jib") version "2.1.0"
    id("com.adarshr.test-logger") version "3.2.0"
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "kotlin")
    apply(plugin = "com.google.cloud.tools.jib")
    apply(plugin = "com.adarshr.test-logger")

    group = "com.redcare.pharmacy.super.stars"
    version = "0.1"

    repositories {
        google()
        mavenCentral()
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile>() {
        kotlinOptions.jvmTarget = "17"
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testlogger {
        theme = ThemeType.MOCHA
    }
}

dependencies {
    testImplementation(kotlin("test"))
}
