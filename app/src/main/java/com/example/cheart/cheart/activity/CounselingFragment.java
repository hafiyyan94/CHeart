package com.example.cheart.cheart.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.cheart.cheart.MainActivity;
import com.example.cheart.cheart.R;
import com.example.cheart.cheart.adapter.CounselorListAdapter;
import com.example.cheart.cheart.app.VolleyAppController;
import com.example.cheart.cheart.adapter.MovieListAdapter;
import com.example.cheart.cheart.model.Counselor;
import com.example.cheart.cheart.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 4/30/2016.
 */
public class CounselingFragment extends Fragment {

    private static final String TAG = CounselingFragment.class.getSimpleName();
    private static final String url = "http://wsn.hol.es/counselor.json";
    private ProgressDialog pDialog;
    private List<Counselor> cList = new ArrayList<Counselor>();
    private ListView listView;
    private CounselorListAdapter adapter;


    public CounselingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity())
                .setActionBarTitle("List Counselor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.counseling_fragment, container, false);

        listView = (ListView) rootView.findViewById(R.id.list);
        adapter = new CounselorListAdapter(getActivity(), cList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Counselor profCoun = cList.get(position);
                Bundle bunProfile = new Bundle();
                bunProfile.putSerializable("counselor",profCoun);
                Fragment profileFragment = new CounselorProfileFragment();
                profileFragment.setArguments(bunProfile);
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container_body,profileFragment).addToBackStack(null).commit();

            }
        });

        pDialog = new ProgressDialog(this.getContext());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest cReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Counselor c = new Counselor();
                                c.setName(obj.getString("Nama"));
                                c.setDescription(obj.getString("Description"));
                                c.setIdCounselor(Integer.parseInt(obj.getString("Id")));
                                c.setThumbnailUrl(obj.getString("Image"));

                                cList.add(c);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });
        // Adding request to request queue
        VolleyAppController.getInstance().addToRequestQueue(cReq);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
