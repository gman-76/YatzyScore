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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;
import com.projects.gerhardschoeman.yatzy.game.Game;

import java.util.Date;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class AddGameDialog extends DialogFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private final int LOADER_ID = 0;

    private AvailablePlayerAdapter ad;
    private Spinner gameTypeSelect;
    private EditText description;

    public interface AddGameDlgCallbacks{
        void newGameReady(Game game);
    }

    private AddGameDlgCallbacks callbacks;

    public void setCallbacks(AddGameDlgCallbacks cb){
        callbacks = cb;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setCustomTitle(LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_game_title, null));

        ad = new AvailablePlayerAdapter(getActivity(),null,0);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_game,null);
        ListView lv = (ListView)v.findViewById(R.id.lstAvailablePlayers);
        lv.setAdapter(ad);

        getLoaderManager().initLoader(LOADER_ID,null,this);

        gameTypeSelect = (Spinner)v.findViewById(R.id.spinNewGameType);
        description = (EditText)v.findViewById(R.id.txtNewGameDescription);

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
                    Game game = new Game(getActivity(),ad.chosenPlayers,(int)gameTypeSelect.getSelectedItemId(),description.getText().toString());
                    if(callbacks!=null) callbacks.newGameReady(game);
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
