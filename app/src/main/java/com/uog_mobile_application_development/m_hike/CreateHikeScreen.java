package com.uog_mobile_application_development.m_hike;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.HashMap;

public class CreateHikeScreen extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hike_screen);
        Button createHikeButton = findViewById(R.id.create_hike_button);
        Button viewHikeButton = findViewById(R.id.viewHike);
        TextView viewHikeText = findViewById(R.id.viewHikeText);
    }

    public void onCreateHike() {
        final String hikeName = "hike_name";
        final String hikeId = "hike_id";
        final String hikeLocation = "hike_location";
        final String hikeDate = "hike_date";
        final String parkingAvailability = "parking_availability";
        final String hikeLength = "hike_length";
        final String hikeDifficulty = "hike_difficulty";
        final String hikeDescription = "hike_description";
        final String hikeObservations = "hike_observations";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put(hikeName, "Test");
        data.put(hikeDate, "Test");
        data.put(hikeDescription, "Test");
        data.put(hikeLength, "Test");
        data.put(hikeLocation, "Test");
        data.put(hikeObservations, "Test");
        data.put(parkingAvailability, "Test");
        data.put(hikeDifficulty, "Test");
      /** databaseHelper.insertData(
                "hikeDetailsTableName", data); I placed a comment on this database insertion code
       because it was throwing an error**/
        Log.v(this.getClass().getName(), "onCreateHike" + "Success" );




    }

    public void onViewHike(){

    }
}

