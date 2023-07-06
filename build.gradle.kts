// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version ("1.8.10") apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}
true