package com.apofig.tddtrainer.service;

import com.codenjoy.dojo.transport.screen.ScreenData;

import java.util.List;

/**
 * User: sanja
 * Date: 27.01.14
 * Time: 0:56
 */
public class PlayerData implements ScreenData {

    private int score;
    private List<String> testList;
    private List<String> info;

    private int leave;

    public PlayerData(int score, List<String> testList, List<String> info, int leave) {
        this.score = score;
        this.testList = testList;
        this.info = info;
        this.leave = leave;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getInfo() {
        return info;
    }

    public void setInfo(List<String> info) {
        this.info = info;
    }
}
