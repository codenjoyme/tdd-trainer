package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.*;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 22:13
 */
public class PlayerServiceImpl implements Tick {

    private Trainer trainer;
    private Solver solver;
    private PlayerController wsPlayerController;

    public PlayerServiceImpl() {
        wsPlayerController = new WsPlayerController();
        solver = wsPlayerController.register("apofig");

        trainer = new Trainer(new TasksImpl("1+1", "1+2", "2+2", "3+7", "10+12", "123+345"), new Calculator(), new Scores() {
            @Override
            public void add(int score) {
                System.out.println("Score added:" + score);
            }
        });

        trainer.set(solver);
    }

    @Override
    public void tick() {
        trainer.tick();
    }
}
