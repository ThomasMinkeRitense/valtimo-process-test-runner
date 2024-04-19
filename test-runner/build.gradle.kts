import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.7" apply false
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

group = "com.ritense"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.ritense.com/repository/maven-public/") }
    maven { url = uri("https://repo.ritense.com/repository/maven-snapshot/") }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

val valtimoVersion: String by project

dependencies {
    api(platform("com.ritense.valtimo:valtimo-dependency-versions:${valtimoVersion}"))
    api("com.ritense.valtimo:valtimo-dependencies")
    implementation("com.ritense.valtimo:local-document-generation")
    implementation("com.ritense.valtimo:local-mail")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.security:spring-security-web")

    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")

    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}