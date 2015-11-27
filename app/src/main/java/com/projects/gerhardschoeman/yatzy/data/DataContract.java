package com.projects.gerhardschoeman.yatzy.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Gerhard on 23/11/2015.
 */
public class DataContract {

    public static final String BASE_AUTHORITY = "com.projects.gerhardschoeman.yatzy";
    private static final Uri BASE_URI = Uri.parse("content://" + BASE_AUTHORITY);

    public static final String PATH_PLAYER = "player";
    public static final String PATH_GAME = "game";
    public static final String PATH_HISTORY = "history";

    public static class PlayerEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_PLAYER).build();
        public static final String BASE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + BASE_AUTHORITY + "/" + PATH_PLAYER;
        public static final String BASE_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + BASE_AUTHORITY + "/" + PATH_PLAYER;

        public static final String TABLE_NAME = "Players";

        public static final String COL_NAME = "Name";
        public static final String COL_PHOTO = "Photo";

        public static Uri getUriFromID(int id){
            return CONTENT_URI.buildUpon().appendPath(Integer.toString(id)).build();
        }
        public static Uri getUriFromName(String name){
            return CONTENT_URI.buildUpon().appendPath(name).build();
        }
    }

    public static class GameEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_GAME).build();
        public static final String BASE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + BASE_AUTHORITY + "/" + PATH_GAME;
        public static final String BASE_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + BASE_AUTHORITY + "/" + PATH_GAME;

        public static final String TABLE_NAME = "Games";

        public static final String COL_STARTED = "Started";
        public static final String COL_FINISHED = "Finished";
        public static final String COL_TYPE = "Type";
        public static final String COL_DESCRIPTION = "Description";

        public static Uri getUriFromID(int id){
            return CONTENT_URI.buildUpon().appendPath(Integer.toString(id)).build();
        }
    }

    public static class GameHistory{
        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_HISTORY).build();
        public static final String BASE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + BASE_AUTHORITY + "/" + PATH_HISTORY;
        public static final String BASE_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + BASE_AUTHORITY + "/" + PATH_HISTORY;

        public static final String playerQueryID = "player";
        public static final String gameQueryID = "game";

        public static final String TABLE_NAME = "GameHistory";

        public static final String COL_GAMEID = "GameID";
        public static final String COL_PLAYERID = "PlayerID";
        public static final String COL_SCOREID = "ScoreID";
        public static final String COL_SCORE = "Score";

        public static Uri getUriFromGameID(int id){
            return CONTENT_URI.buildUpon().appendQueryParameter(gameQueryID,Integer.toString(id)).build();
        }

        public static Uri getUriFromGameAndPlayer(int gameID,int playerID){
            return CONTENT_URI.buildUpon().appendPath(Integer.toString(gameID)).appendPath(Integer.toString(playerID)).build();
        }

        public static Uri getUriFromGameAndPlayerAndScore(int gameID,int playerID,int scoreID){
            return CONTENT_URI.buildUpon()
                    .appendPath(Integer.toString(gameID))
                    .appendPath(Integer.toString(playerID))
                    .appendPath(Integer.toString(scoreID))
                    .build();
        }
    }

}
