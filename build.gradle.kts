import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

plugins {
	id("java-library")
	id("org.springframework.boot") version "3.1.7"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	`maven-publish`
}

group = "com.ritense"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven(URI("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
}

subprojects {
	repositories {
		mavenCentral()
		maven(URI("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
	}

	apply(plugin = "maven-publish")
	configure<PublishingExtension> {
		repositories {
			maven {
				name = "GitHubPackages"
				url = uri("https://maven.pkg.github.com/ThomasMinkeRitense/valtimo-process-test-runner")
				credentials {
					username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
					password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
				}
			}
		}
		publications {
			register<MavenPublication>("default") {
				from(components["java"])
			}
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.bootJar {
	enabled = false
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
