package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public abstract class ScoreGroup {
    private int score = -1;
    private boolean played = false;
    public boolean hasPlayed() {return played;}
    public void play(int s) { played=true; score=s; }
    public int getMarkedScore() { return score; };

    public abstract ScoreGroup getNew();
    public abstract int getID();
    public abstract boolean isUpperSection();
    public abstract ArrayList<Integer> getSupportedGameTypes();
    public abstract int getScore(int d1, int d2, int d3, int d4, int d5);
}
