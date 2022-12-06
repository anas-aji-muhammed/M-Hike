package com.uog_mobile_application_development.m_hike;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uog_mobile_application_development.m_hike.databinding.ActivityHomeScreenBinding;
import com.uog_mobile_application_development.m_hike.models.APIIndividualHikeDetailsModel;
import com.uog_mobile_application_development.m_hike.models.APIResponseModel;
import com.uog_mobile_application_development.m_hike.models.HikeDataModel;
import com.uog_mobile_application_development.m_hike.models.PostHikeDataModel;
import com.uog_mobile_application_development.m_hike.utils.api_service.APIClient;
import com.uog_mobile_application_development.m_hike.utils.api_service.APIInterface;
import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {

    ActivityHomeScreenBinding binding;
    Button createHikeButton;
    Button postHikeButton;
    Button deleteHikesButton;
    SearchView searchView;
    DatabaseHelper db;
    APIInterface apiInterface;
    ArrayList<HikeDataModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addListenerOnButton();
        db = new DatabaseHelper(this);
        setHomepageGridData(false, "");
        apiInterface = APIClient.getClient().create(APIInterface.class);


    }

    private void setHomepageGridData(boolean isForSearch, String query){
        int [] hikeImages = {R.drawable.a, R.drawable.b,R.drawable.c,R.drawable.d,};
        if(isForSearch){
            data =  db.searchHikes(query);
        }
        else{
            data =  db.getAllHikeDetails();
        }
        Log.v("Db Data", data.toString());

//        if(data.isEmpty()){
//            deleteHikesButton.setVisibility(View.GONE);
//            postHikeButton.setVisibility(View.GONE);
//
//        }

        HomeGridAdapter homeGridAdapter = new HomeGridAdapter(HomeScreen.this,hikeImages, data);
        binding.gridView.setAdapter(homeGridAdapter);

        binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeScreen.this, ConfirmDetailsActivity.class);
//                HikeDataModel hikeData = new HikeDataModel(
//                        data.get(i).getHikeName(),
//                        data.get(i).getHikeLocation(),
//                        data.get(i).getHikeDate(),
//                        data.get(i).getParkingAvailability(),
//                        data.get(i).getHikeLength(),
//                        data.get(i).getHikeDifficulty(),
//                        data.get(i).getHikeDescription()
//
//                );
//                hikeData.setHikeId(data.get(i).getHikeId());
                Log.v("Onclick item ", String.format("%s", data.get(i).getHikeId()));

                intent.putExtra("hikeData", data.get(i));
                intent.putExtra("isForEdit", true);
                startActivity(intent);
            }
        });
    }


    /*
    Listener for registering button and
     */
    public void addListenerOnButton() {

        final Context context = this;

        createHikeButton = (Button) findViewById(R.id.create_hike_button);
        postHikeButton = (Button) findViewById(R.id.saveHikeToAPIButton);
        deleteHikesButton = (Button) findViewById(R.id.clearDBButton);
        searchView = (SearchView) findViewById(R.id.HikeSearch);

        createHikeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, CreateHikeScreen.class);
                startActivity(intent);

            }

        });
        postHikeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                postHikeData();

            }

        });
        deleteHikesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                builder.setMessage(R.string.clear_db_alert_message)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                db.deleteAllHikes();
//                                setHomepageGridData();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                // Create the AlertDialog object and return it
                builder.create();


            }

        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                setHomepageGridData(true, s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    setHomepageGridData(true, s);
                }
                else{
                    setHomepageGridData(false, "");
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("onResume ", "Called");

        setHomepageGridData(false, "");
    }

    /**
     Post Hikes Data
     **/
    public void postHikeData(){
        PostHikeDataModel postData = new PostHikeDataModel();
        postData.setUserId("aa6048n");
        ArrayList<APIIndividualHikeDetailsModel> detailsModelList = new ArrayList<>();
        for (int i=0; i< data.size(); i++){
            APIIndividualHikeDetailsModel individualHikeDetailsData = new APIIndividualHikeDetailsModel();
            individualHikeDetailsData.setName(data.get(i).getHikeName());
            individualHikeDetailsData.setDate(data.get(i).getHikeDate());
            detailsModelList.add(individualHikeDetailsData);
        }
        postData.setDetailList(detailsModelList);
        Call<APIResponseModel> postDataCall = apiInterface.postHikesData(postData);
        postDataCall.enqueue(new Callback<APIResponseModel>() {
            @Override
            public void onResponse(Call<APIResponseModel> call, Response<APIResponseModel> response) {
                Log.v("Api Response", String.format("%s", response.toString()));
                Toast.makeText(getApplication(),
                        String.format("Data Uploaded %s", response.toString()), Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onFailure(Call<APIResponseModel> call, Throwable t) {
                Log.v("Api onFailure", String.format("%s", t.toString()));
                Toast.makeText(getApplication(),
                        String.format("Data Failed %s", t.toString()), Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }
}