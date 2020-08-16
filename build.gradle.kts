import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.flywaydb.gradle.task.FlywayMigrateTask
import org.flywaydb.gradle.task.FlywayRepairTask

plugins {
    id("org.springframework.boot") version "2.3.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.avast.gradle.docker-compose") version "0.12.1"
    id("org.flywaydb.flyway") version "6.5.3"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.jpa") version "1.3.72"
}

group = "com.wisemencouncil"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    implementation("org.mindrot:jbcrypt:0.3m")

    implementation("io.jsonwebtoken:jjwt-api:0.10.7")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.7")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.7")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.+")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("io.mockk:mockk:1.9.3")

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

dockerCompose {
    createNested("server").apply {
        useComposeFiles = listOf("./docker-compose.yml")
        buildBeforeUp = true
    }
}

tasks.register("buildServer") {
    dependsOn(":bootJar")
    group = "application"
}

tasks.register("startServer") {
    dependsOn(":buildServer")
    dependsOn(":serverComposeUp")
    group = "application"
}

tasks.register("stopServer") {
    dependsOn(":serverComposeDown")
    group = "application"
}

tasks.register<FlywayMigrateTask>("migrateLocalDatabase") {
    url = System.getenv("JDBC_DATABASE_URL")
    user = System.getenv("JDBC_DATABASE_USERNAME")
    password = System.getenv("JDBC_DATABASE_PASSWORD")
}

tasks.register<FlywayRepairTask>("repairMigrations") {
    url = System.getenv("JDBC_DATABASE_URL")
    user = System.getenv("JDBC_DATABASE_USERNAME")
    password = System.getenv("JDBC_DATABASE_PASSWORD")
}
