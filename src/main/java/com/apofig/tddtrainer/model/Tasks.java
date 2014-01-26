package com.apofig.tddtrainer.model;

import java.util.List;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:42
 */
public interface Tasks {

    String getTask();

    void solved();

    List<String> oldTasks();

}
