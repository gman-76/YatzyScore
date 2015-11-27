package com.projects.gerhardschoeman.yatzy.game;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class SGTwos extends ScoreGroup {
    @Override
    public ScoreGroup getNew() {
        return new SGTwos();
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public boolean isUpperSection() {
        return true;
    }

    @Override
    public ArrayList<Integer> getSupportedGameTypes() {
        ArrayList<Integer> r = new ArrayList<>();
        r.add(Game.GameTypes.YATZY);
        r.add(Game.GameTypes.YATZEE);
        return r;
    }

    @Override
    public int getScore(int d1, int d2, int d3, int d4, int d5) {
        final int target=2;
        return d1==target?d1:0 +
               d2==target?d2:0 +
               d3==target?d3:0 +
               d4==target?d4:0 +
               d5==target?d5:0;
    }
}
