package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddMeetingActivity extends AppCompatActivity {

    private AddMeetingFragment addMeetingFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        addMeetingFragment=new AddMeetingFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.AddMeetingLayout, addMeetingFragment);
        transaction.commit();

        final EditText chooseTime;
        final TimePickerDialog[] timePickerDialog = new TimePickerDialog[1];
        final Calendar[] calendar = new Calendar[1];
        final int[] currentHour = new int[1];
        final int[] currentMinute = new int[1];
        String amPm;

        }

//    Context context = getApplicationContext();
//    CharSequence text = "Hello toast!";
//    int duration = Toast.LENGTH_SHORT;
//
//    Toast toast = Toast.makeText(context, text, duration);
//                toast.show();

    }

