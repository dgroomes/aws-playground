plugins {
    id("dgroomes.common")
    application
}

dependencies {
    runtimeOnly("org.slf4j:slf4j-simple")
    implementation(project(":hello-world-lambda"))
    implementation("org.apache.httpcomponents.client5:httpclient5")
    implementation("com.fasterxml.jackson.core:jackson-databind")
}

application {
    mainClass.set("dgroomes.runner.LambdaSimulator")
}
