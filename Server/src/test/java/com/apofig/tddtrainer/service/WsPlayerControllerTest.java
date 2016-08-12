package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.Solver;
import com.codenjoy.dojo.transport.GameState;
import com.codenjoy.dojo.transport.PlayerTransport;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 23:14
 */
public class WsPlayerControllerTest {

    private WsPlayerController controller;
    private PlayerTransport transport;

    @Before
    public void setup() {
        // given
        controller = new WsPlayerController();

        transport = mock(PlayerTransport.class);
        controller.setTransport(transport);
    }

    @Test
    public void shouldCreateSolverWhenRegister() throws IOException {
        // when
        Solver solver = controller.register("player", "127.0.0.1");

        // then
        verify(transport).registerPlayerEndpoint("player", (PlayerResponseHandlerImpl)solver, "127.0.0.1");
    }

    @Test
    public void shouldSendTask() throws IOException {
        // given
        Solver solver = controller.register("player", "127.0.0.1");

        // when
        solver.solve("task");

        // then
        ArgumentCaptor<GameState> captor = ArgumentCaptor.forClass(GameState.class);
        verify(transport).sendState(Mockito.eq("player"), captor.capture());
        assertEquals("task", captor.getValue().asString());
    }

    @Test
    public void shouldUnregister() throws IOException {
        // when
        controller.unregister("player");

        // then
        verify(transport).unregisterPlayerEndpoint("player");
    }

    @Test
    public void shouldAnswer() throws IOException {
        // given
        Solver solver = controller.register("player", "127.0.0.1");

        // when
        ((PlayerResponseHandlerImpl)solver).onResponseComplete("answer", null);
        String answer = solver.solve("task");

        // then
        assertEquals("answer", answer);
    }

    @Test
    public void shouldWaitForAnswer() throws IOException {
        // given
        Solver solver = controller.register("player", "127.0.0.1");

        // when
        String answer = solver.solve("task");

        // then
        assertNull(answer);
    }

}
