package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.Solver;
import com.codenjoy.dojo.transport.GameState;
import com.codenjoy.dojo.transport.PlayerTransport;

import java.io.IOException;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 21:37
 */
public class WsPlayerController implements PlayerController {

    private PlayerTransport transport;

    @Override
    public void requestControl(String player, final String task) {
        try {
            transport.sendState(player, new GameState() {
                @Override
                public String asString() {
                    return task;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Solver register(final String player, String ip) {
        PlayerResponseHandlerImpl solver = new PlayerResponseHandlerImpl(new Sender() {
            @Override
            public void send(String task) {
                requestControl(player, task);
            }
        });
        transport.registerPlayerEndpoint(player, solver, ip);
        return solver;
    }

    @Override
    public void unregister(String player) {
        transport.unregisterPlayerEndpoint(player);
    }

    public void setTransport(PlayerTransport transport) {
        this.transport = transport;
    }
}
