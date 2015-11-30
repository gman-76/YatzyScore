package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class SGLargeStraight extends ScoreGroup {
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ScoreGroup getNew() {
        return new SGLargeStraight();
    }

    @Override
    public int getID() {
        return 11;
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
        description = "Large Straight ";
        if(
                (d1==2 || d2==2 || d3==2 || d4==2 || d5==2)
                && (d1==3 || d2==3 || d3==3 || d4==3 || d5==3)
                && (d1==4 || d2==4 || d3==4 || d4==4 || d5==4)
                && (d1==5 || d2==5 || d3==5 || d4==5 || d5==5)
                && (d1==6 || d2==6 || d3==6 || d4==6 || d5==6)){
            description += "2,3,4,5,6";
            predictedScore=20;
        }
        return predictedScore;
    }
}
