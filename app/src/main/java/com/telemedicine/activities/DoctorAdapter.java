package com.telemedicine.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.telemedicine.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private Context context;
    private List<Doctor> list;

    public DoctorAdapter(Context context, List<Doctor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Doctor doctor = list.get(position);

        holder.textFullName.setText(doctor.getFullName());
        holder.textAddress.setText(String.valueOf(doctor.getAddress()));
        holder.textSpecs.setText(String.valueOf(doctor.getSpecs()));
        holder.textStars.setText(String.valueOf(doctor.getStars()));
        holder.stars.setImageResource(R.mipmap.strar);
        holder.location.setImageResource(R.mipmap.positionicon);
        String base64Image = doctor.getPhoto();
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.photo.setImageBitmap(decodedByte);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textFullName, textAddress, textStars, textSpecs, id;
        public ImageView stars, location, photo;

        public ViewHolder(View itemView) {
            super(itemView);

            textFullName = itemView.findViewById(R.id.full_name);
            textAddress = itemView.findViewById(R.id.address_doctor);
            textStars = itemView.findViewById(R.id.stars_doctor);
            textSpecs = itemView.findViewById(R.id.specs_doc);
            stars = itemView.findViewById(R.id.stars_icon);
            location = itemView.findViewById(R.id.locationIcon);
            photo = itemView.findViewById(R.id.Photo);
        }
    }
}
