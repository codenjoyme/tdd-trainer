package com.apofig.tddtrainer.model;

import java.util.LinkedList;
import java.util.List;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:44
 */
public class TasksImpl implements Tasks {

    private final List<String> tasks;
    private int index;

    public TasksImpl(List<String> tasks) {
        this.tasks = new LinkedList<String>();
        this.tasks.addAll(tasks);
        index = 0;
    }

    @Override
    public String getTask() {
        return tasks.get(index);
    }

    @Override
    public void solved() {
        index++;
    }

    @Override
    public List<String> oldTasks() {
        return tasks.subList(0, index);
    }
}
