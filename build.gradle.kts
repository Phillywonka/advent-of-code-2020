import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
}
group = "me.philip"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit"))
    testImplementation("com.google.truth:truth:1.1")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}