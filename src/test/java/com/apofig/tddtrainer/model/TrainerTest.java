package com.apofig.tddtrainer.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:21
 */
public class TrainerTest {

    private Trainer trainer;
    private Solver solver;

    @Before
    public void setup() {
        trainer = new Trainer();
        solver = mock(Solver.class);
    }

    private void assertCurrentTask(String expected) {
        assertEquals(expected, trainer.getTask());
    }

    private void shouldSolver(String value) {
        when(solver.solve(anyString())).thenReturn(value);
    }

    @Test
    public void shouldFirstTestFromListWhenStart() {
        assertCurrentTask("1+1=?");
    }

    @Test
    public void shouldSecondTaskWhenResolveFirst() {
        assertCurrentTask("1+1=?");

        shouldSolver("2");

        trainer.update(solver);

        assertCurrentTask("1+2=?");
    }

}