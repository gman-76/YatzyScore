package com.projects.gerhardschoeman.yatzy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;

/**
 * Created by Gerhard on 26/11/2015.
 */
public class GamesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String LOGTAG = GamesFragment.class.getSimpleName();

    private final int LOADER_ID = 0;

    private GameAdapter ad;

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().restartLoader(LOADER_ID,null,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gamelist_fragment,container,false);

        ad = new GameAdapter(getActivity(),null,0);

        ListView lv = (ListView)rootView.findViewById(R.id.lstGames);
        lv.setAdapter(ad);

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.btAddGame);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddGameDialog dg = new AddGameDialog();
                dg.show(getFragmentManager(),"ADDGAMEDLG");
            }
        });

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                DataContract.GameEntry.CONTENT_URI,
                DataProjections.Game_ALL.COLUMNS,
                null,null,null);
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
