package com.vitorjorge.movies.movies.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitorjorge.movies.movies.R;
import com.vitorjorge.movies.movies.utils.GetInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private GetInfo getInfo;
    private TextView version;

    //private TextView

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_about, container, false);

            version = (TextView) rootView.findViewById(R.id.tv_version_AboutId);
            getInfo = new GetInfo();
            String versionText = getInfo.getInformation(getContext());
            version.setText(versionText);

        return rootView;

    }

}
