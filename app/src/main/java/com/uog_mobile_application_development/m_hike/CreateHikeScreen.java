package com.uog_mobile_application_development.m_hike;

import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

import com.uog_mobile_application_development.m_hike.models.HikeDataModel;
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
    Intent i;
    Boolean isForEdit;
    HikeDataModel hikeData;

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

        i = getIntent();
        isForEdit = i.getBooleanExtra("isForEdit", false);
        hikeData = (HikeDataModel)i.getSerializableExtra("hikeData");

        if(isForEdit){
            hikeNameField.setText(hikeData.getHikeName());
            hikeLocationField.setText(hikeData.getHikeLocation());
            hikeDateField.setText(hikeData.getHikeDate());
            hikeDescriptionField.setText(hikeData.getHikeDescription());
            hikeLengthField.setText(hikeData.getHikeLength());
            hikeDifficultyField.setText(hikeData.getHikeDifficulty());
            if(hikeData.getParkingAvailability().equals("YES")){
                parkingAvailabilitySelection.check(R.id.radioParkingAvailabilityYes);

            }
            else{
                parkingAvailabilitySelection.check(R.id.radioParkingAvailabilityNO);

            }
        }


        addListenerOnButton();
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
//                new AlertDialog.Builder(CreateHikeScreen.this)
//                        .setTitle("Confirm")
//                        .setMessage("Do you really want to whatever?")
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                Toast.makeText(MainActivity.this, "Yaay", Toast.LENGTH_SHORT).show();
//                            }})
//                        .setNegativeButton(android.R.string.no, null).show();
//                onCreateHike();
                if(validateFields()) {
                    Intent intent = new Intent(context, ConfirmDetailsActivity.class);
                    Log.v("Parking availability", String.format("%s",parkingAvailabilitySelection.getCheckedRadioButtonId() ));
                    String parkAvailability = "";
                    if(parkingAvailabilitySelection.getCheckedRadioButtonId() == R.id.radioParkingAvailabilityYes){
                        parkAvailability = "YES";
                    }
                    else{
                        parkAvailability = "NO";

                    }
                    HikeDataModel hike= new HikeDataModel(
                            hikeNameField.getText().toString(),
                            hikeLocationField.getText().toString(),
                            hikeDateField.getText().toString(),
                            parkAvailability,
                            hikeLengthField.getText().toString(),
                            hikeDifficultyField.getText().toString(),
                            hikeDescriptionField.getText().toString()
                    );
                    if(isForEdit){
                        Log.v("hikeData.getHikeId()", String.format("%s",hikeData.getHikeId()));

                        hike.setHikeId(hikeData.getHikeId());
                    }
                    else{
                        hike.setHikeId(-1);
                        Log.v("hikeData.getHikeId()", String.format("%s",hike.getHikeId()));
                    }

                    Log.v("isForEdit", String.format("%s",isForEdit));


                    intent.putExtra("hikeData", hike);
                    intent.putExtra("isForEdit", false);
                    startActivityForResult(intent, 0);
                }
                else {
                    Log.v(this.getClass().getName(), "onCreateHike" + "validation false" );
                    Toast.makeText(CreateHikeScreen.this, String.format("%s Hike Failed to Add", hikeNameField.getText().toString()),Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

    @SuppressLint("LongLogTag")
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("onActivityResult",  String.format("Called" ));
        Log.v("onActivityResult resultCode",  String.format("%s", resultCode ));
        Log.v("onActivityResult int extra",  String.format("%s", i.getIntExtra("nav",0) ));

        if(resultCode==1){
            Log.v("onActivityResult resultCode",  String.format("%s", resultCode ));
            finish();
        }
    }
}

