package com.telemedicine.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.telemedicine.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    Token token = Token.getInstance();
    private ImageView imageView;
    private EditText fullNameEditText;
    private EditText birthday;
    private EditText email;
    private EditText phoneNumber;
    private EditText address;
    private EditText password;

    public static String ConvertBitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        imageView = findViewById(R.id.imageView2);
        fullNameEditText = findViewById(R.id.editTextFullName);
        birthday = findViewById(R.id.editTextBirthday);
        email = findViewById(R.id.editTextEmail);
        phoneNumber = findViewById(R.id.editTextPhoneNumber);
        address = findViewById(R.id.editTextAddress);
        password = findViewById(R.id.editTextPassword);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void register(View view) {
        String url = "http://81.180.72.17/api/Register/UserReg";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObject = new JSONObject(response);
                    String contentToken = mainObject.getString("Message");
                    token.setData(contentToken);
                    startActivity(new Intent(SignUpActivity.this, HomeActivity.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String str = new String(((ClientError) error).networkResponse.data, StandardCharsets.UTF_8);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("FullName", fullNameEditText.getText().toString());
                params.put("Birthday", birthday.getText().toString());
                params.put("Email", email.getText().toString());
                params.put("Phone", phoneNumber.getText().toString());
                params.put("Address", address.getText().toString());
                params.put("Username", email.getText().toString());
                params.put("Password", password.getText().toString());
                //params.put("Base64Photo", ConvertBitmapToString(finalImage));
                params.put("Base64Photo", "/9j/4AAQSkZJRgABAQAAAQABAAD/4QAsRXhpZgAASUkqAAgAAAABADsBAgAKAAAAGgAAAAAAAABSYWR1IFpNRVUA/+0AelBob3Rvc2hvcCAzLjAAOEJJTQQEAAAAAABdHAJQAAlSYWR1IFpNRVUcAigASkZCTUQwZjAwMDc5MjAxMDAwMGVjMDEwMDAwOTQwMjAwMDBmMDAyMDAwMDQ3MDMwMDAwZWIwMzAwMDA5MjA0MDAwMGEyMDQwMDAwAP/bAEMACwgICggHCwoJCg0MCw0RHBIRDw8RIhkaFBwpJCsqKCQnJy0yQDctMD0wJyc4TDk9Q0VISUgrNk9VTkZUQEdIRf/bAEMBDA0NEQ8RIRISIUUuJy5FRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRUVFRf/CABEIACwAMAMBIgACEQEDEQH/xAAZAAADAQEBAAAAAAAAAAAAAAAEBQYBAwL/xAAaAQACAwEBAAAAAAAAAAAAAAAABAECAwUG/9oADAMBAAIQAxAAAAC+NVDeffpdl9iHWJm+gGmOFi3rkKYGv5x7YKQ2nFvCOyi6cdtI9N99xL//xAAgEAACAgIDAQADAAAAAAAAAAACAwEEABIFESETEBQy/9oACAEBAAEHASuytxAu4pmTYVGQ9c4d5Y5VsFYMsujrZEvnvmmvkK3yY0zjx6RtbaDjDLnIlx9lOU3ss0Ybc5EuPsoyo87VH61bAQoQuCUMgwcf8cgDD6ZWElVtScbJyjDAX2dICGcasgPqAkp71IY6r1pb5FUQmJwwE46/RTOBSSPsefj/xAAgEQACAwACAQUAAAAAAAAAAAACAwABBBESBRMhMUFh/9oACAECAQE/AGbnpdYfMryzLLjpMmt2h/UvapT6TqI2QNaBdb/q4hvqaiMJrxC6ualYDsuOZmyAgf2f/8QAHhEAAgMAAgMBAAAAAAAAAAAAAgMAAREEEhMUITH/2gAIAQMBAT8ApKyCinrBn7GKWte1LRZprrC4xeKq2MV0ThRTyC8uXyRyMcTPs//EACYQAAICAAQGAgMAAAAAAAAAAAECABEDEiFBIjFRUmFxEHIEQpH/2gAIAQEACD8BZbAO0zV7hxF/sDrF4/UIAUTqJluVUCyqnUxCGAjIW/HOHt3TFFEucv1jIWwGw9u6Ywq3OX6wniuqjoFsbNCquvOmERyDyyg6Rqxb1p9RH0A0oaQYa5WPO4SzN3ExuFxv1jm/AgYZfM/Tc9Zhlk8Dkfhhc4h6My37MHx//8QAHRABAAICAgMAAAAAAAAAAAAAAQARIUExYVFxgf/aAAgBAQABHhD7c4BKILvGERoj3ASqDuNpZeIEhXBuVFgkK9LsEorXdkyMvolJGwl+jLQDAFUiv9ZbRE4O+4RFp72hVKPfcF/yEcxaMhcGuowmUUQLAgBYVeEYnEYBKEE5UPOEtaPFpIO0QRZA0QOAl8NojJKkiHLxV2HKwIgGO48Ai8gM3dACgATU/8QAGRAAAQUAAAAAAAAAAAAAAAAAIBARIUBB/9oACAEBAB8/EKjLhQf/2Q==");

                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }
        };

        requestQueue.add(request);
    }

    @SuppressLint("IntentReset")
    public void selectImage(View view) {
        @SuppressLint("IntentReset") Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(Objects.requireNonNull(data.getData())));
                Bitmap finalImage = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
                imageView.setImageBitmap(finalImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
