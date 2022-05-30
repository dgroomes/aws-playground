plugins {
    id("dgroomes.common")
    application
}

dependencies {
    implementation(project(":hello-world-lambda"))
}

application {
    mainClass.set("dgroomes.runner.Runner")
}
