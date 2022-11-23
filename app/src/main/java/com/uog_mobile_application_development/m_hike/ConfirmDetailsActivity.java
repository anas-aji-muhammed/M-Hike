package com.uog_mobile_application_development.m_hike;

import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeDate;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeDescription;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeDifficulty;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeLength;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeLocation;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeName;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeObservations;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.parkingAvailability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uog_mobile_application_development.m_hike.models.HikeDataModel;
import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.HashMap;

public class ConfirmDetailsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView confirmTextView;
    TextView confirmTextLabel;
    String data = "";
    Button finalSaveButton;
    HikeDataModel hikeData;
    Boolean isForEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_details);
        Intent i = getIntent();
        hikeData = (HikeDataModel)i.getSerializableExtra("hikeData");
        Log.v("hikeData iss", hikeData.toString() + hikeData.getHikeName());
        isForEdit = i.getBooleanExtra("isForEdit", false);

        confirmTextView = findViewById(R.id.hikeDetailsReview);
        confirmTextLabel = findViewById(R.id.confirmHikeLabel);
        confirmTextView.setTextSize(20);
        confirmTextLabel.setTextSize(30);
        finalSaveButton = findViewById(R.id.finalSaveButton);
        addListenerOnButton();
        databaseHelper = new DatabaseHelper(this);
        data = "\n \n" + "Hike Name - " + hikeData.getHikeName() + "\n" +
                "Hike Date - " + hikeData.getHikeDate() + "\n" +
                "Hike Description - " + hikeData.getHikeDescription() + "\n" +
                "Hike Difficulty - " + hikeData.getHikeDifficulty() + "\n" +
                "Hike Location - " + hikeData.getHikeLocation() + "\n" +
                "Parking Availability - " + hikeData.getParkingAvailability() + "\n" +
                "Hike Length - " + hikeData.getHikeLength() + "\n";

        confirmTextView.setText(data);
        if(isForEdit){
            finalSaveButton.setText("Edit");

        }
    }

    public void addListenerOnButton() {

        final Context context = this;


        finalSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(isForEdit){
                    Intent intent = new Intent(context, CreateHikeScreen.class);
                    intent.putExtra("isForEdit", true);
                    intent.putExtra("hikeData", hikeData);

                    finish();
                    startActivity(intent);

                }
                else if(hikeData.getHikeId()!=-1){

                    editHike();
                }
                else{
                    saveHike();
                }

            }

        });

    }

    public void saveHike() {
        Log.v("saveHike",  "Called" );

        HashMap<String, String> data = new HashMap<String, String>();
            data.put(hikeName, hikeData.getHikeName());
            data.put(hikeDate, hikeData.getHikeDate());
            data.put(hikeDescription, hikeData.getHikeDescription());
            data.put(hikeLength, hikeData.getHikeLength());
            data.put(hikeLocation, hikeData.getHikeLocation());
            data.put(hikeObservations, "");
            data.put(parkingAvailability, hikeData.getParkingAvailability());
            data.put(hikeDifficulty, hikeData.getParkingAvailability());
            long result = databaseHelper.insertHike(data, false);
            Log.v("onCreateHike",  "Success" );
            Log.v("result", String.format("%s",result) );
            Toast.makeText(ConfirmDetailsActivity.this, String.format("%s Hike Added", hikeData.getHikeName()),Toast.LENGTH_LONG).show();
            finish();

        }
    public void editHike() {
        Log.v("editHike",  "Called" );

        HashMap<String, String> data = new HashMap<String, String>();
            data.put(hikeName, hikeData.getHikeName());
            data.put(hikeDate, hikeData.getHikeDate());
            data.put(hikeDescription, hikeData.getHikeDescription());
            data.put(hikeLength, hikeData.getHikeLength());
            data.put(hikeLocation, hikeData.getHikeLocation());
            data.put(hikeObservations, "");
            data.put(parkingAvailability, hikeData.getParkingAvailability());
            data.put(hikeDifficulty, hikeData.getParkingAvailability());
            long result = databaseHelper.insertHike(data, true);
            Log.v("editHike",  "Success" );
            Log.v("result", String.format("%s",result) );
            Toast.makeText(ConfirmDetailsActivity.this, String.format("%s Hike updated", hikeData.getHikeName()),Toast.LENGTH_LONG).show();
            finish();

        }
}