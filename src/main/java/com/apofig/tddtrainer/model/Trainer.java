package com.apofig.tddtrainer.model;

import java.util.LinkedList;
import java.util.List;

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
    private List<Boolean> testResult;

    public Trainer(Tasks tasks, Solver calculator, Scores scores) {
        this.tasks = tasks;
        this.calculator = calculator;
        this.scores = scores;
        testResult = new LinkedList<Boolean>();
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

        String task = tasks.getTask();
        boolean solved = isSolved(solver, task);

        updateLastTestResult(solved);

        if (solved) {
            tasks.solved();
            scores.add(100);
        } else {
            scores.add(-100);
        }
    }

    private void updateLastTestResult(boolean solved) {
        if (testResult.size() == tasks.oldTasks().size()) {
            testResult.add(solved);
        } else {
            testResult.set(testResult.size() - 1, solved);
        }
    }

    private boolean isFailRegression(Solver solver) {
        List<String> list = tasks.oldTasks();
        for (int index = 0; index < list.size(); index++) {
            testResult.set(index, isSolved(solver, list.get(index)));
        }

        for (int index = 0; index < list.size(); index++) {
            if (!testResult.get(index)) return true;
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

    public List<String> getTestList() {
        List<String> result = new LinkedList<String>();

        List<String> list = tasks.oldTasks();
        for (int index = 0; index < list.size(); index++) {
            result.add(info(list.get(index)) + " " + getString(testResult.get(index)));
        }
        result.add(info(tasks.getTask()) + " " + "next");

        return result;
    }

    private String info(String task) {
        return task + "=" + calculator.solve(task);
    }

    private String getString(Boolean b) {
        return b ? "success" : "fail";
    }
}
