package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 30/11/2015.
 */
public class SGLargeStraightYahtzee extends ScoreGroup {
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ScoreGroup getNew() {
        return new SGLargeStraightYahtzee();
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
        r.add(Game.GameTypes.YAHTZEE);
        return r;
    }

    @Override
    public int getScore(int d1, int d2, int d3, int d4, int d5, ArrayList<ScoreGroup> availableMoves) {
        predictedScore = 0;
        description = "Large Straight ";
        int fixed=40;
        int ones = (d1==1 ? 1 : 0) + (d2==1 ? 1 : 0) + (d3==1 ? 1 : 0) + (d4==1 ? 1 : 0) + (d5==1 ? 1 : 0);
        int twos = (d1==2 ? 1 : 0) + (d2==2 ? 1 : 0) + (d3==2 ? 1 : 0) + (d4==2 ? 1 : 0) + (d5==2 ? 1 : 0);
        int threes = (d1==3 ? 1 : 0) + (d2==3 ? 1 : 0) + (d3==3 ? 1 : 0) + (d4==3 ? 1 : 0) + (d5==3 ? 1 : 0);
        int fours = (d1==4 ? 1 : 0) + (d2==4 ? 1 : 0) + (d3==4 ? 1 : 0) + (d4==4 ? 1 : 0) + (d5==4 ? 1 : 0);
        int fives = (d1==5 ? 1 : 0) + (d2==5 ? 1 : 0) + (d3==5 ? 1 : 0) + (d4==5 ? 1 : 0) + (d5==5 ? 1 : 0);
        int sixes = (d1==6 ? 1 : 0) + (d2==6 ? 1 : 0) + (d3==6 ? 1 : 0) + (d4==6 ? 1 : 0) + (d5==6 ? 1 : 0);
        if(ones>0 && twos>0 && threes>0 && fours>0 && fives>0){
            predictedScore = fixed;
            description += "1,2,3,4,5";
        }else if(twos>0 && threes>0 && fours>0 && fives>0 && sixes>0){
            predictedScore = fixed;
            description += "2,3,4,5,6";
        }
        return predictedScore;
    }
}
