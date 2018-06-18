package com.jiangkang.gradle.kotlin

import groovy.lang.Closure
import jdk.internal.dynalink.linker.LinkerServices
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.ComponentMetadataHandler
import org.gradle.api.artifacts.dsl.ComponentModuleMetadataHandler
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.query.ArtifactResolutionQuery
import org.gradle.api.artifacts.transform.VariantTransform
import org.gradle.api.artifacts.type.ArtifactTypeContainer
import org.gradle.api.attributes.AttributesSchema
import org.gradle.api.initialization.Settings
import org.omg.CORBA.Object


object Deps {

    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPluginVersion}"

    object Arch {

        object Lifecycle {
            const val runtime = "android.arch.lifecycle:runtime:${Versions.Arch.lifecycle}"
            const val compiler = "android.arch.lifecycle:compiler:${Versions.Arch.lifecycle}"
        }

    }


    fun getDeps(project:Project){
//        project.dependencies.
    }

}


