plugins {
    java
}

val jacksonVersion = "2.13.1" // Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases

val junitJupiterVersion = "5.8.2" // JUnit releases: https://junit.org/junit5/docs/current/release-notes/index.html
val assertJVersion = "3.22.0" // AssertJ releases: https://github.com/assertj/assertj-core/tags

repositories {
    mavenCentral()
}

dependencies {
    constraints {
        implementation("org.apache.commons:commons-text:1.9")
    }
    implementation(platform("com.fasterxml.jackson:jackson-bom:$jacksonVersion"))

    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    testImplementation("org.assertj:assertj-core:$assertJVersion")
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}
