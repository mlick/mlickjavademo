package com.mlick.javademo.java8.futures.example;

import com.mlick.javademo.java8.futures.Task;

import java.util.concurrent.CountDownLatch;

/**
 * Example Task
 */
public class StringInputTask implements Task<String, String> {
    private final String taskName;

    public StringInputTask(final String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String process(final String input, final CountDownLatch latch) {

        //Some process that takes time
        try {
            Thread.sleep(3000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("Done: " + taskName);
        latch.countDown();
        return String.format("Task %s - %s - complete - Yay!", taskName, input);
    }

    @Override
    public String toString() {
        return "StringInputTask{" +
                "taskName='" + taskName + '\'' +
                '}';
    }
}
