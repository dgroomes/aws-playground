plugins {
    id("dgroomes.common")
    `java-library`
}

dependencies {
    api(project(":echo"))
    api("com.amazonaws:aws-lambda-java-core")
    implementation("com.amazonaws:aws-lambda-java-events")
    runtimeOnly("com.amazonaws:aws-lambda-java-log4j2")
}

tasks {

    // This follows the recommendation in the AWS docs for building the ZIP file: https://docs.aws.amazon.com/lambda/latest/dg/java-package.html
    // I wanted to use the 'java-library-distribution' built-in Gradle plugin but it puts everything in a top-level directory
    // in the ZIP file and AWS doesn't recognize that format.
    register<Zip>("buildZip") {
        from(compileJava)
        from(processResources)
        into("lib") {
            from(configurations.runtimeClasspath)
        }
    }
}
