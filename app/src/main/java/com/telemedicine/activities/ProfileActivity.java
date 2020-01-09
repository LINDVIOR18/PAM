package com.telemedicine.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.telemedicine.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("Registered")
public class ProfileActivity extends AppCompatActivity {

    private TextView profileName;
    private TextView profileBirthday;
    private TextView profileEmail;
    private TextView profilePhone;
    private TextView profileLocation;
    private ImageView profileImage;
    private String tok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = findViewById(R.id.profileName);
        profileBirthday = findViewById(R.id.profileBirthday);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);
        profileLocation = findViewById(R.id.profileLocation);
        profileImage = findViewById(R.id.profileImage);
        Token token = Token.getInstance();
        tok = token.getData();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(ProfileActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_notifications:
                        Toast.makeText(ProfileActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileActivity.this, NotificationsActivity.class));
                        break;
                    case R.id.action_newrequest:
                        Toast.makeText(ProfileActivity.this, "New Request", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_profile:
                        Toast.makeText(ProfileActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                        break;
                    case R.id.action_schedule:
                        Toast.makeText(ProfileActivity.this, "List of dosctors", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        break;
                }
                return true;
            }
        });

        String url = "http://81.180.72.17/api/Profile/GetProfile";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject mainObject = new JSONObject(response);
                    profileName.setText(mainObject.getString("FullName"));
                    profileBirthday.setText(mainObject.getString("Birthday"));
                    profileEmail.setText(mainObject.getString("Email"));
                    profilePhone.setText(mainObject.getString("Phone"));
                    profileLocation.setText(mainObject.getString("Address"));
                    String base64Image = mainObject.getString("Base64Photo");
                    byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    profileImage.setImageBitmap(decodedByte);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        requestQueue.add(request);
    }
}
