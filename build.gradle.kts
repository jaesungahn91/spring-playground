plugins {
    java
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "io.github.js"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.github.maricn:logback-slack-appender:1.6.1")
    implementation("net.gpedro.integrations.slack:slack-webhook:1.4.0")
    implementation("com.slack.api:bolt:1.27.3")
    implementation("com.slack.api:bolt-servlet:1.27.3")
    implementation("com.slack.api:bolt-jetty:1.27.3")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
