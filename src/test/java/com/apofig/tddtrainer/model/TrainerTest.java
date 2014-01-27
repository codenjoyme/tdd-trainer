package com.apofig.tddtrainer.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 20:21
 */
public class TrainerTest {

    public static final int SUCCESS_SCORE = 100;
    public static final int FAIL_PENALTY = -100;
    private static final int REGRESSION_PENALTY = -1000;

    private Trainer trainer;
    private Solver solver;
    private Scores scores;

    @Before
    public void setup() {
        scores = mock(Scores.class);
        trainer = new Trainer(new TasksImpl(Arrays.asList("1+1", "1+2", "1+3", "1+4")), new Calculator(), scores);
        solver = mock(Solver.class);
        trainer.set(solver);
    }

    private void assertCurrentTask(String expected) {
        assertEquals(expected, trainer.getTask());
    }

    private void solverReturn(String... values) {
        OngoingStubbing<String> when = when(solver.solve(anyString()));
        for (String value : values) {
            when = when.thenReturn(value);
        }
    }

    @Test
    public void shouldFirstTestFromListWhenStart() {
        // then
        assertCurrentTask("1+1");
    }

    @Test
    public void shouldSecondTaskWhenResolveFirst() {
        // given
        assertCurrentTask("1+1");

        // when
        solverReturn("2");
        trainer.tick();

        // then
        assertCurrentTask("1+2");
    }

    @Test
    public void shouldStillFirstTaskWhenNotResolveFirst() {
        // given
        assertCurrentTask("1+1");

        // when
        solverReturn("3");
        trainer.tick();

        // then
        assertCurrentTask("1+1");
    }

    @Test
    public void shouldAddScoreWhenSolved() {
        // given, when
        shouldSecondTaskWhenResolveFirst();

        // then
        verify(scores).add(SUCCESS_SCORE);
    }

    @Test
    public void shouldRemoveScoreWhenNotSolved() {
        // given, when
        shouldStillFirstTaskWhenNotResolveFirst();

        // then
        verify(scores).add(FAIL_PENALTY);
    }

    @Test
    public void shouldDoNothingWhenSolverIsNull() {
        // given
        assertCurrentTask("1+1");

        // when
        trainer.set(null);
        trainer.tick();

        // then
        assertCurrentTask("1+1");
    }

    @Test
    public void shouldAskSolverWithAllTestsWhenUpdate() {
        // given
        solverReturn("2");
        assertCurrentTask("1+1");
        trainer.tick();

        assertCurrentTask("1+2");
        solverReturn("2", "3");
        trainer.tick();

        reset(solver);
        assertCurrentTask("1+3");

        // when
        solverReturn("2", "3");
        trainer.tick();

        // then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(solver, times(3)).solve(captor.capture());
        assertEquals("[1+1, 1+2, 1+3]", captor.getAllValues().toString());
    }

    @Test
    public void shouldRemoveScoresWhenRegressionFail() {
        // given
        solverReturn("2");
        assertCurrentTask("1+1");
        trainer.tick();
        reset(scores);

        assertCurrentTask("1+2");

        // when
        solverReturn("fail", "3");
        trainer.tick();

        // then
        verify(scores).add(REGRESSION_PENALTY);

        assertCurrentTask("1+2");
    }

    @Test
    public void shouldAskSolverWhenTick() {
        // given
        solverReturn("2");
        assertCurrentTask("1+1");
        trainer.tick();
        reset(solver);

        // when
        solverReturn("2");
        trainer.tick();

        // then
        verify(solver).solve("1+1");
    }

    @Test
    public void shouldReturnTestListWhenFail() {
        // given
        solverReturn("2");
        assertCurrentTask("1+1");
        trainer.tick();

        assertCurrentTask("1+2");
        solverReturn("2", "3");
        trainer.tick();

        assertCurrentTask("1+3");
        solverReturn("fail", "3", "4");

        // when
        trainer.tick();

        // then
        assertEquals("[1+1=2 fail, 1+2=3 success, 1+3=4 next]", trainer.getTestList().toString());
    }

    @Test
    public void shouldReturnTestListWhenNotFail() {
        // given
        solverReturn("2");
        assertCurrentTask("1+1");
        trainer.tick();

        assertCurrentTask("1+2");
        solverReturn("2", "3");
        trainer.tick();

        assertCurrentTask("1+3");
        solverReturn("2", "3", "4");

        // when
        trainer.tick();

        // then
        assertEquals("[1+1=2 success, 1+2=3 success, 1+3=4 success, 1+4=5 next]", trainer.getTestList().toString());
    }

    @Test
    public void shouldStillBadScoresWhenSecondTick() {
        // given
        assertCurrentTask("1+1");
        solverReturn("fail");
        trainer.tick();
        reset(scores);

        //when
        trainer.tick();

        // then
        assertCurrentTask("1+1");
        verify(scores).add(FAIL_PENALTY);
    }

    @Test
    public void shouldTestListNotChangeWhenTickAtFailTest() {
        // given
        assertCurrentTask("1+1");
        solverReturn("fail");
        trainer.tick();
        reset(scores);

        //when
        trainer.tick();
        trainer.tick();
        trainer.tick();
        solverReturn("2");
        trainer.tick();

        // then
        assertEquals("[1+1=2 success, 1+2=3 next]", trainer.getTestList().toString());
    }



}
