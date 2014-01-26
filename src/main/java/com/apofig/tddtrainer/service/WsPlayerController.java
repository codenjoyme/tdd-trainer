package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.Solver;
import com.codenjoy.dojo.transport.GameState;
import com.codenjoy.dojo.transport.PlayerTransport;
import com.codenjoy.dojo.transport.ws.WebSocketPlayerTransport;

import java.io.IOException;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 21:37
 */
public class WsPlayerController implements PlayerController {

    private PlayerTransport transport = new WebSocketPlayerTransport();

    @Override
    public void requestControl(String player, final String task) throws IOException {
        transport.sendState(player, new GameState() {
            @Override
            public String asString() {
                return task;
            }
        });
    }

    @Override
    public Solver register(String player) {
        PlayerResponseHandlerImpl solver = new PlayerResponseHandlerImpl();
        transport.registerPlayerEndpoint(player, solver, "127.0.0.1");
        return solver;
    }

    @Override
    public void unregister(String player) {
        transport.unregisterPlayerEndpoint(player);
    }
}
