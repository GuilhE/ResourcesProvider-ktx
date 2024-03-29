plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(AndroidConstants.compileSdkVersions)

    defaultConfig {
        minSdkVersion(AndroidConstants.minSdkVersion)
        targetSdkVersion(AndroidConstants.targetSdkVersion)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions { unitTests.isIncludeAndroidResources = true }
    sourceSets { get("test").resources.setSrcDirs(listOf("src/test/res")) }
}

dependencies {
    implementation(Libs.androix_core_ktx)
    implementation(Libs.androix_fragment_ktx)

    testImplementation(Libs.junit)
    testImplementation(Libs.androix_test)
    testImplementation(Libs.robolectric)
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}
val dokkaDoc by tasks.registering(org.jetbrains.dokka.gradle.DokkaTask::class) {
    outputDirectory.set(buildDir.resolve("javadoc"))
    dokkaSourceSets {
        named("main") {
            noAndroidSdkLink.set(false)
        }
    }
}
val dokkaJar by tasks.registering(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(dokkaDoc)
}
apply(from = rootProject.file("deploy-bintray.gradle.kts"))