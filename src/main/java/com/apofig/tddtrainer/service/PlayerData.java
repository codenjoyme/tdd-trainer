package com.apofig.tddtrainer.service;

import com.codenjoy.dojo.transport.screen.ScreenData;

/**
 * User: sanja
 * Date: 27.01.14
 * Time: 0:56
 */
public class PlayerData implements ScreenData {

    private int score;
    private String info;

    public PlayerData(int score, String info) {
        this.score = score;
        this.info = info;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
