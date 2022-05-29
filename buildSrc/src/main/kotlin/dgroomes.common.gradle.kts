plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    constraints {
        implementation("org.apache.commons:commons-text:1.9")
    }

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

tasks {

    test {
        useJUnitPlatform()
    }
}
