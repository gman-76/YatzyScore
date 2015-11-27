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
