package com.example.ajindal1.foodforfavors;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ajindal1.foodforfavors.R;

/**
 * Created by ajindal1 on 9/12/15.
 */
public class FeederFragment extends ListFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }


}
