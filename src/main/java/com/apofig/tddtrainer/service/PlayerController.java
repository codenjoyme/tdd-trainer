package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.Solver;

import java.io.IOException;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 21:36
 */
public interface PlayerController {
    void requestControl(String player, String task) throws IOException;

    Solver register(String player, String ip);

    void unregister(String player);
}