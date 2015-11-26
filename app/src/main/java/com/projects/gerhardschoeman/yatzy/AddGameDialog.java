package com.projects.gerhardschoeman.yatzy;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;

import java.util.Date;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class AddGameDialog extends DialogFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final int LOADER_ID = 0;

    AvailablePlayerAdapter ad;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose players:");

        ad = new AvailablePlayerAdapter(getActivity(),null,0);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_game,null);
        ListView lv = (ListView)v.findViewById(R.id.lstAvailablePlayers);
        lv.setAdapter(ad);

        getLoaderManager().initLoader(LOADER_ID,null,this);

        builder.setView(v);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(ad.chosenPlayers.size()>0){
                    ContentValues cv = new ContentValues();
                    cv.put(DataContract.GameEntry.COL_STARTED, System.currentTimeMillis());
                    Uri newGame = getActivity().getContentResolver().insert(DataContract.GameEntry.CONTENT_URI,cv);
                    String gameID = newGame.getPathSegments().get(1);
                    for(String p:ad.chosenPlayers){
                        Cursor pDetails = getActivity().getContentResolver().query(DataContract.PlayerEntry.getUriFromName(p), DataProjections.Player_ALL.COLUMNS,null,null,null);
                        pDetails.moveToFirst();
                        String playerID = pDetails.getString(DataProjections.Player_ALL.COL_ID);
                        cv = new ContentValues();
                        cv.put(DataContract.GameHistory.gameQueryID,gameID);
                        cv.put(DataContract.GameHistory.playerQueryID,playerID);
                        
                    }
                }
            }
        });

        return builder.create();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), DataContract.PlayerEntry.CONTENT_URI, DataProjections.Player_ALL.COLUMNS,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ad.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ad.swapCursor(null);
    }
}
