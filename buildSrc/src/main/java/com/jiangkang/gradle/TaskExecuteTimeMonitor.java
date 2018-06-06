package com.jiangkang.gradle;

import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionListener;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.tasks.TaskState;

import java.util.HashMap;
import java.util.Map;

public class TaskExecuteTimeMonitor implements TaskExecutionListener, BuildListener {

    private long timeStart;

    private long timeEnd;

    private HashMap<String, String> taskInfoMap = new HashMap<>();


    @Override
    public void beforeExecute(Task task) {
        timeStart = System.currentTimeMillis();
    }

    @Override
    public void afterExecute(Task task, TaskState taskState) {
        timeEnd = System.currentTimeMillis();
        long time = timeEnd - timeStart;
        if (time > 100){
            taskInfoMap.put(task.getPath(), String.valueOf(time) + "ms");
        }
    }

    @Override
    public void buildStarted(Gradle gradle) {

    }

    @Override
    public void settingsEvaluated(Settings settings) {

    }

    @Override
    public void projectsLoaded(Gradle gradle) {

    }

    @Override
    public void projectsEvaluated(Gradle gradle) {

    }

    @Override
    public void buildFinished(BuildResult buildResult) {
        System.out.println("执行时间大于100ms的任务");
        for (Map.Entry<String, String> entry : taskInfoMap.entrySet()
                ) {
            System.out.println("       " + entry.getValue() + "  " + entry.getKey());
        }
    }
}
