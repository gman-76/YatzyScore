package com.projects.gerhardschoeman.yatzy;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;

/**
 * Created by Gerhard on 24/11/2015.
 */
public class PlayersFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
                                                         EditPlayerDialog.DialogClosedCallBack,
                                                         PlayerListViewHolder.TouchCallback{

    private static final String LOGTAG = PlayersFragment.class.getSimpleName();

    private final int LOADER_ID=0;

    private PlayerAdapter ad;

    private final String EDITDLGTAG = "EDITDLGTAG";

    @Override
    public void onResume() {
        Log.d(LOGTAG,"onResume()");
        super.onResume();
        getLoaderManager().restartLoader(LOADER_ID,null,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.player_fragment,container,false);

        ad = new PlayerAdapter(getActivity(),null,0);

        ad.viewholderCallback = this;

        ListView lv = (ListView)rootView.findViewById(R.id.lstPlayers);
        ad.listview = lv;
        lv.setAdapter(ad);

        /*lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });*/

        final PlayersFragment thisFragment = this;

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.btAddPlayer);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPlayerDialog dg = new EditPlayerDialog();
                dg.setDlgCallback(thisFragment);
                dg.show(getFragmentManager(), EDITDLGTAG);
            }
        });

        Toolbar tb = (Toolbar)rootView.findViewById(R.id.tbPlayerList);
        tb.setNavigationIcon(R.drawable.menu);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolbarNavMenuCallbacks cb = (MainActivity) getActivity();
                cb.onClicked();
            }
        });

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                DataContract.PlayerEntry.CONTENT_URI,
                DataProjections.Player_ALL.COLUMNS,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ad.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        ad.swapCursor(null);
    }

    @Override
    public void dialogClosed(boolean dataRefreshed) {
        if(dataRefreshed){
            getLoaderManager().restartLoader(LOADER_ID,null,this);
        }
    }

    @Override
    public void clickedPlayer(String name) {
        Log.d(LOGTAG,"received callback for click on " + name);
        EditPlayerDialog dg = new EditPlayerDialog();
        dg.setDlgCallback(this);
        Bundle b = new Bundle();
        b.putString(EditPlayerDialog.PLAYERNAME_ARG,name);
        dg.setArguments(b);
        dg.show(getFragmentManager(), EDITDLGTAG);
    }

    @Override
    public void deletePlayer(String name) {
        Log.d(LOGTAG,"received callback to delete " + name);
        int r = getActivity().getContentResolver().delete(DataContract.PlayerEntry.getUriFromName(name),null,null);
        if(r>0){
            getLoaderManager().restartLoader(LOADER_ID,null,this);
        }
    }
}
