package com.uog_mobile_application_development.m_hike;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uog_mobile_application_development.m_hike.databinding.ActivityHomeScreenBinding;
import com.uog_mobile_application_development.m_hike.models.HikeDataModel;
import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {

    ActivityHomeScreenBinding binding;
    Button createHikeButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addListenerOnButton();
        db = new DatabaseHelper(this);
        setHomepageGridData();


    }

    private void setHomepageGridData(){
        String [] hikeName = {"Greenwich Park", "North Calculka", "Lake District", "Hyde Park"};
        int [] hikeImages = {R.drawable.a, R.drawable.b,R.drawable.c,R.drawable.d,};
        ArrayList<HikeDataModel> data =  db.getAllHikeDetails();
        Log.v("Db Data", data.toString());


        HomeGridAdapter homeGridAdapter = new HomeGridAdapter(HomeScreen.this, hikeName,hikeImages, data);
        binding.gridView.setAdapter(homeGridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(HomeScreen.this, "You clicked on" +hikeName[i],Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addListenerOnButton() {

        final Context context = this;

        createHikeButton = (Button) findViewById(R.id.create_hike_button);

        createHikeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, CreateHikeScreen.class);
                startActivity(intent);

            }

        });

    }
}