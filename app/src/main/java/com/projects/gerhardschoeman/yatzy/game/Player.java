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

    private ArrayList<ScoreGroup> availableMoves = new ArrayList<>();

    public int getID() {return id;}

    public Player(Context context,String playerName){
        mContext = context;

        Cursor c = mContext.getContentResolver().query(DataContract.PlayerEntry.getUriFromName(playerName),
                DataProjections.Player_ALL.COLUMNS,null,null,null);
        c.moveToFirst();
        name = playerName;
        id = c.getInt(DataProjections.Player_ALL.COL_ID);
        photo = c.getBlob(DataProjections.Player_ALL.COL_PHOTO);
    }

    public Player(Context context,int playerID){
        mContext = context;

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

    public void createMovesFor(int gameType){
        availableMoves = ScoreGroupFactory.getListByType(gameType);
    }

    public void playMove(int id,int score){
        for(ScoreGroup sg:availableMoves){
            if(sg.getID()==id && !sg.hasPlayed()){
                sg.play(score);
                break;
            }
        }
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
        int ret = 0;
        for(ScoreGroup sg:availableMoves){
            if(sg.hasPlayed()){
                ret += sg.getMarkedScore();
            }
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