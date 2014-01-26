package com.apofig.tddtrainer.model;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:23
 */
public class Trainer {

    private Solver calculator;
    private Tasks tasks;
    private Scores scores;

    public Trainer(Tasks tasks, Solver calculator, Scores scores) {
        this.tasks = tasks;
        this.calculator = calculator;
        this.scores = scores;
    }

    public String getTask() {
        return tasks.getTask();
    }

    public void update(Solver solver) {
//        if (solver == null) return;   TODO потестить

        String actual = solver.solve(getTask());
        String expected = calculator.solve(getTask());
        if (expected.equals(actual)) {
            tasks.solved();
            scores.add(100);
        } else {
            scores.add(-100);
        }
    }
}
