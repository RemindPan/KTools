package com.jiangkang.gradle.kotlin


object Deps {

    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPluginVersion}"

    const val lottie = "com.airbnb.android:lottie:2.5.1"

    object Arch {

        object Lifecycle {
            const val runtime = "android.arch.lifecycle:runtime:${Versions.Arch.lifecycle}"
            const val compiler = "android.arch.lifecycle:compiler:${Versions.Arch.lifecycle}"
        }

    }

    object Support {
        const val compact = "com.android.support:support-compat:${Versions.supportVersion}"
        const val v4 = "com.android.support:support-v4:"
        const val v7 = "com.android.support:appcompat-v7:"
        const val degisn = "com.android.support:design:${Versions.supportVersion}"
        const val annotations = "com.android.support:support-annotations:${Versions.supportVersion}"
        const val recyclerview = "com.android.support:recyclerview-v7:${Versions.supportVersion}"
        const val cardview = "com.android.support:cardview-v7:${Versions.supportVersion}"
        const val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayoutVersion}"
        const val vector_drawable = "com.android.support:support-vector-drawable:${Versions.supportVersion}"
        const val multidex = "com.android.support:multidex:1.0.1"


        object Test {

            const val runner = "com.android.support.test:runner:1.0.2"
            const val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
        }


    }


}


