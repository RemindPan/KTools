package com.jiangkang.gradle.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import static java.lang.System.out;

public class HelloWorldTask extends DefaultTask {


    @TaskAction
    public void sayHello() {
        out.println("Hello World");
    }


}
