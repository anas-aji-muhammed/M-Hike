package com.uog_mobile_application_development.m_hike.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.*;

import androidx.annotation.Nullable;

import com.uog_mobile_application_development.m_hike.models.HikeDataModel;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName = "mHikeMasterDB";
    private SQLiteDatabase database;

    final String hikeDetailsTableName = "hike_details";

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


        final String CreateTable =     "CREATE TABLE " + hikeDetailsTableName + " (" +
                hikeId   + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                hikeLocation + " TEXT," +
                hikeDate + " TEXT," +
                parkingAvailability + " Bool," +
                hikeLength + " Text," +
                hikeDifficulty + " Text," +
                hikeDescription + " Text," +
                hikeName + " Text," +
                hikeObservations + " INTEGER" + ")";

        try{
            sqLiteDatabase.execSQL(CreateTable);

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
                return database.insertOrThrow(hikeDetailsTableName, null, rowValues);

            } catch (android.database.SQLException e) {
                Log.v("insertHike Exception", e.toString());
                return 0;
            }
        }
        else {
            try {
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

        ArrayList<HikeDataModel> hikeDataArrayList = new ArrayList<HikeDataModel>();

        results.moveToFirst();
        while (!results.isAfterLast()) {
            HikeDataModel hikeData = new HikeDataModel(
                    results.getString(0),
                    results.getString(1),
                    results.getString(2),
                    results.getString(3),
                    results.getString(4),
                    results.getString(5),
                    results.getString(6),
                    results.getInt(7)

            );
            hikeDataArrayList.add(hikeData);

            results.moveToNext();
        }

        return hikeDataArrayList;
    }

}
