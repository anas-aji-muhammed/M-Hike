package com.uog_mobile_application_development.m_hike.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName = "mHikeMasterDB";
    private SQLiteDatabase database;



    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createHikeDetailsTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        database.execSQL("Drop Table if EXISTS " + databaseName);
        Log.v(this.getClass().getName(), databaseName+ " upgraded to version " + i1 );
        onCreate(database);

    }

    public void createHikeDetailsTable(){
        final String hikeDetailsTableName = "hike_details";

        final String hikeName = "hike_name";
        final String hikeId = "hike_id";
        final String hikeLocation = "hike_location";
        final String hikeDate = "hike_date";
        final String parkingAvailability = "parking_availability";
        final String hikeLength = "hike_length";
        final String hikeDifficulty = "hike_difficulty";
        final String hikeDescription = "hike_description";
        final String hikeObservations = "hike_observations";

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

        database.execSQL(CreateTable);
    }

//    public long insertData( String tableName, HashMap<String, String> data){
//        ContentValues rowValues = new ContentValues();
//        for (String i : data.keySet()) {
//            System.out.println(i);
//            rowValues.put(i, data.get(i)
//            );
//
//        }
//        String[] projection = {
//                "hike_name",
//               "hike_location"
//        };
////        Cursor cursor = database.query("hike_details", projection, new String[]{""});
//);
//        Log.v(this.getClass().getName(), "onViewHike data " + cursor.moveToFirst() );
//        cursor.close();
//
//        return database.insertOrThrow(tableName, null, rowValues);
//    }

}
