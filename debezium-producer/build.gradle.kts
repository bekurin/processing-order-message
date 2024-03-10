plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))
    implementation("io.debezium:debezium-connector-mysql")
    implementation("org.springframework.integration:spring-integration-debezium")
}
