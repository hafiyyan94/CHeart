package com.example.cheart.cheart.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cheart.cheart.MainActivity;
import com.example.cheart.cheart.R;
import com.example.cheart.cheart.model.Counselor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 5/21/2016.
 */
public class RegisterFragment extends Fragment {

    MainActivity main;
    private EditText problem;
    private Button regBut;
    private Button cancBut;
    private final String registerURL = "http://cheart.web.id/androidservice/coba.php";
    private final String keyIdCounselor = "id_counselor";
    private final String keyIdClient = "id_client";
    private final String id_client = "1";
    private final String flag_registeration = "P";
    private final String keyProblemDescription = "problem_description";
    private final String keyFlagRegisteration = "flag_registeration";


    public RegisterFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity())
                .setActionBarTitle("Daftar");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.register_counseling_layout, container, false);

        problem = (EditText) rootView.findViewById(R.id.problem);
        regBut = (Button) rootView.findViewById(R.id.daftarUp);
        cancBut = (Button) rootView.findViewById(R.id.cancelUp);

        regBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        cancBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment profileFragment = new CounselingFragment();
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.container_body, profileFragment).addToBackStack(null).commit();
            }
        });

        return rootView;


    }

    private void register(){
        final String problem_description = problem.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, registerURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(main.getBaseContext(), response, Toast.LENGTH_LONG).show();
                        Log.d("responseReq", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(main.getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
                        VolleyLog.d(RegisterFragment.class.getSimpleName(), "Error: " + error.getMessage());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Bundle bun = getArguments();
                Counselor coun = (Counselor) bun.getSerializable("counselor");
                Map<String,String> params = new HashMap<String, String>();
                params.put(keyFlagRegisteration,"I");
                params.put(keyIdCounselor,String.valueOf(coun.getIdCounselor()) );
                params.put(keyIdClient,id_client);
                params.put(keyProblemDescription,problem_description);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
    }
}
