package com.projects.gerhardschoeman.yatzy.game;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.projects.gerhardschoeman.yatzy.data.DataContract;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public abstract class ScoreGroup {
    protected int predictedScore = -1;
    protected String description;

    private int score = -1;
    private boolean played = false;
    public boolean hasPlayed() {return played;}
    public void play(Context context,int gameID,int playerID) {
        played=true;
        score=predictedScore;
        Uri uri = DataContract.GameHistory.getUriFromGameAndPlayerAndScore(gameID,playerID,getID());
        ContentValues cv = new ContentValues();
        cv.put(DataContract.GameHistory.COL_SCORE,score);
        context.getContentResolver().update(uri,cv,null,null);
    }
    public int getMarkedScore() { return score; }
    public int getPredictedScore() { return predictedScore; }
    public void setExistingScore(int s){
        played = true;
        score = s;
    }

    public abstract String getDescription();
    public abstract ScoreGroup getNew();
    public abstract int getID();
    public abstract boolean isUpperSection();
    public abstract ArrayList<Integer> getSupportedGameTypes();
    public abstract int getScore(int d1, int d2, int d3, int d4, int d5, ArrayList<ScoreGroup> availableMoves);
}
