package com.example.cheart.cheart.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.cheart.cheart.MainActivity;
import com.example.cheart.cheart.R;
import com.example.cheart.cheart.app.VolleyAppController;
import com.example.cheart.cheart.model.Counselor;

/**
 * Created by Administrator on 5/7/2016.
 */
public class CounselorProfileFragment extends Fragment {
    private static final String TAG = CounselorProfileFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    ImageLoader imageLoader = VolleyAppController.getInstance().getImageLoader();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity())
                .setActionBarTitle("Profile");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.profile_counselor_layout_fragment, container, false);
        pDialog = new ProgressDialog(this.getContext());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        if (imageLoader == null)
            imageLoader = VolleyAppController.getInstance().getImageLoader();

        Bundle bun = getArguments();

        final Counselor coun = (Counselor) bun.getSerializable("counselor");

        NetworkImageView thumbNail = (NetworkImageView) rootView.findViewById(R.id.thumbnail);
        TextView name = (TextView) rootView.findViewById(R.id.name);
        TextView desc = (TextView) rootView.findViewById(R.id.desc);
        Button daftarBut = (Button) rootView.findViewById(R.id.daftar);

        daftarBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bunProfile = new Bundle();
                bunProfile.putSerializable("counselor", coun);
                Fragment profileFragment = new RegisterFragment();
                profileFragment.setArguments(bunProfile);
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container_body, profileFragment).addToBackStack(null).commit();

            }
        });

        if (coun != null) {
            thumbNail.setImageUrl(coun.getThumbnailUrl(), imageLoader);
            name.setText(coun.getName());
            desc.setText(coun.getDescription());
        }

        hidePDialog();
        return rootView;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
