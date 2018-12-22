package com.jiangkang.gradle.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project


open class HelloWorldPluginExtension {
    var msg: String? = null
    var code: String? = null
}

/*
* 一般的项目插件
* */
class HelloWorldPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extensions = project.extensions.create<HelloWorldPluginExtension>("greeting", HelloWorldPluginExtension::class.java)
        project.run {
            task("hello").doLast {
                println("Hello World!")
                println("msg = ${extensions.msg}")
                println("code = ${extensions.code}")
            }
        }
    }
}

/*
* 初始化 Settings脚本
* */
//class HelloWorldPlugin : Plugin<Settings> {
//    override fun apply(settings: Settings) {
//        println("------------------------Settings Script Start--------------------------------")
//        println("settingsDir = ${settings.settingsDir}")
//        println("rootDir = ${settings.rootDir}")
//        println("------------------------Settings Script  End --------------------------------")
//    }
//
//}

/*
* 初始化脚本
* */
//class HelloWorldPlugin : Plugin<Gradle> {
//    override fun apply(gradle: Gradle) {
//        gradle.addBuildListener(object : BuildListener {
//            override fun settingsEvaluated(settings: Settings) {
//                println("---------------settingsEvaluated() Start-----------------------")
//                println("---------------settingsEvaluated() Start-----------------------")
//            }
//
//            override fun buildFinished(result: BuildResult) {
//                println("----------------构建完成-----------------------------")
//            }
//
//            override fun projectsLoaded(gradle: Gradle) {
//                println("----------------项目加载完成-----------------------------")
//            }
//
//            override fun buildStarted(gradle: Gradle) {
//                println("----------------构建开始-----------------------------")
//            }
//
//            override fun projectsEvaluated(gradle: Gradle) {
//                println("----------------项目执行完毕-----------------------------")
//            }
//
//        })
//    }
//
//}


