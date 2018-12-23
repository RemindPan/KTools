package com.jiangkang.gradle.kotlin

import com.jiangkang.gradle.kotlin.Versions.frescoVersion


object Deps {

    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPluginVersion}"

    const val androidRuntime = "com.google.android:android:4.1.1.4"

    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"

    val aspectj = "org.aspectj:aspectjtools:1.9.1"
    val aspectj_weaver = "org.aspectj:aspectjweaver:1.9.1"

    val greendaoPlugin = "org.greenrobot:greendao-gradle-plugin:3.2.2"

    val gradle_dependencies_graph_plugin = "com.vanniktech:gradle-dependency-graph-generator-plugin:0.4.0"

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"

    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1"

    val anko = "org.jetbrains.anko:anko:${Versions.anko_version}"

    val lottie = "com.airbnb.android:lottie:2.5.1"

    val junit = "junit:junit:4.12"

    val robolectric = "org.robolectric:robolectric:3.6.1"

    val debug_db = "com.amitshekhar.android:debug-db:1.0.0"

    val anrwatchdog = "com.github.anrwatchdog:anrwatchdog:1.3.0"

    val gson = "com.google.code.gson:gson:2.8.4"

    val javapoet = "com.squareup:javapoet:${Versions.javapoet_version}"

    val greendao = "org.greenrobot:greendao:${Versions.greendaoVersion}"

    val zxing = "com.google.zxing:core:3.3.0"

    val logger = "com.orhanobut:logger:${Versions.loggerVersion}"

    object Arch {

        object Lifecycle {
            val runtime = "android.arch.lifecycle:runtime:${Versions.Arch.lifecycle}"
            val compiler = "android.arch.lifecycle:compiler:${Versions.Arch.lifecycle}"
            val extentions = "android.arch.lifecycle:extensions:${Versions.Arch.lifecycle}"
        }

        object Room {

            val runtime = "android.arch.persistence.room:runtime:${Versions.Arch.persistence}"
            val compiler = "android.arch.persistence.room:compiler:${Versions.Arch.persistence}"
            val rxjava2 = "android.arch.persistence.room:rxjava2:${Versions.Arch.persistence}"

        }

        object Navigation {
            val ui = "android.arch.navigation:navigation-ui:${Versions.Arch.navigation}"
            val fragment = "android.arch.navigation:navigation-fragment:${Versions.Arch.navigation}"
        }

        val workmanager = "android.arch.work:work-runtime:${Versions.Arch.work}"

        val paging = "android.arch.paging:runtime:${Versions.Arch.paging}"

    }

    object Support {
        val compact = "com.android.support:support-compat:${Versions.supportVersion}"
        val v4 = "com.android.support:support-v4:${Versions.supportVersion}"
        val v7 = "com.android.support:appcompat-v7:${Versions.supportVersion}"
        val design = "com.android.support:design:${Versions.supportVersion}"
        val supportAnnotations = "com.android.support:support-annotations:${Versions.supportVersion}"
        val recyclerview = "com.android.support:recyclerview-v7:${Versions.supportVersion}"
        val cardview = "com.android.support:cardview-v7:${Versions.supportVersion}"
        val constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayoutVersion}"
        val vector_drawable = "com.android.support:support-vector-drawable:${Versions.supportVersion}"
        val multidex = "com.android.support:multidex:1.0.1"
        val dynamic_animation = "com.android.support:support-dynamic-animation:${Versions.supportVersion}"

        object Test {
            val runner = "com.android.support.test:runner:1.0.2"
            val rulers = "com.android.support.test:rules:1.0.2"
            val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
        }


    }

    object OkHttp {

        val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
        val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttpVersion}"
        val logginginterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

        val chuck = "com.readystatesoftware.chuck:library:1.1.0"
        val chuck_no_op = "com.readystatesoftware.chuck:library-no-op:1.1.0"
    }

    object LeakCanary {

        val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanaryVersion}"

        val leakcanary_no_op = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakcanaryVersion}"

    }

    object ButterKnife {
        val butterknife = "com.jakewharton:butterknife:${Versions.butterKnifeVersion}"
        val compiler = "com.jakewharton:butterknife-compiler:${Versions.butterKnifeVersion}"
    }

    object RxJava {

        val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
        val rxanroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"

    }

    object Retrofit {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        val convert_json = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        val adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    }

    object Dagger {
        val dagger_android = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
        val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
        val compiler = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"

    }


    object Glide {

        val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        val okhttp = "com.github.bumptech.glide:okhttp3-integration:4.0.0"
        val compiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    }

    object EventBus {

        val eventbus = "org.greenrobot:eventbus:${Versions.eventbusVersion}"
        val compiler = "org.greenrobot:eventbus-annotation-processor:${Versions.eventbusVersion}"

    }

    object Fresco {
        val fresco = "com.facebook.fresco:fresco:$frescoVersion"

        //GIF
        val animated_gif = "com.facebook.fresco:animated-gif:$frescoVersion"

        //WebP静态图
        val webpsupport = "com.facebook.fresco:webpsupport:$$frescoVersion"

        //WebP动态图
        val animated_webp = "com.facebook.fresco:animated-webp:$frescoVersion"
    }


    object ARouter {
        val api = "com.alibaba:arouter-api:1.2.4"
        val compiler = "com.alibaba:arouter-compiler:1.1.4"
    }

    object Test {
        val mockito = "org.mockito:mockito-core:2.10.0"
    }

    object Auto {
        val service = "com.google.auto.service:auto-service:1.0-rc4"
        val common = "com.google.auto:auto-common:0.9"
    }


}


