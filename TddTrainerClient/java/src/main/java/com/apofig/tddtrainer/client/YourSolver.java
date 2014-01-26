package com.apofig.tddtrainer.client;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 21:55
 */
public class YourSolver implements Solver {
    @Override
    public String solve(String task) {
        String[] split = task.split("\\+");
        Integer i1 = Integer.valueOf(split[0]);
        Integer i2 = Integer.valueOf(split[1]);
        return String.valueOf(i1 + i2);
    }
}
