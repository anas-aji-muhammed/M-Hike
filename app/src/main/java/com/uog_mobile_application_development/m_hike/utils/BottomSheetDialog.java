package com.uog_mobile_application_development.m_hike.utils;

import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeDate;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.hikeName;
import static com.uog_mobile_application_development.m_hike.utils.database.DbConstants.*;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.uog_mobile_application_development.m_hike.R;
import com.uog_mobile_application_development.m_hike.utils.database.DatabaseHelper;

import java.util.HashMap;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.add_observation_bottom_sheet,
                container, false);
        databaseHelper = new DatabaseHelper(getContext());

        Button saveObservationButton = v.findViewById(R.id.saveObservation);
        EditText observationEditTextField = v.findViewById(R.id.editTextObservationName);
        EditText observationDateEditTextField = v.findViewById(R.id.editTextObservationDate);
        EditText observationCommentEditTextField = v.findViewById(R.id.editTextObservationComment);

        saveObservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put(hikeObservations, observationEditTextField.getText().toString());
                data.put(timeOfObservations, observationDateEditTextField.getText().toString());
                data.put(observationComments, observationCommentEditTextField.getText().toString());
                Toast.makeText(getActivity(),
                        "Observation Saved", Toast.LENGTH_SHORT)
                        .show();
                long result = databaseHelper.insertObservations(data);
                Log.v("Observation Save",  "Success" );
                Log.v("result", String.format("%s",result) );
                dismiss();
            }
        });

        return v;
    }
}
