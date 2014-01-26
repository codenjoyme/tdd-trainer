package com.apofig.tddtrainer.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:21
 */
public class TrainerTest {

    private Trainer trainer;
    private Solver solver;
    private Scores scores;

    @Before
    public void setup() {
        scores = mock(Scores.class);
        trainer = new Trainer(new TasksImpl("1+1", "1+2", "1+3"), new Calculator(), scores);
        solver = mock(Solver.class);
    }

    private void assertCurrentTask(String expected) {
        assertEquals(expected, trainer.getTask());
    }

    private void solverReturn(String value) {
        when(solver.solve(anyString())).thenReturn(value);
        trainer.update(solver);
    }

    @Test
    public void shouldFirstTestFromListWhenStart() {
        assertCurrentTask("1+1");
    }

    @Test
    public void shouldSecondTaskWhenResolveFirst() {
        assertCurrentTask("1+1");

        solverReturn("2");

        trainer.update(solver);

        assertCurrentTask("1+2");
    }

    @Test
    public void shouldStillFirstTaskWhenNotResolveFirst() {
        assertCurrentTask("1+1");

        solverReturn("3");

        assertCurrentTask("1+1");
    }

    @Test
    public void shouldAddScoreWhenSolved() {
        shouldSecondTaskWhenResolveFirst();

        verify(scores).add(100);
    }

    @Test
    public void shouldRemoveScoreWhenNotSolved() {
        shouldStillFirstTaskWhenNotResolveFirst();

        verify(scores).add(-100);
    }

    @Test
    public void shouldDoNothingWhenSolverIsNull() {
        assertCurrentTask("1+1");

        trainer.update(null);

        assertCurrentTask("1+1");
    }


}
