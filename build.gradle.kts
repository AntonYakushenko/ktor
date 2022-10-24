val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kotestVersion = "4.1.3"



plugins {
    application

    kotlin("jvm") version "1.7.20"
    id("io.ktor.plugin") version "2.1.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.20"
    id("io.qameta.allure") version "2.10.0"

}

allure {
    autoconfigure= true
    version.set("2.18.1")
}


group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
}


dependencies {
    //Ktor
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")

    //Kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.5.1")
    testImplementation("io.kotest.extensions:kotest-extensions-allure:1.2.0")
    implementation("io.kotest.extensions:kotest-assertions-ktor:1.0.3")

    //Allure

    implementation("io.qameta.allure:allure-kotlin-model:2.2.0")
    implementation("io.qameta.allure:allure-kotlin-commons:2.2.0")
    implementation("io.qameta.allure:allure-kotlin-junit4:2.2.0")
    testImplementation("io.kotest:kotest-extensions-allure:$kotestVersion")




    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // JUnit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")


}
tasks.test {
    useJUnitPlatform()
}

