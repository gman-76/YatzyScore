package com.projects.gerhardschoeman.yatzy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Gerhard on 25/11/2015.
 */
public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_fragment,container,false);

        Toolbar tb = (Toolbar)rootView.findViewById(R.id.tbAbout);
        tb.setNavigationIcon(R.drawable.menu);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolbarNavMenuCallbacks cb = (MainActivity) getActivity();
                cb.onClicked();
            }
        });

        TextView tv = (TextView)rootView.findViewById(R.id.txtAboutDetails);
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        return rootView;
    }
}
