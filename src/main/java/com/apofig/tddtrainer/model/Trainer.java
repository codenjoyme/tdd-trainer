package com.apofig.tddtrainer.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:23
 */
public class Trainer {

    private Solver calculator;
    private Solver solver;
    private List<String> tasks = new LinkedList<String>();

    public Trainer() {
        tasks.addAll(Arrays.asList("1+1", "1+2"));
        calculator = new Calculator();
    }

    public String getTask() {
        return tasks.get(0);
    }

    public void update(Solver solver) {
        this.solver = solver;
//        if (solver == null) return;

        String actual = solver.solve(getTask());
        String expected = calculator.solve(getTask());
        if (expected.equals(actual)) {
            tasks.remove(0);
        }
    }
}
