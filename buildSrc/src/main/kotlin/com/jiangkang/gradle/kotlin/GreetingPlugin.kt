package com.jiangkang.gradle.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Property


//open class GreetingPluginExtension(
//        private val msgProp: Property<String>,
//        private val configurableOutputFiles: ConfigurableFileCollection
//) {
//    var msg by msgProp
//    var outputFiles: FileCollection by configurableOutputFiles
//
//    constructor(project: Project) : this(
//            project.objects.property<String>(String::class.java),
//            project.layout.configurableFiles()
//    ) {
//
//    }
//
//}
//
//
//class GreetingPlugin : Plugin<Project> {
//
//    override fun apply(project: Project) {
//
//    }
//
//}