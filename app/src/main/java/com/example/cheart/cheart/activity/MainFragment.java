package com.example.cheart.cheart.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cheart.cheart.MainActivity;
import com.example.cheart.cheart.R;

/**
 * Created by Administrator on 4/29/2016.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity())
                .setActionBarTitle("CHeart");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        Button counselingButton = (Button) rootView.findViewById(R.id.counselingButton);
        Button chatButton = (Button) rootView.findViewById(R.id.chatButton);

        counselingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getActivity().getApplicationContext(),"Counseling Coy",Toast.LENGTH_SHORT);
                //Log.d("hello","Counseling");
                Fragment counselingFragment = new CounselingFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container_body, counselingFragment).addToBackStack(null).commit();

            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity().getApplicationContext(), "Chat Coy", Toast.LENGTH_SHORT);
                Log.d("hello2","Chat");
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




}
