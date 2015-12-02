package com.projects.gerhardschoeman.yatzy.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;

import java.util.ArrayList;

/**
 * Created by Gerhard on 27/11/2015.
 */
public class Player {

    private Context mContext;

    private String name;
    private int id;
    private byte[] photo;
    private int gameType;
    private boolean bonus;
    private int bonusScore;
    private int upperTotal;
    private int lowerTotal;

    private ArrayList<ScoreGroup> availableMoves = new ArrayList<>();

    public int getID() {return id;}

    public Player(Context context,String playerName,int type){
        mContext = context;

        gameType = type;

        Cursor c = mContext.getContentResolver().query(DataContract.PlayerEntry.getUriFromName(playerName),
                DataProjections.Player_ALL.COLUMNS,null,null,null);
        c.moveToFirst();
        name = playerName;
        id = c.getInt(DataProjections.Player_ALL.COL_ID);
        photo = c.getBlob(DataProjections.Player_ALL.COL_PHOTO);
    }

    public Player(Context context,int playerID,int type){
        mContext = context;

        gameType = type;

        Cursor c = mContext.getContentResolver().query(DataContract.PlayerEntry.getUriFromID(playerID),
                DataProjections.Player_ALL.COLUMNS,null,null,null);
        c.moveToFirst();
        name = c.getString(DataProjections.Player_ALL.COL_NAME);
        id = playerID;
        photo = c.getBlob(DataProjections.Player_ALL.COL_PHOTO);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Player){
            return ((Player)o).id==this.id;
        }
        return false;
    }

    public boolean gotBonus(){
        return bonus;
    }

    public int getBonusScore(){
        return bonusScore;
    }

    public int getGameType(){ return gameType; }

    public void createMovesFor(int gameType){
        availableMoves = ScoreGroupFactory.getListByType(gameType);
    }

    public void setMoveScore(int id,int score){
        for(ScoreGroup sg:availableMoves){
            if(sg.getID()==id && !sg.hasPlayed()){
                sg.setExistingScore(score);
                break;
            }
        }
    }

    public ArrayList<ScoreGroup> getMoveHistory(){
        return availableMoves;
    }

    public ArrayList<ScoreGroup> getAvailableMoves(){
        ArrayList<ScoreGroup> remaining  = new ArrayList<>();
        for(ScoreGroup sg:availableMoves){
            if(!sg.hasPlayed()){
                remaining.add(sg);
            }
        }
        return remaining;
    }

    public void save(int gameID){
        for(ScoreGroup sg:availableMoves){
            Uri uri = DataContract.GameHistory.getUriFromGameAndPlayerAndScore(gameID, id, sg.getID());
            Cursor c = mContext.getContentResolver().query(uri, DataProjections.History_ALL.COLUMNS,null,null,null);
            if(c!=null && c.moveToFirst()){
                ContentValues cv = new ContentValues();
                cv.put(DataContract.GameHistory.COL_SCORE,sg.getMarkedScore());
                mContext.getContentResolver().update(uri,cv,null,null);
            }else {
                ContentValues cv = new ContentValues();
                cv.put(DataContract.GameHistory.COL_GAMEID,gameID);
                cv.put(DataContract.GameHistory.COL_PLAYERID,id);
                cv.put(DataContract.GameHistory.COL_SCOREID,sg.getID());
                cv.put(DataContract.GameHistory.COL_SCORE,sg.getMarkedScore());
                mContext.getContentResolver().insert(DataContract.GameHistory.CONTENT_URI, cv);
            }
        }
    }

    public int getTotalScore(){
        upperTotal = 0;
        lowerTotal = 0;
        bonusScore = 0;
        bonus = false;
        for(ScoreGroup sg:availableMoves){
            if(sg.hasPlayed()){
                if(sg.isUpperSection()) upperTotal += sg.getMarkedScore(); else lowerTotal += sg.getMarkedScore();
            }
        }
        if(upperTotal>=63) {
            bonus=true;
            switch (gameType) {
                case Game.GameTypes.YATZY:
                    bonusScore = 50;
                    break;
                case Game.GameTypes.YAHTZEE:
                    bonusScore = 35;
                    break;
            }
        }
        return upperTotal + lowerTotal + bonusScore;
    }

    public int getUpperTotal() { return upperTotal; }
    public int getLowerTotal() { return lowerTotal; }

    public int getMovesRemaining(){
        int ret = 0;
        for(ScoreGroup sg:availableMoves){
            if(!sg.hasPlayed()) ret++;
        }
        return ret;
    }

    public String getName(){
        return name;
    }

    public byte[] getPhoto(){
        return photo;
    }
}
