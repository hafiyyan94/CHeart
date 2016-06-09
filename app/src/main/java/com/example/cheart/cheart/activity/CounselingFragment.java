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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cheart.cheart.MainActivity;
import com.example.cheart.cheart.R;
import com.example.cheart.cheart.adapter.CounselorListAdapter;
import com.example.cheart.cheart.app.VolleyAppController;
import com.example.cheart.cheart.model.Counselor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 4/30/2016.
 */
public class CounselingFragment extends Fragment {

    private static final String TAG = CounselingFragment.class.getSimpleName();
    private static final String url = "http://cheart.web.id/androidservice/counselor.php";
    private ProgressDialog pDialog;
    private List<Counselor> cList = new ArrayList<Counselor>();
    private ListView listView;
    private CounselorListAdapter adapter;
    private String[] keyJSONarr = {"id_counselor","name","profession","bdate","image","longitude","latitude"};

    public CounselingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity())
                .setActionBarTitle("List Counselor");
        pDialog = new ProgressDialog(this.getContext());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        adapter = new CounselorListAdapter(getActivity(), cList);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
                Log.d("response",response);
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(CounselingFragment.class.getSimpleName(), "Error: " + error.getMessage());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.counseling_fragment, container, false);

        listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Counselor profCoun = cList.get(position);
                Bundle bunProfile = new Bundle();
                bunProfile.putSerializable("counselor", profCoun);
                Fragment profileFragment = new CounselorProfileFragment();
                profileFragment.setArguments(bunProfile);
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container_body, profileFragment).addToBackStack(null).commit();

            }
        });

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

    @Override
    public void onResume(){
        super.onResume();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void showJSON(String response){
        int id_counselor = 0;
        String name="";
        String profession="";
        String bdate = "";
        String imageLink = "";
        double longitude = 0.0;
        double latitude = 0.0;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("root");
            for(int index = 0; index < result.length(); index++){
                JSONObject counselorData = result.getJSONObject(index);
                id_counselor = Integer.parseInt(counselorData.getString(keyJSONarr[0]));
                name = counselorData.getString(keyJSONarr[1]);
                profession = counselorData.getString(keyJSONarr[2]);
                bdate = counselorData.getString(keyJSONarr[3]);
                imageLink = counselorData.getString(keyJSONarr[4]);
                longitude = counselorData.getDouble(keyJSONarr[5]);
                latitude = counselorData.getDouble(keyJSONarr[6]);
                cList.add(new Counselor(id_counselor,name,profession,bdate,imageLink,longitude,latitude));
                Log.d("counselor",cList.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }
}
