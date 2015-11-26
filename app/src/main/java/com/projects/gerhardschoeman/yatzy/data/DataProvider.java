package com.projects.gerhardschoeman.yatzy.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.projects.gerhardschoeman.yatzy.data.DataContract.GameHistory;
import com.projects.gerhardschoeman.yatzy.data.DataContract.GameEntry;
import com.projects.gerhardschoeman.yatzy.data.DataContract.PlayerEntry;

import java.util.InputMismatchException;

/**
 * Created by Gerhard on 23/11/2015.
 */
public class DataProvider extends ContentProvider {

    private static final String LOGTAG = DataProvider.class.getSimpleName();

    private DataDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DataDBHelper(getContext());
        return true;
    }

    private static final int ALL_PLAYERS = 0;
    private static final int PLAYER_BY_ID = 1;
    private static final int PLAYER_BY_NAME = 2;
    private static final int ALL_GAMES = 3;
    private static final int GAME_BY_ID = 4;
    private static final int ALL_HISTORY = 5;
    private static final int HISTORY_BY_GAME_ID = 6;
    private static final int HISTORY_BY_PLAYER_ID = 7;
    private static final int HISTORY_GAME_PLAYER = 8;

    private static class MyUriMatcher extends UriMatcher{
        public MyUriMatcher(int code) {
            super(code);
        }
        @Override
        public int match(Uri uri) {
            int m = super.match(uri);
            switch(m){
                case ALL_PLAYERS:
                case PLAYER_BY_ID:
                case PLAYER_BY_NAME:
                case ALL_GAMES:
                case GAME_BY_ID:
                case HISTORY_GAME_PLAYER:
                    return m;
                case ALL_HISTORY:
                    if(uri.getQueryParameter(GameHistory.playerQueryID)!=null){
                        return HISTORY_BY_PLAYER_ID;
                    }else if(uri.getQueryParameter(GameHistory.gameQueryID)!=null){
                        return HISTORY_BY_GAME_ID;
                    }
                    return m;
            }
            return NO_MATCH;
        }
    }

    private static MyUriMatcher uriMatcher;
    static{
        uriMatcher = new MyUriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DataContract.BASE_AUTHORITY, DataContract.PATH_PLAYER,ALL_PLAYERS);
        uriMatcher.addURI(DataContract.BASE_AUTHORITY, DataContract.PATH_PLAYER + "/#",PLAYER_BY_ID);
        uriMatcher.addURI(DataContract.BASE_AUTHORITY, DataContract.PATH_PLAYER + "/*",PLAYER_BY_NAME);
        uriMatcher.addURI(DataContract.BASE_AUTHORITY, DataContract.PATH_GAME,ALL_GAMES);
        uriMatcher.addURI(DataContract.BASE_AUTHORITY, DataContract.PATH_GAME + "/#",GAME_BY_ID);
        uriMatcher.addURI(DataContract.BASE_AUTHORITY, DataContract.PATH_HISTORY,ALL_HISTORY);
        uriMatcher.addURI(DataContract.BASE_AUTHORITY, DataContract.PATH_HISTORY + "/*/*", HISTORY_GAME_PLAYER);
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch(match){
            case ALL_PLAYERS: return PlayerEntry.BASE_TYPE;
            case PLAYER_BY_ID: return PlayerEntry.BASE_ITEM_TYPE;
            case PLAYER_BY_NAME: return PlayerEntry.BASE_ITEM_TYPE;
            case ALL_GAMES: return GameEntry.BASE_TYPE;
            case GAME_BY_ID: return GameEntry.BASE_ITEM_TYPE;
            case ALL_HISTORY: return GameHistory.BASE_TYPE;
            case HISTORY_GAME_PLAYER: return GameHistory.BASE_ITEM_TYPE;
            case HISTORY_BY_GAME_ID: return GameHistory.BASE_ITEM_TYPE;
            case HISTORY_BY_PLAYER_ID: return GameHistory.BASE_ITEM_TYPE;
        }
        throw new UnsupportedOperationException("Unknown uri in getType(): " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(LOGTAG,"query for " + uri);
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        switch(match){
            case ALL_PLAYERS:
                Log.d(LOGTAG,"query received for all players");
                if(sortOrder==null){
                    sortOrder = PlayerEntry.COL_NAME + " ASC";
                }
                return db.query(PlayerEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case PLAYER_BY_ID: {
                String id = uri.getPathSegments().get(1);
                Log.d(LOGTAG,"query on player id " + id);
                selection = PlayerEntry._ID + "=?";
                selectionArgs = new String[]{id};
                return db.query(PlayerEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            }
            case PLAYER_BY_NAME:{
                String name = uri.getPathSegments().get(1);
                Log.d(LOGTAG,"query on player name " + name);
                selection = PlayerEntry.COL_NAME + "=?";
                selectionArgs = new String[]{name};
                return db.query(PlayerEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            }
            case ALL_GAMES:
                return db.query(GameEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case GAME_BY_ID: {
                String id = uri.getPathSegments().get(1);
                selection = GameEntry._ID + "=?";
                selectionArgs = new String[]{id};
                return db.query(GameEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            }
            case ALL_HISTORY:
                return db.query(GameHistory.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case HISTORY_BY_GAME_ID:{
                String id = uri.getQueryParameter(GameHistory.gameQueryID);
                selection = GameHistory.COL_GAMEID + "=?";
                selectionArgs = new String[]{id};
                return db.query(GameHistory.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            }
            case HISTORY_BY_PLAYER_ID:{
                String id = uri.getQueryParameter(GameHistory.playerQueryID);
                selection = GameHistory.COL_PLAYERID + "=?";
                selectionArgs = new String[]{id};
                return db.query(GameHistory.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            }
            case HISTORY_GAME_PLAYER:{
                String gameID = uri.getPathSegments().get(1);
                String playerID = uri.getPathSegments().get(2);
                selection = GameHistory.COL_GAMEID + "=? AND " + GameHistory.COL_PLAYERID + "=?";
                selectionArgs = new String[]{gameID,playerID};
                return db.query(GameHistory.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            }
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOGTAG,"received insert for " + uri);
        int match = uriMatcher.match(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri retUri = null;
        switch(match){
            case ALL_PLAYERS:{
                Log.d(LOGTAG,"matched to players");
                long r = db.insert(PlayerEntry.TABLE_NAME,null,values);
                if(r>0){
                    Log.d(LOGTAG,"insert ok");
                    retUri = uri;
                }else{
                    throw new SQLException("Unable to insert player record for " + uri);
                }
            }
            break;
            case ALL_GAMES:{
                long r = db.insert(GameEntry.TABLE_NAME,null,values);
                if(r>0){
                    String newGameID=null;
                    Cursor c = db.rawQuery("select seq from sqlite_sequence WHERE name = '" + GameEntry.TABLE_NAME + "'",null);
                    if(c!=null && c.moveToFirst()){
                        newGameID = Integer.toString(c.getInt(0));
                    }
                    if(newGameID==null) throw new SQLException("Unable to get new game id for " + uri);
                    retUri = GameEntry.getUriFromID(Integer.parseInt(newGameID));
                }else{
                    throw new SQLException("Unable to insert game record for " + uri);
                }
            }
            break;
            case ALL_HISTORY:{
                long r = db.insert(GameHistory.TABLE_NAME,null,values);
                if(r>0){
                    retUri = uri;
                }else{
                    throw new SQLException("Unable to insert game history record for " + uri);
                }
            }
            break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return retUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        int ret = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch(match){
            case PLAYER_BY_ID:{
                Log.d(LOGTAG,"Update by player id");
                String id = uri.getPathSegments().get(1);
                selection = PlayerEntry._ID + "=?";
                selectionArgs = new String[]{id};
                ret = db.update(PlayerEntry.TABLE_NAME,values,selection,selectionArgs);
            }
            break;
        }
        return ret;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int match = uriMatcher.match(uri);
        int ret = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch(match){
            case PLAYER_BY_NAME:{
                String name = uri.getPathSegments().get(1);
                Cursor c = db.rawQuery("select " + PlayerEntry._ID + " from " + PlayerEntry.TABLE_NAME + " where " + PlayerEntry.COL_NAME + "='" + name + "'", null);
                c.moveToFirst();
                String id = Integer.toString(c.getInt(0));
                Log.d(LOGTAG,"Delete by player name for " + name + " id=" + id);
                selection = GameHistory.COL_PLAYERID + "=?";
                selectionArgs = new String[]{id};
                db.delete(GameHistory.TABLE_NAME,selection,selectionArgs);
                selection = PlayerEntry._ID + "=?";
                ret = db.delete(PlayerEntry.TABLE_NAME,selection,selectionArgs);
            }
            break;
        }
        return ret;
    }
}
