package com.uog_mobile_application_development.m_hike;

import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeDate;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeDescription;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeDifficulty;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeId;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.uog_mobile_application_development.m_hike.models.HikeDataModel;
import com.uog_mobile_application_development.m_hike.models.ObservationDataModel;
import com.uog_mobile_application_development.m_hike.utils.BottomSheetDialog;
import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfirmDetailsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView confirmTextView;
    TextView confirmTextLabel;
    String data = "";
    Button finalSaveButton;
    Button deleteHikeButton;
    Button addObservationButton;
    HikeDataModel hikeData;
    Boolean isForEdit;
    Intent i;

    ListView observationsListView;

    ArrayList<ObservationDataModel> observationDataArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_details);
        i = getIntent();
        setResult(0,i);//0 for going back and edit
        hikeData = (HikeDataModel)i.getSerializableExtra("hikeData");
        Log.v("hikeData iss", hikeData.toString() + hikeData.getHikeName());
        isForEdit = i.getBooleanExtra("isForEdit", false);

        confirmTextView = findViewById(R.id.hikeDetailsReview);
        confirmTextLabel = findViewById(R.id.confirmHikeLabel);
        confirmTextView.setTextSize(20);
        confirmTextLabel.setTextSize(30);
        finalSaveButton = findViewById(R.id.finalSaveButton);
        deleteHikeButton = findViewById(R.id.deleteButton);
        addObservationButton = findViewById(R.id.addObservationButton);
        observationsListView=(ListView)findViewById(R.id.observationsListView);


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

        setObservationsList();

        if(isForEdit){
            finalSaveButton.setText("Edit");
            deleteHikeButton.setVisibility(View.VISIBLE);

        }
    }

    public void setObservationsList(){
        observationDataArrayList = databaseHelper.getAllObservations();
        if(observationDataArrayList.isEmpty()){

        }
        else{
            ObservationsListAdapter adapter=new ObservationsListAdapter(observationDataArrayList, this);
            observationsListView.setAdapter(adapter);
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

        deleteHikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.v("deleteHikeButton",  "on click" );
                int result;
                result = databaseHelper.deleteHike(String.format("%s", hikeData.getHikeId()));
                Log.v("result", String.format("%s",result) );
                Toast.makeText(ConfirmDetailsActivity.this, String.format("%s Hike Deleted", hikeData.getHikeName()),Toast.LENGTH_LONG).show();
                setResult(1,i);//1 for going back to homepage after success

                finish();
            }
        });
        addObservationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.v("addObservationButton",  "on click" );
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(),
                        "ModalBottomSheet");


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
            setResult(1,i);//1 for going back to homepage after success

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
            data.put(hikeDifficulty, hikeData.getHikeDifficulty());
            data.put(hikeId, String.format("%s",hikeData.getHikeId()));
            long result = databaseHelper.insertHike(data, true);
            Log.v("editHike",  "Success" );
            Log.v("result", String.format("%s",result) );
            Toast.makeText(ConfirmDetailsActivity.this, String.format("%s Hike updated", hikeData.getHikeName()),Toast.LENGTH_LONG).show();
            setResult(1,i);//1 for going back to homepage after success

            finish();

        }


}