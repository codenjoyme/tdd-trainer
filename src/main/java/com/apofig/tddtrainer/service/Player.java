package com.apofig.tddtrainer.service;

import com.codenjoy.dojo.transport.screen.ScreenRecipient;

/**
 * User: oleksandr.baglai
 * Date: 10/1/12
 * Time: 3:49 AM
 */
public class Player implements ScreenRecipient {
    public static final Player NULL = new NullPlayer();

    private String name;
    private String callbackUrl;

    public Player() {
    }

    public Player(String name, String callbackUrl) {
        this.name = name;
        this.callbackUrl = callbackUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == Player.NULL && (o != Player.NULL)) return false;

        if (o instanceof Player) {
            Player p = (Player)o;

            return (p.name.equals(name));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
