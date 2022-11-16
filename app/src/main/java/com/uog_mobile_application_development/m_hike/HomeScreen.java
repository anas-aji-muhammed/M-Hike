package com.uog_mobile_application_development.m_hike;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uog_mobile_application_development.m_hike.databinding.ActivityHomeScreenBinding;


public class HomeScreen extends AppCompatActivity {

    ActivityHomeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String [] hikeName = {"Greenwich Park", "North Calculka", "Lake District", "Hyde Park"};
        int [] hikeImages = {R.drawable.a, R.drawable.b,R.drawable.c,R.drawable.d,};

        HomeGridAdapter homeGridAdapter = new HomeGridAdapter(HomeScreen.this, hikeName,hikeImages);
        binding.gridView.setAdapter(homeGridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HomeScreen.this, "You clicked on" +hikeName[i],Toast.LENGTH_SHORT).show();
            }
        });
    }
}