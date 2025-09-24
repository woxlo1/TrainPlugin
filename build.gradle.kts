plugins {
    kotlin("jvm") version "1.9.0"
}


repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}


dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}


tasks.withType<Jar> {
    archiveBaseName.set("TrainPlugin")
}