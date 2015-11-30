package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 30/11/2015.
 */
public class SGFullHouseYahtzee extends ScoreGroup {
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ScoreGroup getNew() {
        return new SGFullHouseYahtzee();
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
        r.add(Game.GameTypes.YAHTZEE);
        return r;
    }

    @Override
    public int getScore(int d1, int d2, int d3, int d4, int d5, ArrayList<ScoreGroup> availableMoves) {
        predictedScore = 0;
        description = "Full House ";
        int fixed = 25;
        int ones = (d1==1 ? 1 : 0) + (d2==1 ? 1 : 0) + (d3==1 ? 1 : 0) + (d4==1 ? 1 : 0) + (d5==1 ? 1 : 0);
        int twos = (d1==2 ? 1 : 0) + (d2==2 ? 1 : 0) + (d3==2 ? 1 : 0) + (d4==2 ? 1 : 0) + (d5==2 ? 1 : 0);
        int threes = (d1==3 ? 1 : 0) + (d2==3 ? 1 : 0) + (d3==3 ? 1 : 0) + (d4==3 ? 1 : 0) + (d5==3 ? 1 : 0);
        int fours = (d1==4 ? 1 : 0) + (d2==4 ? 1 : 0) + (d3==4 ? 1 : 0) + (d4==4 ? 1 : 0) + (d5==4 ? 1 : 0);
        int fives = (d1==5 ? 1 : 0) + (d2==5 ? 1 : 0) + (d3==5 ? 1 : 0) + (d4==5 ? 1 : 0) + (d5==5 ? 1 : 0);
        int sixes = (d1==6 ? 1 : 0) + (d2==6 ? 1 : 0) + (d3==6 ? 1 : 0) + (d4==6 ? 1 : 0) + (d5==6 ? 1 : 0);
        if(ones==3){
            if(twos==2){
                predictedScore = fixed;
                description += "1,1,1,2,2";
            }else if(threes==2){
                predictedScore = fixed;
                description += "1,1,1,3,3";
            }else if(fours==2){
                predictedScore = fixed;
                description += "1,1,1,4,4";
            }else if(fives==2){
                predictedScore = fixed;
                description += "1,1,1,5,5";
            }else if(sixes==2){
                predictedScore = fixed;
                description += "1,1,1,6,6";
            }
        }
        else if(twos==3){
            if(ones==2){
                predictedScore = fixed;
                description += "2,2,2,1,1";
            }else if(threes==2){
                predictedScore = fixed;
                description += "2,2,2,3,3";
            }else if(fours==2){
                predictedScore = fixed;
                description += "2,2,2,4,4";
            }else if(fives==2){
                predictedScore = fixed;
                description += "2,2,2,5,5";
            }else if(sixes==2){
                predictedScore = fixed;
                description += "2,2,2,6,6";
            }
        }else if(threes==3){
            if(twos==2){
                predictedScore = fixed;
                description += "3,3,3,2,2";
            }else if(ones==2){
                predictedScore = fixed;
                description += "3,3,3,1,1";
            }else if(fours==2){
                predictedScore = fixed;
                description += "3,3,3,4,4";
            }else if(fives==2){
                predictedScore = fixed;
                description += "3,3,3,5,5";
            }else if(sixes==2){
                predictedScore = fixed;
                description += "3,3,3,6,6";
            }
        }else if(fours==3){
            if(twos==2){
                predictedScore = fixed;
                description += "4,4,4,2,2";
            }else if(threes==2){
                predictedScore = fixed;
                description += "4,4,4,3,3";
            }else if(ones==2){
                predictedScore = fixed;
                description += "4,4,4,1,1";
            }else if(fives==2){
                predictedScore = fixed;
                description += "4,4,4,5,5";
            }else if(sixes==2){
                predictedScore = fixed;
                description += "4,4,4,6,6";
            }
        }else if(fives==3){
            if(twos==2){
                predictedScore = fixed;
                description += "5,5,5,2,2";
            }else if(threes==2){
                predictedScore = fixed;
                description += "5,5,5,3,3";
            }else if(fours==2){
                predictedScore = fixed;
                description += "5,5,5,4,4";
            }else if(ones==2){
                predictedScore = fixed;
                description += "5,5,5,1,1";
            }else if(sixes==2){
                predictedScore = fixed;
                description += "5,5,5,6,6";
            }
        }else if(sixes==3){
            if(twos==2){
                predictedScore = fixed;
                description += "6,6,6,2,2";
            }else if(threes==2){
                predictedScore = fixed;
                description += "6,6,6,3,3";
            }else if(fours==2){
                predictedScore = fixed;
                description += "6,6,6,4,4";
            }else if(fives==2){
                predictedScore = fixed;
                description += "6,6,6,5,5";
            }else if(ones==2){
                predictedScore = fixed;
                description += "6,6,6,1,1";
            }
        }
        return predictedScore;
    }
}
