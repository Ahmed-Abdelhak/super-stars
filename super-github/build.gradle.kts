plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.spring") version "1.9.21"
}

group = "com.redcare.pharmacy.super.stars.super.github"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.6.2")
    implementation("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
    testImplementation("org.mockito:mockito-core:4.2.0")
    testImplementation("org.mockito:mockito-inline:4.2.0")
    testImplementation("io.mockk:mockk:1.13.10")


    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jib {
    container {
        mainClass = "com.redcare.pharmacy.SuperGithubApplicationKt"
        jvmFlags = listOf("-server", "-XX:+PrintFlagsFinal")
        creationTime.set("USE_CURRENT_TIMESTAMP")
    }
    from {
        image = "eclipse-temurin:17-jre"
    }
    to {
        image = "super-github"
    }
}
