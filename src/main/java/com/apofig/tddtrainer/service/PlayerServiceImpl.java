package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        List<String> tasks = new WithFile("com\\apofig\\tddtrainer\\tasks.txt").loadSplitted("\r\n");

        trainer = new Trainer(new TasksImpl(tasks), new Calculator(), new Scores() {
            @Override
            public void add(int score) {
                System.out.println("Score added:" + score);
            }
        });
    }

    @Override
    public void tick() {
        trainer.tick();
    }

    public void register(String playerName) {
        solver = wsPlayerController.register(playerName);
        trainer.set(solver);
    }
}
