package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.Solver;
import com.codenjoy.dojo.transport.PlayerResponseHandler;
import com.codenjoy.dojo.transport.TransportErrorType;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 21:39
 */
public class PlayerResponseHandlerImpl implements PlayerResponseHandler, Solver {

    private String answer = null;
    private Sender sender;

    public PlayerResponseHandlerImpl(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void onResponseComplete(String answer, Object context) {
        this.answer = answer;
    }

    @Override
    public void onError(TransportErrorType type, Object context) {
        answer = "";
    }

    @Override
    public String solve(String task) {
        sender.send(task);
        waitForAnswer();

        String result = answer;
        answer = null;
        return result;
    }

    private void waitForAnswer() {
        int counter = 0;
        while (answer == null && counter++ < 10) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}