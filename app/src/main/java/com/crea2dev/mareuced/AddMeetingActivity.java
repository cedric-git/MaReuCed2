package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

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


//        chooseTime = findViewById(R.id.newMeeting_Time);
//        chooseTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                calendar[0] = Calendar.getInstance();
////                currentHour[0] = calendar[0].get(Calendar.HOUR_OF_DAY);
////                currentMinute[0] = calendar[0].get(Calendar.MINUTE);
//
//                timePickerDialog[0] = new TimePickerDialog(AddMeetingActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
////                            if (hourOfDay >= 12) {
////                                amPm = "PM";
////                            } else {
////                                amPm = "AM";
////                            }
//
//
//                        chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
//
//
//                    }
//                }, currentHour[0], currentMinute[0], false);
//
//                timePickerDialog[0].show();
//            }
//            });

        }
    }

