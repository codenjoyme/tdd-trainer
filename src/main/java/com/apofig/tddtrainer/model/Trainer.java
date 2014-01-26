package com.apofig.tddtrainer.model;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:23
 */
public class Trainer implements Tick {

    private Solver calculator;
    private Tasks tasks;
    private Scores scores;
    private Solver solver;

    public Trainer(Tasks tasks, Solver calculator, Scores scores) {
        this.tasks = tasks;
        this.calculator = calculator;
        this.scores = scores;
    }

    public String getTask() {
        return tasks.getTask();
    }

    public void set(Solver solver) {
        this.solver = solver;
    }

    private void runSolver() {
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

    @Override
    public void tick() {
        if (solver == null) return;

        runSolver();
    }
}
