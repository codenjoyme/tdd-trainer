package com.apofig.tddtrainer.service;

import com.apofig.tddtrainer.model.*;
import com.codenjoy.dojo.transport.screen.ScreenRecipient;
import com.codenjoy.dojo.transport.screen.ScreenSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * User: sanja
 * Date: 26.01.14
 * Time: 22:13
 */
@Component("playerService")
public class PlayerServiceImpl implements Tick {

    public static final int TIME = 10;
    private Trainer trainer;
    private Solver solver;
    private Player player;
    private List<String> info;
    private int score;
    private int counter;

    @Autowired private PlayerController wsPlayerController;
    @Autowired private ScreenSender<ScreenRecipient, PlayerData> screenSender;

    public PlayerServiceImpl() {
        List<String> tasks = new WithFile("com/apofig/tddtrainer/tasks.txt").loadSplitted("\r\n");
        info = new LinkedList<String>();

        trainer = new Trainer(new TasksImpl(tasks), new Calculator(), new Scores() {

            @Override
            public void add(int score) {
                info.add("" + score);
                PlayerServiceImpl.this.score += score;
                PlayerServiceImpl.this.score = Math.max(0, PlayerServiceImpl.this.score);
            }
        });
    }

    @Override
    public void tick() {
        if (++counter % TIME == 0) {
            trainer.tick();
        }
        int leave = TIME - counter % TIME;

        HashMap<ScreenRecipient, PlayerData> map = new HashMap<ScreenRecipient, PlayerData>();
        map.put(player, new PlayerData(score, trainer.getTestList(), info, leave));
        screenSender.sendUpdates(map);
        info.clear();
    }

    public void register(Player player) {
        this.player = player;
        solver = wsPlayerController.register(player.getName(), player.getCallbackUrl());
        trainer.set(solver);
    }

    public Player get(String playerName) {
        if (player == null || !player.getName().equals(playerName)) {
            return Player.NULL;
        }
        return player;
    }
}
