package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class SGChance extends ScoreGroup {
    @Override
    public String getDescription() {
        return "Chance";
    }

    @Override
    public ScoreGroup getNew() {
        return new SGChance();
    }

    @Override
    public int getID() {
        return 13;
    }

    @Override
    public boolean isUpperSection() {
        return false;
    }

    @Override
    public ArrayList<Integer> getSupportedGameTypes() {
        ArrayList<Integer> r = new ArrayList<>();
        r.add(Game.GameTypes.YATZY);
        r.add(Game.GameTypes.YAHTZEE);
        return r;
    }

    @Override
    public int getScore(int d1, int d2, int d3, int d4, int d5, ArrayList<ScoreGroup> availableMoves) {
        predictedScore = d1+d2+d3+d4+d5;
        return predictedScore;
    }
}
