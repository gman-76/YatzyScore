package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class SGYatzy extends ScoreGroup {
    @Override
    public String getDescription() {
        return "Yatzy";
    }

    @Override
    public ScoreGroup getNew() {
        return new SGYatzy();
    }

    @Override
    public int getID() {
        return 14;
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
        predictedScore = 0;
        if(d1==d2 && d2==d3 && d3==d4 && d4==d5) predictedScore=50;
        return predictedScore;
    }
}
