plugins {
    id("dgroomes.common")
    `java-library`
}

dependencies {
    api(project(":echo"))
}

tasks {
    val buildLambdaDistribution = register<Zip>("buildLambdaDistribution") {
        val sourceSet = sourceSets.main.get().runtimeClasspath
        from(sourceSet)
    }

    named("build") {
        dependsOn(buildLambdaDistribution)
    }
}
