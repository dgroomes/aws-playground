plugins {
    application
}

val slf4jVersion = "1.7.36" // SLF4J releases: http://www.slf4j.org/news.html
val jacksonVersion = "2.13.1" // Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
val httpComponentsV5Version = "5.1.3" // HttpComponents v5 releases: https://hc.apache.org/news.html

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("org.apache.httpcomponents.client5:httpclient5:$httpComponentsV5Version")
    implementation(platform("com.fasterxml.jackson:jackson-bom:$jacksonVersion"))
}

application {
    mainClass.set("dgroomes.Main")
}
