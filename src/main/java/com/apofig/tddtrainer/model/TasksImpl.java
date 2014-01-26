package com.apofig.tddtrainer.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:44
 */
public class TasksImpl implements Tasks {

    private final List<String> tasks;

    public TasksImpl(String ... tasks) {
        this.tasks = new LinkedList<String>();
        this.tasks.addAll(Arrays.asList(tasks));
    }

    @Override
    public String getTask() {
        return tasks.get(0);
    }

    @Override
    public void solved() {
        tasks.remove(0);
    }
}
