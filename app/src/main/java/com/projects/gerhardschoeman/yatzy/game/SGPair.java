package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 28/11/2015.
 */
public class SGPair extends ScoreGroup {
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ScoreGroup getNew() {
        return new SGPair();
    }

    @Override
    public int getID() {
        return 6;
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
        description = "One pair of ";
        int ones = (d1==1 ? 1 : 0) + (d2==1 ? 1 : 0) + (d3==1 ? 1 : 0) + (d4==1 ? 1 : 0) + (d5==1 ? 1 : 0);
        int twos = (d1==2 ? 1 : 0) + (d2==2 ? 1 : 0) + (d3==2 ? 1 : 0) + (d4==2 ? 1 : 0) + (d5==2 ? 1 : 0);
        int threes = (d1==3 ? 1 : 0) + (d2==3 ? 1 : 0) + (d3==3 ? 1 : 0) + (d4==3 ? 1 : 0) + (d5==3 ? 1 : 0);
        int fours = (d1==4 ? 1 : 0) + (d2==4 ? 1 : 0) + (d3==4 ? 1 : 0) + (d4==4 ? 1 : 0) + (d5==4 ? 1 : 0);
        int fives = (d1==5 ? 1 : 0) + (d2==5 ? 1 : 0) + (d3==5 ? 1 : 0) + (d4==5 ? 1 : 0) + (d5==5 ? 1 : 0);
        int sixes = (d1==6 ? 1 : 0) + (d2==6 ? 1 : 0) + (d3==6 ? 1 : 0) + (d4==6 ? 1 : 0) + (d5==6 ? 1 : 0);

        if(ones>1) {
            predictedScore = 2;
            description += "1,1";
        }
        if(twos>1) {
            if(predictedScore>0){
                //we already have a possible pair so this is another option
                ScoreGroup ngroup = new SGPair();
                ngroup.description = "One pair of 2,2";
                ngroup.predictedScore = 4;
                availableMoves.add(ngroup);
            }else {
                predictedScore = 4;
                description += "2,2";
            }
        }
        if(threes>1) {
            if(predictedScore>0){
                //we already have a possible pair so this is another option
                ScoreGroup ngroup = new SGPair();
                ngroup.description = "One pair of 3,3";
                ngroup.predictedScore = 6;
                availableMoves.add(ngroup);
            }else {
                predictedScore = 6;
                description += "3,3";
            }
        }
        if(fours>1) {
            if(predictedScore>0){
                //we already have a possible pair so this is another option
                ScoreGroup ngroup = new SGPair();
                ngroup.description = "One pair of 4,4";
                ngroup.predictedScore = 8;
                availableMoves.add(ngroup);
            }else {
                predictedScore = 8;
                description += "4,4";
            }
        }
        if(fives>1) {
            if(predictedScore>0){
                //we already have a possible pair so this is another option
                ScoreGroup ngroup = new SGPair();
                ngroup.description = "One pair of 5,5";
                ngroup.predictedScore = 10;
                availableMoves.add(ngroup);
            }else {
                predictedScore = 10;
                description += "5,5";
            }
        }
        if(sixes>1) {
            if(predictedScore>0){
                //we already have a possible pair so this is another option
                ScoreGroup ngroup = new SGPair();
                ngroup.description = "One pair of 6,6";
                ngroup.predictedScore = 12;
                availableMoves.add(ngroup);
            }else {
                predictedScore = 12;
                description += "6,6";
            }
        }
        return predictedScore;
    }
}
