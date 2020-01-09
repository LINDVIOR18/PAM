package com.telemedicine.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.telemedicine.R;

@SuppressLint("Registered")
public class HomeActivity extends AppCompatActivity {

    private EditText name;
    private EditText desease;
    private EditText location;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.editTextName);
        desease = findViewById(R.id.editDesease);
        location = findViewById(R.id.editYourLocation);
        description = findViewById(R.id.editDescription);
//        Token token = Token.getInstance();
//        String tok = token.getData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_notifications:
                        Toast.makeText(HomeActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
                        break;
                    case R.id.action_newrequest:
                        Toast.makeText(HomeActivity.this, "New Request", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_profile:
                        Toast.makeText(HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                        break;
                    case R.id.action_schedule:
                        Toast.makeText(HomeActivity.this, "List of dosctors", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    public void addConsultation(View view) {


        name.setText("");
        desease.setText("");
        location.setText("");
        description.setText("");
        startActivity(new Intent(HomeActivity.this, NotificationsActivity.class));
//        String url = "http://81.180.72.17/api/Doctor/AddConsultation";
//
//        requestQueue = Volley.newRequestQueue(this);
//
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
//
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                try {
//                    String str = new String(((NetworkResponse)((ClientError)error).networkResponse).data, "UTF-8");
////                    Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//
//            }})
//        {
//
//            @Override
//            public Map<String,String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/x-www-form-urlencoded");
//                params.put("token", tok);
//                return params;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded";
//            }
//        };
//
//        requestQueue.add(request);

    }


}
