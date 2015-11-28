package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class SGFullHouse extends ScoreGroup {
    @Override
    public String getDescription() {
        return "Full House";
    }

    @Override
    public ScoreGroup getNew() {
        return new SGFullHouse();
    }

    @Override
    public int getID() {
        return 12;
    }

    @Override
    public boolean isUpperSection() {
        return false;
    }

    @Override
    public ArrayList<Integer> getSupportedGameTypes() {
        ArrayList<Integer> r = new ArrayList<>();
        r.add(Game.GameTypes.YATZY);
        r.add(Game.GameTypes.YATZEE);
        return r;
    }

    @Override
    public int getScore(int d1, int d2, int d3, int d4, int d5, ArrayList<ScoreGroup> availableMoves) {
        predictedScore = 0;
        int sum = d1+d2+d3+d4+d5;
        int ones = (d1==1 ? 1 : 0) + (d2==1 ? 1 : 0) + (d3==1 ? 1 : 0) + (d4==1 ? 1 : 0) + (d5==1 ? 1 : 0);
        int twos = (d1==2 ? 1 : 0) + (d2==2 ? 1 : 0) + (d3==2 ? 1 : 0) + (d4==2 ? 1 : 0) + (d5==2 ? 1 : 0);
        int threes = (d1==3 ? 1 : 0) + (d2==3 ? 1 : 0) + (d3==3 ? 1 : 0) + (d4==3 ? 1 : 0) + (d5==3 ? 1 : 0);
        int fours = (d1==4 ? 1 : 0) + (d2==4 ? 1 : 0) + (d3==4 ? 1 : 0) + (d4==4 ? 1 : 0) + (d5==4 ? 1 : 0);
        int fives = (d1==5 ? 1 : 0) + (d2==5 ? 1 : 0) + (d3==5 ? 1 : 0) + (d4==5 ? 1 : 0) + (d5==5 ? 1 : 0);
        int sixes = (d1==6 ? 1 : 0) + (d2==6 ? 1 : 0) + (d3==6 ? 1 : 0) + (d4==6 ? 1 : 0) + (d5==6 ? 1 : 0);
        if(ones==3 && (twos==2 || threes==2 || fours==2 || fives==2 || sixes==2)) predictedScore = sum;
        else if(twos==3 && (ones==2 || threes==2 || fours==2 || fives==2 || sixes==2)) predictedScore = sum;
        else if(threes==3 && (twos==2 || ones==2 || fours==2 || fives==2 || sixes==2)) predictedScore = sum;
        else if(fours==3 && (twos==2 || threes==2 || ones==2 || fives==2 || sixes==2)) predictedScore = sum;
        else if(fives==3 && (twos==2 || threes==2 || fours==2 || ones==2 || sixes==2)) predictedScore = sum;
        else if(sixes==3 && (twos==2 || threes==2 || fours==2 || fives==2 || ones==2)) predictedScore = sum;
        return predictedScore;
    }
}
