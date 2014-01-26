package com.apofig.tddtrainer.model;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:23
 */
public class Trainer {

    private Solver solver;

    public String getTask() {
        if (solver != null) {
            return "1+2=?";
        }
        return "1+1=?";
    }

    public void update(Solver solver) {
        this.solver = solver;
    }
}
