package com.uog_mobile_application_development.m_hike.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.*;

import androidx.annotation.Nullable;

import com.uog_mobile_application_development.m_hike.models.HikeDataModel;
import com.uog_mobile_application_development.m_hike.models.ObservationDataModel;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName = "mHikeMasterDB";
    private SQLiteDatabase database;

    final String hikeDetailsTableName = "hike_details";
    final String observationsTableName = "observations_table";

//    final String hikeName = "hike_name";
//    final String hikeId = "hike_id";
//    final String hikeLocation = "hike_location";
//    final String hikeDate = "hike_date";
//    final String parkingAvailability = "parking_availability";
//    final String hikeLength = "hike_length";
//    final String hikeDifficulty = "hike_difficulty";
//    final String hikeDescription = "hike_description";
//    final String hikeObservations = "hike_observations";



    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
        try{
            database = getWritableDatabase();
            Log.v("getWritableDatabase", database.getPath());


        }
        catch (RuntimeException e){
            Log.v("getWritableDatabase()", e.toString());

        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createHikeDetailsTable(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        database.execSQL("Drop Table if EXISTS " + databaseName);
        Log.v(this.getClass().getName(), databaseName+ " upgraded to version " + i1 );
        onCreate(database);

    }

    public void createHikeDetailsTable(SQLiteDatabase sqLiteDatabase){
        database = sqLiteDatabase;


        final String CreateHikeTable =     "CREATE TABLE " + hikeDetailsTableName + " (" +
                hikeId   + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                hikeLocation + " TEXT," +
                hikeDate + " TEXT," +
                parkingAvailability + " Bool," +
                hikeLength + " Text," +
                hikeDifficulty + " Text," +
                hikeDescription + " Text," +
                hikeName + " Text," +
                hikeObservations + " INTEGER" + ")";

        final String CreateObservationsTable =     "CREATE TABLE " + observationsTableName + " (" +
                observationId   + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                hikeId + " INTEGER," +
                hikeObservations + " TEXT," +
                timeOfObservations + " TEXT," +
                observationComments + " TEXT" +
                 ")";

        try{
            sqLiteDatabase.execSQL(CreateHikeTable);
            sqLiteDatabase.execSQL(CreateObservationsTable);

        }
        catch ( android.database.SQLException e){
            Log.v("Create Table Exception", e.toString());
        }
    }

    public long insertHike(HashMap<String, String> data, Boolean isForUpdate) {
        ContentValues rowValues = new ContentValues();
        for (String i : data.keySet()) {
            System.out.println(i);
            rowValues.put(i, data.get(i)
            );

        }

        if (!isForUpdate) {

            try {
                long insertionResult =  database.insertOrThrow(hikeDetailsTableName, null, rowValues);
                Log.v("insertionResult", String.format("%s",insertionResult));

                return insertionResult;

            } catch (android.database.SQLException e) {
                Log.v("insertHike Exception", e.toString());
                return 0;
            }
        }
        else {
            try {
                Log.v("database.update",String.format("Hike Id -- %s",data.get("hike_id")));

                return database.update(hikeDetailsTableName, rowValues, "hike_id = ?", new String[]{data.get("hike_id")});

            } catch (android.database.SQLException e) {
                Log.v("insertHike Exception", e.toString());
                return 0;
            }
        }
    }

    public ArrayList<HikeDataModel> getAllHikeDetails(){
        Cursor results = database.query(hikeDetailsTableName, new String[] {
                hikeName, hikeLocation, hikeDate,parkingAvailability, hikeLength, hikeDifficulty, hikeDescription,  hikeId},
                null, null, null, null, hikeName);
//        String query = String.format("SELECT * FROM %s", hikeDetailsTableName);
//        Cursor results = database.rawQuery(query, null);

        ArrayList<HikeDataModel> hikeDataArrayList = new ArrayList<>();

        results.moveToFirst();
        while (!results.isAfterLast()) {
            Log.v("database results",String.format("%s", (Object) results.getColumnNames()));

            HikeDataModel hikeData = new HikeDataModel(
                    results.getString(0),

                    results.getString(1),
                    results.getString(2),
                    results.getString(3),
                    results.getString(4),
                    results.getString(5),
                    results.getString(6)

                    );
            hikeData.setHikeId(results.getInt(7));
            hikeDataArrayList.add(hikeData);

            results.moveToNext();
        }

        results.close();

        return hikeDataArrayList;
    }

    public ArrayList<HikeDataModel> searchHikes(String searchText){
                String query = String.format("SELECT * FROM %s WHERE name LIKE %s", hikeDetailsTableName, searchText);

        Cursor results = database.rawQuery
                ("select * from " + hikeDetailsTableName + " where "
                        + hikeName + " like ?", new String[] { "%" + searchText + "%" });

//        Cursor results = database.rawQuery(query, null);

        ArrayList<HikeDataModel> hikeDataArrayList = new ArrayList<>();

        results.moveToFirst();
        while (!results.isAfterLast()) {
            Log.v("database results",String.format("%s", (Object) results.getColumnNames()));
            Log.v("results.getString(1)",String.format("%s", results.getString(1)));
            Log.v("results.getString(2)",String.format("%s", results.getString(2)));
            Log.v("results.getString(3)",String.format("%s", results.getString(3)));
            Log.v("results.getString(4)",String.format("%s", results.getString(4)));
            Log.v("results.getString(5)",String.format("%s", results.getString(5)));
            Log.v("results.getString(6)",String.format("%s", results.getString(6)));
            Log.v("results.getString(7)",String.format("%s", results.getString(7)));
            Log.v("results.getString(0)",String.format("%s", results.getString(0)));

            HikeDataModel hikeData = new HikeDataModel(
                    results.getString(7),

                    results.getString(1),
                    results.getString(2),
                    results.getString(3),
                    results.getString(4),
                    results.getString(5),
                    results.getString(6)

            );
            hikeData.setHikeId(results.getInt(0));
            hikeDataArrayList.add(hikeData);

            results.moveToNext();
        }

        results.close();

        return hikeDataArrayList;
    }


    public int deleteHike(String id){
        return database.delete(hikeDetailsTableName, "hike_id=?", new String[]{id});
    }
    public int deleteAllHikes(){
        try{
            database.execSQL("delete from "+ hikeDetailsTableName);
            return 1;
        }
        catch (SQLException e){
            Log.v("DeleteHikes Exception", String.format("%s",e.toString()));
            return 0;
        }
    }

    // Observations Db operations

    public long insertObservations(HashMap<String, String> data) {
        Log.v("Insert Observation","Called");
        ContentValues rowValues = new ContentValues();
        for (String i : data.keySet()) {
            System.out.println(i);
            rowValues.put(i, data.get(i)
            );

        }
            try {
                long insertionResult =  database.insertOrThrow(observationsTableName, null, rowValues);
                Log.v("insertionResult", String.format("%s",insertionResult));

                return insertionResult;

            } catch (android.database.SQLException e) {
                Log.v("insertHike Exception", e.toString());
                return 0;
            }

    }

    public int deleteObservation(String id){
        return database.delete(observationsTableName, "observation_id=?", new String[]{id});
    }

    public ArrayList<ObservationDataModel> getAllObservations(){
        Cursor results = database.query(observationsTableName, new String[] {
                        observationId, hikeObservations, timeOfObservations, observationComments},
                null, null, null, null, hikeObservations);
//        String query = String.format("SELECT * FROM %s", hikeDetailsTableName);
//        Cursor results = database.rawQuery(query, null);

        ArrayList<ObservationDataModel> observationDataArrayList = new ArrayList<>();

        results.moveToFirst();
        while (!results.isAfterLast()) {
            Log.v("database results",String.format("%s", (Object) results.getColumnNames()));

            ObservationDataModel observationData = new ObservationDataModel(
                    results.getString(1),
                    results.getString(2),
                    results.getString(3)

            );
            observationData.setObservationId(results.getInt(0));
            observationDataArrayList.add(observationData);

            results.moveToNext();
        }

        results.close();

        return observationDataArrayList;
    }


}
