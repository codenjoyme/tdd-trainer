package com.apofig.tddtrainer.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * User: sanja
 * Date: 27.01.14
 * Time: 3:19
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void shouldCalcDec() {
        assertTask("2+2", "4");
        assertTask("1+1", "2");
        assertTask("1+2", "3");
        assertTask("2+2", "4");
        assertTask("3+4", "7");
        assertTask("5+6", "11");
        assertTask("6+7", "13");
        assertTask("7+8", "15");
        assertTask("8+9", "17");
        assertTask("10+1", "11");
        assertTask("11+1", "12");
        assertTask("22+10", "32");
        assertTask("22+22", "44");
        assertTask("300+1", "301");
        assertTask("1+300", "301");
        assertTask("300+300", "600");
        assertTask("1234+4321", "5555");
        assertTask("12345+12345", "24690");
    }

    @Test
    public void shouldCalcHex() {
        assertTask("0xA+1", "11");
        assertTask("0xB+1", "12");
        assertTask("0xC+1", "13");
        assertTask("0xD+1", "14");
        assertTask("0xE+1", "15");
        assertTask("0xF+1", "16");
        assertTask("1+0xA", "11");
        assertTask("1+0xB", "12");
        assertTask("1+0xC", "13");
        assertTask("1+0xD", "14");
        assertTask("1+0xE", "15");
        assertTask("1+0xF", "16");
        assertTask("0xA+0xA", "20");
        assertTask("0xA0+0x0A", "170");
        assertTask("0xA+16", "26");
        assertTask("34+0x5E", "128");
        assertTask("0x4FDA+0x45DF", "38329");
    }

    @Test
    public void shouldCalcBinary() {
        assertTask("0b1+0b1", "2");
        assertTask("0b1001+0b0110", "15");
        assertTask("0b1111+0x1", "16");
        assertTask("0xFFFF+0b1000100", "65603");
        assertTask("0b101010+123", "165");
    }

    @Test
    public void shouldCalcOctal() {
        assertTask("0324+0312", "414");
        assertTask("066666+1", "28087");
        assertTask("077777+1", "32768");
    }

    @Test
    public void shouldMultipleSum() {
        assertTask("0x2+02+0b10+2", "8");
        assertTask("0x2", "2");
    }

    @Test
    public void shouldCalcRoman() {
        assertTask("0rI+00", "1");
        assertTask("0rII+00", "2");
        assertTask("0rIII+00", "3");
        assertTask("0rIV+00", "4");
        assertTask("0rV+00", "5");
        assertTask("0rVI+00", "6");
        assertTask("0rVII+00", "7");
        assertTask("0rVIII+00", "8");
        assertTask("0rIX+00", "9");
        assertTask("0rX+00", "10");
        assertTask("0rXI+00", "11");
        assertTask("0rXII+00", "12");
        assertTask("0rXIII+00", "13");
        assertTask("0rXIV+00", "14");
        assertTask("0rXV+00", "15");
        assertTask("0rXVI+00", "16");
        assertTask("0rXVII+00", "17");
        assertTask("0rXVIII+00", "18");
        assertTask("0rXIX+00", "19");
        assertTask("0rXX+00", "20");
        assertTask("0rXXI+00", "21");
        assertTask("0rXXII+00", "22");
        assertTask("0rXXIII+00", "23");
        assertTask("0rXXIV+00", "24");
        assertTask("0rXXV+00", "25");
        assertTask("0rXXVI+00", "26");
        assertTask("0rXXVII+00", "27");
        assertTask("0rXXVIII+00", "28");
        assertTask("0rXXIX+00", "29");
        assertTask("0rXXX+00", "30");
        assertTask("0rXXXI+00", "31");
        assertTask("0rXLVI+00", "46");
        assertTask("0rXCIX+00", "99");
        assertTask("0rDLXXXIII+00", "583");
        assertTask("0rDCCCLXXXVIII+00", "888");
        assertTask("0rMDCLXVIII+00", "1668");
        assertTask("0rMCMLXXXIX+00", "1989");
        assertTask("0rMMMCMXCIX+00", "3999");
    }

    private void assertTask(String task, String expected) {
        assertEquals(expected, calculator.solve(task));
    }
}
