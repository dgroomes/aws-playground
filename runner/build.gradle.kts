plugins {
    id("dgroomes.common")
    application
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":hello-world-lambda"))
}

application {
    mainClass.set("dgroomes.runner.Runner")
}
