package com.apofig.tddtrainer.model;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:23
 */
public class Trainer {

    private Solver calculator;
    private Solver solver;
    private Tasks tasks;

    public Trainer(Tasks tasks, Solver calculator) {
        this.tasks = tasks;
        this.calculator = calculator;
    }

    public String getTask() {
        return tasks.getTask();
    }

    public void update(Solver solver) {
        this.solver = solver;
//        if (solver == null) return;

        String actual = solver.solve(getTask());
        String expected = calculator.solve(getTask());
        if (expected.equals(actual)) {
            tasks.solved();
        }
    }
}
