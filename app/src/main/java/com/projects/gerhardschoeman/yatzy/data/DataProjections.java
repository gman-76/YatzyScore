package com.projects.gerhardschoeman.yatzy.data;

import com.projects.gerhardschoeman.yatzy.data.DataContract.PlayerEntry;
import com.projects.gerhardschoeman.yatzy.data.DataContract.GameEntry;

/**
 * Created by Gerhard on 24/11/2015.
 */
public class DataProjections {
    public static class Player_ALL{
        public static final String[] COLUMNS = {
                PlayerEntry._ID,
                PlayerEntry.COL_NAME,
                PlayerEntry.COL_PHOTO
        };
        public static final int COL_ID = 0;
        public static final int COL_NAME = 1;
        public static final int COL_PHOTO = 2;
    }
    public static class Game_ALL{
        public static final String[] COLUMNS = {
                GameEntry._ID,
                GameEntry.COL_STARTED,
                GameEntry.COL_FINISHED
        };
        public static final int COL_ID = 0;
        public static final int COL_STARTED = 1;
        public static final int COL_FINISHED = 2;
    }
}
