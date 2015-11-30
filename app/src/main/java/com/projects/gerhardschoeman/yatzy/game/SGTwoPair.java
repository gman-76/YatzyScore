package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class SGTwoPair extends ScoreGroup {
    @Override
    public String getDescription() {
        return description;
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
        description = "Two pair of ";
        int ones = (d1==1 ? 1 : 0) + (d2==1 ? 1 : 0) + (d3==1 ? 1 : 0) + (d4==1 ? 1 : 0) + (d5==1 ? 1 : 0);
        int twos = (d1==2 ? 1 : 0) + (d2==2 ? 1 : 0) + (d3==2 ? 1 : 0) + (d4==2 ? 1 : 0) + (d5==2 ? 1 : 0);
        int threes = (d1==3 ? 1 : 0) + (d2==3 ? 1 : 0) + (d3==3 ? 1 : 0) + (d4==3 ? 1 : 0) + (d5==3 ? 1 : 0);
        int fours = (d1==4 ? 1 : 0) + (d2==4 ? 1 : 0) + (d3==4 ? 1 : 0) + (d4==4 ? 1 : 0) + (d5==4 ? 1 : 0);
        int fives = (d1==5 ? 1 : 0) + (d2==5 ? 1 : 0) + (d3==5 ? 1 : 0) + (d4==5 ? 1 : 0) + (d5==5 ? 1 : 0);
        int sixes = (d1==6 ? 1 : 0) + (d2==6 ? 1 : 0) + (d3==6 ? 1 : 0) + (d4==6 ? 1 : 0) + (d5==6 ? 1 : 0);

        boolean twopair=false;
        if(ones>1 && !twopair) {
            if(twos>1){
                predictedScore = 2 + 4;
                description += "1,1,2,2";
                twopair=true;
            }else if(threes>1){
                predictedScore = 2 + 6;
                description += "1,1,3,3";
                twopair=true;
            }else if(fours>1){
                predictedScore = 2 + 8;
                description += "1,1,4,4";
                twopair=true;
            }else if(fives>1){
                predictedScore = 2 + 10;
                description += "1,1,5,5";
                twopair=true;
            }else if(sixes>1){
                predictedScore = 2 + 12;
                description += "1,1,6,6";
                twopair=true;
            }
        }
        if(twos>1 && !twopair) {
            if(threes>1){
                predictedScore = 4 + 6;
                description += "2,2,3,3";
                twopair=true;
            }else if(fours>1){
                predictedScore = 4 + 8;
                description += "2,2,4,4";
                twopair=true;
            }else if(fives>1){
                predictedScore = 4 + 10;
                description += "2,2,5,5";
                twopair=true;
            }else if(sixes>1){
                predictedScore = 4 + 12;
                description += "2,2,6,6";
                twopair=true;
            }
        }
        if(threes>1 && !twopair) {
            if(fours>1){
                predictedScore = 6 + 8;
                description += "3,3,4,4";
                twopair=true;
            }else if(fives>1){
                predictedScore = 6 + 10;
                description += "3,3,5,5";
                twopair=true;
            }else if(sixes>1){
                predictedScore = 6 + 12;
                description += "3,3,6,6";
                twopair=true;
            }
        }
        if(fours>1 && !twopair) {
            if(fives>1){
                predictedScore = 8 + 10;
                description += "4,4,5,5";
                twopair=true;
            }else if(sixes>1){
                predictedScore = 8 + 12;
                description += "4,4,6,6";
                twopair=true;
            }
        }
        if(fives>1 && !twopair) {
            if(sixes>1){
                predictedScore = 10 + 12;
                description += "5,5,6,6";
            }
        }
        return predictedScore;
    }
}
