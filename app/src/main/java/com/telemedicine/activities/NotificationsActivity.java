package com.telemedicine.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.telemedicine.R;

@SuppressLint("Registered")
public class NotificationsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_notifications);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(NotificationsActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NotificationsActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_notifications:
                        Toast.makeText(NotificationsActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NotificationsActivity.this, NotificationsActivity.class));
                        break;
                    case R.id.action_newrequest:
                        Toast.makeText(NotificationsActivity.this, "New Request", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NotificationsActivity.this, HomeActivity.class));
                        break;
                    case R.id.action_profile:
                        Toast.makeText(NotificationsActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NotificationsActivity.this, ProfileActivity.class));
                        break;
                    case R.id.action_schedule:
                        Toast.makeText(NotificationsActivity.this, "List of dosctors", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NotificationsActivity.this, MainActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    public void selectImage(View view) {
    }
}
