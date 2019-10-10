package com.crea2dev.mareuced;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.utils.TimeConverting;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingFragment extends DialogFragment implements OnClickListener {

    int hourChoosen;
    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;


    @BindView(R.id.newMeeting_Name) TextInputLayout mNewMeeting_Name;
    @BindView(R.id.newMeeting_Place) TextInputLayout mNewMeeting_Place;
    @BindView(R.id.newMeeting_Time) TextInputLayout mNewMeeting_Time;
    @BindView(R.id.newMeeting_Participants) TextInputLayout mNewMeeting_Participants;
    @BindView(R.id.button_add_meeting) Button mValidateButton;
    @BindView(R.id.button_add_participant) Button mAddParticipantButton;
    @BindView(R.id.display_participant_list_text_view) TextView mDisplayParticipantList;


//    private Button mValidateButton;

    public AddMeetingFragment() {
    }
    private MeetingModel mMeeting;

    //create object of listview
//    ListView listView=(ListView)findViewById(R.id.listview);
    @BindView(R.id.listview) ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_meeting, container, false);
        ButterKnife.bind(this, view);

        //================================ SAVE MEETING


//
//todo : recuperer tous les champs et les additionner a la liste de MeetingModel : meetings

        mValidateButton.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(View v) {

//                        gerer le format d'heure

                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                        String timeField = mNewMeeting_Time.getEditText().getText().toString();

//                        try {
//                            Date formatedTime = sdf.parse(timeField);
//                            System.out.println(formatedTime);
//                        } catch (ParseException e) {
//                            Toast.makeText(getContext(), "Incorrect date format", Toast.LENGTH_SHORT).show();
//                            e.printStackTrace();
//                        }


//                        empecher les champs vides
                        if (!mNewMeeting_Name.getEditText().getText().toString().equals("")
                                 && !mNewMeeting_Time.getEditText().getText().toString().equals("")
                                 && !mDisplayParticipantList.getText().toString().equals("")
                                 && !mNewMeeting_Place.getEditText().getText().toString().equals(""))

                        {
//                            mMeeting = new MeetingModel(
//                                    mNewMeeting_Name.getEditText().getText().toString(),
//                                    mNewMeeting_Time.getEditText().getText().toString(),
////                                    formatedTime : peut remplacer la ligne precedente
//                                    mNewMeeting_Place.getEditText().getText().toString(),
//                                    mDisplayParticipantList.getText().toString()
////                                    participants : peut remplacer la ligne precedente
//
//                            );


                            Injection.getMeetingApiService().addMeeting(mMeeting);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getContext(), "Field missing", Toast.LENGTH_SHORT).show();

                        }
                    }
                    });

        //================================ ADD PARTICIPANTS
//todo : a chaque appuis sur ADD PARTICIPANT, l'email renseigne est ajoute a une arraylist
//       et displayed dans la TextView au dessous (entraine son update a chaque click ?)


//create ArrayList of String
        final ArrayList participants = new ArrayList();

//        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, participants);

//assign adapter to listview
//        listView.setAdapter(arrayAdapter);


        mAddParticipantButton.setOnClickListener(
                new OnClickListener() {
                    String newParticipant = "";
                    String newLine = System.getProperty("line.separator");
                    @Override
                    public void onClick(View v) {

                        String inputParticipant = mNewMeeting_Participants.getEditText().getText().toString();
                        newParticipant = newParticipant + inputParticipant+" ,"+newLine;

                        participants.add(newParticipant);
                        System.out.println(participants);

                        mDisplayParticipantList.setText(newParticipant);
//                        mDisplayParticipantList.setText(participants);

//                        Toast.makeText(getContext(), inputParticipant, Toast.LENGTH_SHORT).show();
                        mNewMeeting_Participants.getEditText().getText().clear();

                    }
                });


        //================================  TIME PICKER

        mNewMeeting_Time.getEditText().setOnClickListener(new OnClickListener() {
//                    mTime_Zone.getEditText().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        mNewMeeting_Time.getEditText().setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                        hourChoosen = hourOfDay;
                        Toast.makeText(getContext(), Integer.toString(hourChoosen), Toast.LENGTH_LONG).show();
//                        String Heure_String;
//                        mNewMeeting_Time_To_Record = hourOfDay;
//                        Heure_String = toString(Calendar.HOUR_OF_DAY);
//                        String Heure_String = new String(calendar.get(Calendar.HOUR_OF_DAY));
//                        String Heure_string = mNewMeeting_Time_To_Record.getText().toString();
                    }

                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }



//todo : a chaque appuis sur ADD PARTICIPANT, l'email renseigne est ajoute a une arraylist
//       et displayed dans la TextView au dessous (entraine son update a chaque click ?)


String newEmail;

            public void setNewEmail(String newEmail) {
                this.newEmail = newEmail;
            }
            public String getNewEmail() {
                return newEmail;
            }


OnClickListener onClickListener = new OnClickListener() {
    @Override
    public void onClick(View view) {

//        String mName = mNameEdit.getText().toString().trim();

            dismiss();
//        } else {
            Toast.makeText(getContext(), "Field missing", Toast.LENGTH_SHORT).show();
        }
//    }
};


        });
        return view;
    }

    @Override
    public void onClick(View v) {
    }
}

