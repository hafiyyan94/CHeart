package com.example.cheart.cheart.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cheart.cheart.R;



/**
 * Created by Administrator on 4/30/2016.
 */
public class CounselingFragment extends Fragment {
    public CounselingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.counseling_fragment, container, false);


        return rootView;
    }


}
