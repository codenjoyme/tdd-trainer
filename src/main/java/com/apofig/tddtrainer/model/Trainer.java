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
        if (solver == null) return;

        if (isFailRegression(solver)) {
            scores.add(-1000);
            return;
        }

        if (isSolved(solver, tasks.getTask())) {
            tasks.solved();
            scores.add(100);
        } else {
            scores.add(-100);
        }
    }

    private boolean isFailRegression(Solver solver) {
        for (String oldTask : tasks.oldTasks()) {
            if (!isSolved(solver, oldTask)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSolved(Solver solver, String task) {
        String actual = solver.solve(task);
        String expected = calculator.solve(task);
        return expected.equals(actual);
    }
}
