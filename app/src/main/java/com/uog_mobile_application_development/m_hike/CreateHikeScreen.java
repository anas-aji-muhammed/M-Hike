package com.uog_mobile_application_development.m_hike;

import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.HashMap;

public class CreateHikeScreen extends AppCompatActivity {
    Button addHikeButton;
    DatabaseHelper databaseHelper;
    EditText hikeNameField;
    EditText hikeLocationField;
    EditText hikeDateField;
    EditText hikeDescriptionField;
    EditText hikeLengthField;
    EditText hikeDifficultyField;
    RadioGroup parkingAvailabilitySelection;
    RadioButton parkingAvailabilitySelectionYES;
    RadioButton parkingAvailabilitySelectionNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hike_screen);
        databaseHelper = new DatabaseHelper(this);
        hikeNameField = findViewById(R.id.editTextHikeName);
        hikeLocationField = findViewById(R.id.editTextLocationName);
        hikeDateField = findViewById(R.id.editTextDate);
        hikeDescriptionField = findViewById(R.id.editTextHikeDescription);
        hikeLengthField = findViewById(R.id.editTextHikeLength);
        hikeDifficultyField = findViewById(R.id.editTextDifficultyLevel);
        parkingAvailabilitySelection = findViewById(R.id.parkingAvailabilityRadioGroup);
        parkingAvailabilitySelectionYES = findViewById(R.id.radioParkingAvailabilityYes);
        parkingAvailabilitySelectionNO = findViewById(R.id.radioParkingAvailabilityNO);
        addListenerOnButton();
    }

    public void onCreateHike() {
        HashMap<String, String> data = new HashMap<String, String>();
        if(validateFields()){
            data.put(hikeName, hikeNameField.getText().toString());
            data.put(hikeDate, hikeDateField.getText().toString());
            data.put(hikeDescription, hikeDescriptionField.getText().toString());
            data.put(hikeLength, hikeLengthField.getText().toString());
            data.put(hikeLocation, hikeLocationField.getText().toString());
            data.put(hikeObservations, "");
            data.put(parkingAvailability, "Test");
            data.put(hikeDifficulty, hikeDifficultyField.getText().toString());
            long result = databaseHelper.insertHike(data);
            Log.v("onCreateHike",  "Success" );
            Log.v("result", String.format("%s",result) );
            Toast.makeText(CreateHikeScreen.this, String.format("%s Hike Added", hikeNameField.getText().toString()),Toast.LENGTH_SHORT).show();
            finish();

        }
        else{
            Log.v(this.getClass().getName(), "onCreateHike" + "validation false" );
            Toast.makeText(CreateHikeScreen.this, String.format("%s Hike Failed to Add", hikeNameField.getText().toString()),Toast.LENGTH_SHORT).show();

        }






    }

    private Boolean validateFields(){
        if(hikeNameField.length()==0){
            hikeNameField.setError("Name is Required");
            return false;
        }
        if(hikeLocationField.length()==0){
            hikeLocationField.setError("Location is Required");
            return false;
        }
        if(hikeDateField.length()==0){
            hikeDateField.setError("Date is Required");
            return false;
        }
        if(hikeLengthField.length()==0){
            hikeLengthField.setError("Length is Required");
            return false;
        }
        if(hikeDifficultyField.length()==0){
            hikeDifficultyField.setError("Difficulty Level is Required");
            return false;
        }
        if(parkingAvailabilitySelection.getCheckedRadioButtonId() == -1){
            parkingAvailabilitySelectionNO.setError("Parking Availability is Required");
            return false;
        }

        return true;

    }


    public void addListenerOnButton() {

        final Context context = this;

        addHikeButton = (Button) findViewById(R.id.saveHikeButton);

        addHikeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onCreateHike();


            }

        });

    }
}

