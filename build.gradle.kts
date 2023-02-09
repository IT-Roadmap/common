import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `maven-publish`
    id("org.springframework.boot") version "3.0.2"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"
}

group = "ru.boringowl"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
        credentials {
            username = username
            password = project.properties["authToken"].toString()
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("jitpack") {
            groupId = "ru.boringowl"
            artifactId = "roadmap-common"

            from(components["kotlin"])
        }
    }
}
