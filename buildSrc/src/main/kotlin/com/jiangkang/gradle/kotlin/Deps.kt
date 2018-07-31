package com.jiangkang.gradle.kotlin

import com.jiangkang.gradle.kotlin.Versions.frescoVersion


object Deps {

    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPluginVersion}"

    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"

    const val aspectj = "org.aspectj:aspectjtools:1.9.1"
    const val aspectj_weaver = "org.aspectj:aspectjweaver:1.9.1"

    const val greendaoPlugin = "org.greenrobot:greendao-gradle-plugin:3.2.2"

    const val gradle_dependencies_graph_plugin = "com.vanniktech:gradle-dependency-graph-generator-plugin:0.4.0"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"

    const val anko = "org.jetbrains.anko:anko:0.10.4"

    const val lottie = "com.airbnb.android:lottie:2.5.1"

    const val junit = "junit:junit:4.12"

    const val robolectric = "org.robolectric:robolectric:3.6.1"

    const val debug_db = "com.amitshekhar.android:debug-db:1.0.0"

    const val anrwatchdog = "com.github.anrwatchdog:anrwatchdog:1.3.0"

    const val gson ="com.google.code.gson:gson:2.8.4"

    object Arch {

        object Lifecycle {
            const val runtime = "android.arch.lifecycle:runtime:${Versions.Arch.lifecycle}"
            const val compiler = "android.arch.lifecycle:compiler:${Versions.Arch.lifecycle}"
            const val extentions = "android.arch.lifecycle:extensions:${Versions.Arch.lifecycle}"
        }

        object Room {

            const val runtime = "android.arch.persistence.room:runtime:${Versions.Arch.persistence}"
            const val compiler = "android.arch.persistence.room:compiler:${Versions.Arch.persistence}"
            const val rxjava2 = "android.arch.persistence.room:rxjava2:${Versions.Arch.persistence}"

        }

        object Navigation {
            const val ui = "android.arch.navigation:navigation-ui:${Versions.Arch.navigation}"
            const val fragment = "android.arch.navigation:navigation-fragment:${Versions.Arch.navigation}"
        }

        const val workmanager = "ndroid.arch.work:work-runtime:${Versions.Arch.work}"

        const val paging = "android.arch.paging:runtime:${Versions.Arch.paging}"

    }

    object Support {
        const val compact = "com.android.support:support-compat:${Versions.supportVersion}"
        const val v4 = "com.android.support:support-v4:${Versions.supportVersion}"
        const val v7 = "com.android.support:appcompat-v7:${Versions.supportVersion}"
        const val design = "com.android.support:design:${Versions.supportVersion}"
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

    object OkHttp {

        const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
        const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttpVersion}"
        const val logginginterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

        const val chuck = "com.readystatesoftware.chuck:library:1.1.0"
        const val chuck_no_op = "com.readystatesoftware.chuck:library-no-op:1.1.0"
    }

    object LeakCanary {

        const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryVersion}"

        const val leakcanary_no_op = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakcanaryVersion}"

    }

    object ButterKnife {
        const val butterknife = "com.jakewharton:butterknife:${Versions.butterKnifeVersion}"
        const val compiler = "com.jakewharton:butterknife-compiler:${Versions.butterKnifeVersion}"
    }

    object RxJava {

        const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
        const val rxanroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"

    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val convert_json = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    }

    object Dagger {
        const val dagger_android = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
        const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
        const val compiler = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"

    }


    object Glide {

        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val okhttp = "com.github.bumptech.glide:okhttp3-integration:4.0.0"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    }

    object EventBus {

        const val eventbus = "org.greenrobot:eventbus:${Versions.eventbusVersion}"
        const val compiler = "org.greenrobot:eventbus-annotation-processor:${Versions.eventbusVersion}"

    }

    object Fresco {
        const val fresco = "com.facebook.fresco:fresco:$frescoVersion"

        //GIF
        const val animated_gif = "com.facebook.fresco:animated-gif:$frescoVersion"

        //WebP静态图
        const val webpsupport = "com.facebook.fresco:webpsupport:$$frescoVersion"

        //WebP动态图
        const val animated_webp = "com.facebook.fresco:animated-webp:$frescoVersion"
    }


    object ARouter {
        const val api = "com.alibaba:arouter-api:1.2.4"
        const val compiler = "com.alibaba:arouter-compiler:1.1.4"
    }

}


