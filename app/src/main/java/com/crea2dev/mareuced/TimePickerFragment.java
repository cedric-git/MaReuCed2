package com.crea2dev.mareuced;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link TimePickerFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link TimePickerFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class TimePickerFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

//    @Override ?????????
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
    }


}
