package com.projects.gerhardschoeman.yatzy;

import android.content.ContentProvider;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataDBHelper;

import java.util.HashSet;

/**
 * Created by Gerhard on 24/11/2015.
 */
public class DBTests extends AndroidTestCase {
    //check that db tables exists
    public void testCreateDB(){
        DataDBHelper dbHelper = new DataDBHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select name from sqlite_master where type='table'",null);
        assertTrue("Error! DB does not contain any tables",c.moveToFirst());
        HashSet<String> expectedTables = new HashSet<>();
        expectedTables.add(DataContract.PlayerEntry.TABLE_NAME);
        expectedTables.add(DataContract.GameEntry.TABLE_NAME);
        expectedTables.add(DataContract.GameHistory.TABLE_NAME);
        do{
            expectedTables.remove(c.getString(0));
        }while(c.moveToNext());
        assertTrue("Not all expected tables coud be found in DB",expectedTables.isEmpty());
    }
}
