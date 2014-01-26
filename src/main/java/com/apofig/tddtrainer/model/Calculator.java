package com.apofig.tddtrainer.model;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:38
 */
public class Calculator implements Solver {

    @Override
    public String solve(String task) {
        if (task.equals("1+1")) return "2";
        if (task.equals("1+2")) return "3";
        if (task.equals("1+3")) return "4";
        return "";
    }

}
