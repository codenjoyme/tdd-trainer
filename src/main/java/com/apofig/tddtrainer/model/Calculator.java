package com.apofig.tddtrainer.model;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:38
 */
public class Calculator implements Solver {

    @Override
    public String solve(String task) {
        String[] split = task.split("\\+");
        int result = 0;
        for (String s : split) {
            result += getInteger(s);
        }
        return String.valueOf(result);
    }

    private Integer getInteger(String s) {
        if (s.startsWith("0x")) {
            return Integer.parseInt(s.substring(2, s.length()), 16);
        }
        if (s.startsWith("0b")) {
            return Integer.parseInt(s.substring(2, s.length()), 2);
        }
        if (s.startsWith("0")) {
            return Integer.parseInt(s.substring(1, s.length()), 8);
        }
        if (s.startsWith("0r")) {
            return 0; // TODO
        }
        return Integer.valueOf(s);
    }

}
