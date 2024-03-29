

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    }
}


plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
    // For KEthereum library
    maven(url="https://jitpack.io")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_1_8
}

object Versions {
    val ProvenanceProtos = "1.7.0-0.0.2"
    val ProvenanceHDWallet = "0.1.9"
    val BouncyCastle = "1.63"
    val Kethereum = "0.83.4"
    val Komputing = "0.1"
    val Grpc = "1.42.0"
    val Kotlin = "1.5.0"
}

dependencies {
    implementation(files("./local_lib/proto-kotlin-feature_msg-based-fees-proto-0c10fdf.jar"))
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-allopen:${Versions.Kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.Kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin}")

    // Provenance
    implementation("io.provenance.protobuf:pb-proto-java:${Versions.ProvenanceProtos}")
    implementation("io.provenance.hdwallet:hdwallet:${Versions.ProvenanceHDWallet}")

    // Grpc
    implementation("io.grpc:grpc-alts:${Versions.Grpc}")
    implementation("io.grpc:grpc-netty:${Versions.Grpc}")
    implementation("io.grpc:grpc-protobuf:${Versions.Grpc}")
    implementation("io.grpc:grpc-stub:${Versions.Grpc}")

    // Crypto
    implementation("org.bouncycastle:bcprov-jdk15on:${Versions.BouncyCastle}")
    implementation("com.github.komputing.kethereum:bip32:${Versions.Kethereum}")
    implementation("com.github.komputing.kethereum:bip39:${Versions.Kethereum}")
    implementation("com.github.komputing.kethereum:crypto:${Versions.Kethereum}")
    implementation("com.github.komputing.kethereum:crypto_api:${Versions.Kethereum}")
    implementation("com.github.komputing.kethereum:crypto_impl_bouncycastle:${Versions.Kethereum}")
    implementation("com.github.komputing.kethereum:extensions_kotlin:${Versions.Kethereum}")
    implementation("com.github.komputing.kethereum:model:${Versions.Kethereum}")
    implementation("com.github.komputing:kbase58:${Versions.Komputing}")
    implementation("com.github.komputing:kbip44:${Versions.Komputing}")
    implementation("com.google.protobuf:protobuf-java:3.19.1")

    // Test
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

configurations.forEach { it.exclude("org.slf4j", "slf4j-api") }


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.provenance.client"
            artifactId = "pb-grpc-client-kotlin"
            version = "1.0.0-SNAPSHOT"

            from(components["java"])
        }
    }
}
