package com.telemedicine.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.ClientError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.telemedicine.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("Registered")
public class MainActivity extends AppCompatActivity {

    private DividerItemDecoration dividerItemDecoration;
    private RequestQueue requestQueue;
    private List<Doctor> doctorList;
    private RecyclerView.Adapter adapter;
    private String tok;
    private String url = "http://81.180.72.17/api/Doctor/GetDoctorList";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mList = findViewById(R.id.main_list);

        doctorList = new ArrayList<>();
        adapter = new DoctorAdapter(getApplicationContext(), doctorList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);
        Token token = Token.getInstance();
        tok = token.getData();

        getData();
    }

    private void getData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Doctor doctor = new Doctor();
                        doctor.setFullName(jsonObject.getString("FullName"));
                        doctor.setAddress(jsonObject.getString("Address"));
                        doctor.setDocSpecs(jsonObject.getString("Specs"));
                        doctor.setDocStars(jsonObject.getDouble("Stars"));
                        doctor.setStarsIcon("@mipmap/strar");
                        doctor.setLocationIcon("@mipmap/positionicon");
                        doctor.setPhoto(jsonObject.getString("Photo"));
                        doctor.setABout(jsonObject.getString("ABout"));
                        doctorList.add(doctor);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String str = new String(((ClientError) error).networkResponse.data, StandardCharsets.UTF_8);
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();

                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("token", tok);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
