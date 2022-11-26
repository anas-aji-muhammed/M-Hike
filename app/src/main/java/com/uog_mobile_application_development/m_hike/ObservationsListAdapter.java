package com.uog_mobile_application_development.m_hike;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.uog_mobile_application_development.m_hike.models.ObservationDataModel;
import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.ArrayList;

public class ObservationsListAdapter implements ListAdapter {
    private final ArrayList<ObservationDataModel> observationDataList;
    Context context;
    DatabaseHelper databaseHelper;

    public ObservationsListAdapter(ArrayList<ObservationDataModel> observationData, Context context) {
        this.observationDataList = observationData;
        this.context = context;

        databaseHelper = new DatabaseHelper(context);

    }


    //    public ObservationsListAdapter(@NonNull Context context, String[] observationData, String[] subTitle) {
//        super(context, 0);
//        this.observationData = observationData;
//        this.subTitle = subTitle;
//        this.context = context;
//
//    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return observationDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("Get View Called", "");
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.observations_list_item, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            TextView tittle=convertView.findViewById(R.id.observationListItemTitle);
            TextView subTitle=convertView.findViewById(R.id.observationListItemSubtitle);
            TextView comment=convertView.findViewById(R.id.observationListItemComment);
            Button deleteObservationButton=convertView.findViewById(R.id.deleteObservationButton);
            tittle.setText(observationDataList.get(position).getObservation());
            subTitle.setText(observationDataList.get(position).getObservationDateTime());
            comment.setText(observationDataList.get(position).getObservationComment());
            deleteObservationButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("LongLogTag")
                @Override
                public void onClick(View v) {
                    Log.v("deleteObservationButton onClick id", String.format("%s", observationDataList.get(position).getObservationId()));
                    databaseHelper.deleteObservation(String.format("%s",observationDataList.get(position).getObservationId()));
                    Toast.makeText(context, String.format("%s Hike Deleted", observationDataList.get(position).getObservation()),Toast.LENGTH_LONG).show();

                }
            });
        }
        return convertView;

    }

    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return observationDataList.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    ;

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }


}
