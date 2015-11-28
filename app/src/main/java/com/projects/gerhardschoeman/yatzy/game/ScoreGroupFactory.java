package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class ScoreGroupFactory {
    private static ArrayList<ScoreGroup> availableGroups = new ArrayList<>();
    static{
        availableGroups.add(new SGOnes());
        availableGroups.add(new SGTwos());
        availableGroups.add(new SGThrees());
        availableGroups.add(new SGFours());
        availableGroups.add(new SGFives());
        availableGroups.add(new SGSixes());
        availableGroups.add(new SGYatzy());
        availableGroups.add(new SGChance());
        availableGroups.add(new SGSmallStraight());
        availableGroups.add(new SGLargeStraight());
        availableGroups.add(new SGThreeOfKind());
        availableGroups.add(new SGFourOfKind());
        availableGroups.add(new SGFullHouse());
        availableGroups.add(new SGPair());
    };

    public static ArrayList<ScoreGroup> getListByType(int type){
        ArrayList<ScoreGroup> ret = new ArrayList<>();
        for(ScoreGroup sg:availableGroups){
            if(sg.getSupportedGameTypes().contains(type)){
                ret.add(sg.getNew());
            }
        }
        return ret;
    }
}
