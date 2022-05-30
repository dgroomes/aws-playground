plugins {
    java
}

val jacksonVersion = "2.13.1" // Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
val awsLambdaJavaCoreVersion =
    "1.2.1" // AWS Lambda Java releases: https://docs.aws.amazon.com/lambda/latest/dg/java-package.html
val awsLambdaJavaEventsVersion =
    "3.11.0" // AWS Lambda Java releases: https://docs.aws.amazon.com/lambda/latest/dg/java-package.html
val awsLambdaJavaLog4j2Version =
    "1.5.1" // AWS Lambda Java releases: https://docs.aws.amazon.com/lambda/latest/dg/java-package.html


val junitJupiterVersion = "5.8.2" // JUnit releases: https://junit.org/junit5/docs/current/release-notes/index.html
val assertJVersion = "3.22.0" // AssertJ releases: https://github.com/assertj/assertj-core/tags

repositories {
    mavenCentral()
}

java {
    toolchain {
        // We're stuck with Java 11 until AWS supports 17.
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

dependencies {
    constraints {
        implementation("com.amazonaws:aws-lambda-java-core:$awsLambdaJavaCoreVersion")
        implementation("com.amazonaws:aws-lambda-java-events:$awsLambdaJavaEventsVersion")
        runtimeOnly("com.amazonaws:aws-lambda-java-log4j2:$awsLambdaJavaLog4j2Version")
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
