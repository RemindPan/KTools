package com.jiangkang.gradle.kotlin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class FileDemoTask : DefaultTask() {

    var destination: Any? = null

    fun getDestination(): File {
        return project.file(destination!!)
    }

    @TaskAction
    fun writeTxtToFile() {
        val file = getDestination()
        file.parentFile.mkdirs()
        file.writeText("Hello")
    }
}

