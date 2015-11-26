package com.projects.gerhardschoeman.yatzy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gerhard on 23/11/2015.
 */
public class DataDBHelper extends SQLiteOpenHelper {

    private static final String dbName = "yatzy.db";
    private static final int dbVersion = 1;

    public DataDBHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + DataContract.PlayerEntry.TABLE_NAME + " (" +
                    DataContract.PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DataContract.PlayerEntry.COL_NAME + " TEXT UNIQUE NOT NULL," +
                    DataContract.PlayerEntry.COL_PHOTO + " BLOB)";
        db.execSQL(sql);

        sql = "CREATE TABLE " + DataContract.GameEntry.TABLE_NAME + " (" +
            DataContract.GameEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            DataContract.GameEntry.COL_STARTED + " INTEGER NOT NULL," +
            DataContract.GameEntry.COL_FINISHED + " INTEGER)";
        db.execSQL(sql);

        sql = "CREATE TABLE " + DataContract.GameHistory.TABLE_NAME + " (" +
            DataContract.GameHistory.COL_GAMEID + " INTEGER," +
            DataContract.GameHistory.COL_PLAYERID + " INTEGER," +
            DataContract.GameHistory.COL_SCOREID + " INTEGER," +
            DataContract.GameHistory.COL_SCORE + " INTEGER," +
            "FOREIGN KEY (" + DataContract.GameHistory.COL_GAMEID + ") REFERENCES " + DataContract.GameEntry.TABLE_NAME + " (" + DataContract.GameEntry._ID + ")," +
            "FOREIGN KEY (" + DataContract.GameHistory.COL_PLAYERID + ") REFERENCES " + DataContract.PlayerEntry.TABLE_NAME + " (" + DataContract.PlayerEntry._ID + "))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.GameHistory.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.GameEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DataContract.PlayerEntry.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
