package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 22:13
 */
@Component("playerService")
public class PlayerServiceImpl implements Tick {

    private Trainer trainer;
    private Solver solver;

    @Autowired
    private PlayerController wsPlayerController;

    public PlayerServiceImpl() {
        trainer = new Trainer(new TasksImpl("1+1", "1+2", "2+2", "3+7", "10+12", "123+345"), new Calculator(), new Scores() {
            @Override
            public void add(int score) {
                System.out.println("Score added:" + score);
            }
        });

    }

    public void init() {
        solver = wsPlayerController.register("apofig");
        trainer.set(solver);
    }

    @Override
    public void tick() {
        trainer.tick();
    }
}
