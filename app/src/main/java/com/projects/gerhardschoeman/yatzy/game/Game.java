package com.projects.gerhardschoeman.yatzy.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.projects.gerhardschoeman.yatzy.data.DataContract.GameEntry;
import com.projects.gerhardschoeman.yatzy.data.DataContract.PlayerEntry;
import com.projects.gerhardschoeman.yatzy.data.DataContract.GameHistory;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Gerhard on 27/11/2015.

public static class TurnTypes{
    public static final int count = 15;
    public static final int ONES = 0;
    public static final int TWOS = 1;
    public static final int THREES = 2;
    public static final int FOURS = 3;
    public static final int FIVES = 4;
    public static final int SIXES = 5;
    public static final int PAIR = 6;
    public static final int TWOPAIR = 7;
    public static final int THREEKIND = 8;
    public static final int FOURKIND = 9;
    public static final int SMALLSTRAIGHT = 10;
    public static final int LRGSTRAIGHT = 11;
    public static final int FULLHOUSE = 12;
    public static final int CHANCE = 13;
    public static final int YATZY = 14;
}
 */
public class Game {

    private static final String LOGTAG = Game.class.getSimpleName();

    public static class GameTypes{
        public static int YATZY = 0;
        public static int YATZEE = 1;
    }

    private int id;
    private String description;
    private long startTime;
    private long endTime;
    private int gameType;
    private ArrayList<Player> players = new ArrayList<Player>();

    private Context mContext;

    public Game(Context context, int gameID){
        mContext = context;

        Cursor c = mContext.getContentResolver().query(GameEntry.getUriFromID(gameID), DataProjections.Game_ALL.COLUMNS, null,null,null);
        if(!c.moveToFirst()){
            Log.e(LOGTAG,"Could not get record for game id");
            return;
        }
        id = gameID;
        description = c.getString(DataProjections.Game_ALL.COL_DESCRIPTION);
        startTime = c.getLong(DataProjections.Game_ALL.COL_STARTED);
        endTime = c.getLong(DataProjections.Game_ALL.COL_FINISHED);
        gameType = c.getInt(DataProjections.Game_ALL.COL_TYPE);

        Cursor pCursor = mContext.getContentResolver().query(GameHistory.getUriFromGameID(id), DataProjections.History_ALL.COLUMNS,null,null,null);
        ArrayList<Integer> added = new ArrayList<>();
        while(pCursor.moveToNext()){
            int playerID = pCursor.getInt(DataProjections.History_ALL.COL_PLAYERID);
            Player player=null;
            if(!added.contains(playerID)) {
                player = new Player(mContext, playerID);
                player.createMovesFor(gameType);
                players.add(player);
                added.add(playerID);
            }else{
                for(int i=0;i<players.size();++i){
                    if(players.get(i).getID()==playerID){
                        player = players.get(i);
                        break;
                    }
                }
            }
            int scoreid = pCursor.getInt(DataProjections.History_ALL.COL_SCOREID);
            int score = pCursor.getInt(DataProjections.History_ALL.COL_SCORE);
            if(score>=0)
            player.playMove(scoreid,score);
        }
    }

    public Game(Context context,HashSet<String> chosen,int type,String descr){
        mContext = context;
        gameType = type;
        description = descr;
        startTime = System.currentTimeMillis();
        endTime = -1;

        ContentValues cv = new ContentValues();
        cv.put(GameEntry.COL_STARTED, startTime);
        cv.put(GameEntry.COL_FINISHED,endTime);
        cv.put(GameEntry.COL_TYPE,gameType);
        cv.put(GameEntry.COL_DESCRIPTION,description);
        Uri newGame = mContext.getContentResolver().insert(GameEntry.CONTENT_URI,cv);
        Log.d(LOGTAG, "got new game uri " + newGame);
        id = Integer.parseInt(newGame.getPathSegments().get(1));

        for(String p:chosen){
            Player np = new Player(mContext,p);
            np.createMovesFor(gameType);
            np.save(id);
            players.add(np);
        }
    }

    public ArrayList<Player> getSortedPlayers(){
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                Integer lhsScore = lhs.getTotalScore();
                Integer rhsScore = rhs.getTotalScore();
                if(lhsScore==rhsScore) return lhs.getName().compareTo(rhs.getName());
                return lhsScore.compareTo(rhsScore);
            }
        });
        return players;
    }
}