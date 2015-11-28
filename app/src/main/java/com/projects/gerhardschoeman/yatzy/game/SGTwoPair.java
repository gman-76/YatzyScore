package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class SGTwoPair extends ScoreGroup {
    @Override
    public String getDescription() {
        return "Two pair";
    }

    @Override
    public ScoreGroup getNew() {
        return new SGTwoPair();
    }

    @Override
    public int getID() {
        return 7;
    }

    @Override
    public boolean isUpperSection() {
        return false;
    }

    @Override
    public ArrayList<Integer> getSupportedGameTypes() {
        ArrayList<Integer> r = new ArrayList<>();
        r.add(Game.GameTypes.YATZY);
        return r;
    }

    @Override
    public int getScore(int d1, int d2, int d3, int d4, int d5, ArrayList<ScoreGroup> availableMoves) {
        predictedScore = 0;
        return predictedScore;
    }
}
