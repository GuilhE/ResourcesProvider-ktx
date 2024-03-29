import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

object AndroidConstants {
    const val minSdkVersion = 23
    const val targetSdkVersion = 30
    const val compileSdkVersions = 30
    const val buildToolsVersion = "30.0.2"
}

object Versions {
    const val build_gradle_version = "4.0.1"
    const val kotlin_gradle_plugin_version = "1.4.0"
    const val bintray_plugin_version = "1.8.5"

    const val bintray_version = "1.8.5"
    const val dokka_version = "1.4.0"

    const val kotlin_jdk_version = "1.4.0"
    const val androix_core_ktx_version = "1.3.1"
    const val androix_fragment_ktx_version = "1.2.5"

    const val junit_version = "4.13"
    const val androix_core_test_version = "1.3.0"
    const val robolectric_version = "4.4"
}

object Libs {
    const val com_jfrog_bintray = "com.jfrog.bintray.gradle"
    const val maven_publish = "maven-publish"
    const val org_jetbrains_dokka = "org.jetbrains.dokka"

    const val com_android_tools_build_gradle = "com.android.tools.build:gradle:${Versions.build_gradle_version}"
    const val org_jetbrains_kotlin_kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_gradle_plugin_version}"
    const val com_jfrog_bintray_gradle_bintray_plugin = com_jfrog_bintray + ":gradle-bintray-plugin:${Versions.bintray_plugin_version}"
    const val dokka_gradle_plugin = org_jetbrains_dokka + ":dokka-gradle-plugin:${Versions.dokka_version}"

    const val kotlin_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_jdk_version}"
    const val androix_core_ktx = "androidx.core:core-ktx:${Versions.androix_core_ktx_version}"
    const val androix_fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.androix_fragment_ktx_version}"
    const val androix_test = "androidx.test:core:${Versions.androix_core_test_version}"
    const val junit = "junit:junit:${Versions.junit_version}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric_version}"
}

val PluginDependenciesSpec.mavenPublish: PluginDependencySpec
    inline get() = id(Libs.maven_publish)

val PluginDependenciesSpec.dokka: PluginDependencySpec
    inline get() = id(Libs.org_jetbrains_dokka).version(Versions.dokka_version)

val PluginDependenciesSpec.bintray: PluginDependencySpec
    inline get() = id(Libs.com_jfrog_bintray).version(Versions.bintray_version)