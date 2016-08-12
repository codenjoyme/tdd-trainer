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
        if (s.startsWith("0r")) {
            return toArabic(s.substring(2, s.length()));
        }
        if (s.startsWith("0")) {
            return Integer.parseInt(s.substring(1, s.length()), 8);
        }
        return Integer.valueOf(s);
    }

    public static int toArabic(String number) {
        if (number.isEmpty()) return 0;
        if (number.startsWith("M")) return 1000 + toArabic(number.substring(1));
        if (number.startsWith("CM")) return 900 + toArabic(number.substring(2));
        if (number.startsWith("D")) return 500 + toArabic(number.substring(1));
        if (number.startsWith("CD")) return 400 + toArabic(number.substring(2));
        if (number.startsWith("C")) return 100 + toArabic(number.substring(1));
        if (number.startsWith("XC")) return 90 + toArabic(number.substring(2));
        if (number.startsWith("L")) return 50 + toArabic(number.substring(1));
        if (number.startsWith("XL")) return 40 + toArabic(number.substring(2));
        if (number.startsWith("X")) return 10 + toArabic(number.substring(1));
        if (number.startsWith("IX")) return 9 + toArabic(number.substring(2));
        if (number.startsWith("V")) return 5 + toArabic(number.substring(1));
        if (number.startsWith("IV")) return 4 + toArabic(number.substring(2));
        if (number.startsWith("I")) return 1 + toArabic(number.substring(1));
        throw new IllegalArgumentException("Что-то пошло не так");
    }

}
