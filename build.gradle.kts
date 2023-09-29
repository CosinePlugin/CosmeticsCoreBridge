plugins {
    id("java")
}

group = "kr.cosine.cosmeticscorebridge"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.skriptlang.org/releases")
}

dependencies {
    compileOnly("org.spigotmc", "spigot-api", "1.16.5-R0.1-SNAPSHOT")

    compileOnly(files("libs/CosmeticsCore.jar"))
    compileOnly("com.github.SkriptLang", "Skript", "2.7.0")
    compileOnly("net.luckperms", "api", "5.4")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks{
    test {
        useJUnitPlatform()
    }
    jar {
        archiveFileName.set("${rootProject.name}-${project.version}.jar")
        destinationDirectory.set(File("D:\\서버\\1.20.1 - 개발\\plugins"))
    }
}