
/*
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.41"

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

configurations {
    create("uberJarImplementation1") {
        this.description = "dependencies included in the uberJar"
        this.extendsFrom(configurations.implementation.get())
        this.isTransitive = true
        this.isCanBeResolved = true
        this.isVisible = true
    }
}
val uberJarImplementation1 by configurations

val uberJarImplementation2 by configurations.creating {
    extendsFrom(configurations.implementation.get())
}


dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // The first one works but does not
    // implementation("com.beust:jcommander:1.71")
    // uberJarImplementation1("com.beust:jcommander:1.71")
    uberJarImplementation2("com.beust:jcommander:1.71")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    // Define the main class for the application
    mainClassName = "demo.AppKt"
}
