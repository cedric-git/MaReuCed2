package com.crea2dev.mareuced;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Service.Injection;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddMeetingFragment extends DialogFragment implements OnClickListener {

int hourChossen;
    EditText chooseTime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;


    @BindView(R.id.newMeeting_Name)
    TextInputLayout mNewMeeting_Name;
    @BindView(R.id.newMeeting_Place)
    TextInputLayout mNewMeeting_Place;
    @BindView(R.id.newMeeting_Time)
    TextInputLayout mNewMeeting_Time;
//
//    @BindView(R.id.enter_time)
//    Text mNewMeeting_Time2;

    @BindView(R.id.newMeeting_Participants)
    TextInputLayout mNewMeeting_Participants;

    @BindView(R.id.button_add_meeting)
    Button mValidateButton;

//    @BindView(R.id.button_add_meeting)
//    Button mV;

//    @BindView(R.id.button_add_meeting) Button Button_add_meeting;

    private EditText mNameEdit;
    private EditText mParticipantsEdit;
    private NumberPicker mMinutesPicker;
    private NumberPicker mHourPicker;
//    private Button mValidateButton;

    public AddMeetingFragment() {
    }
    private MeetingModel mMeeting;


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


        mValidateButton.setOnClickListener(
                new OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Meeting Saved", Toast.LENGTH_SHORT).show();

//                        gerer les nulls
        mMeeting = new MeetingModel(mNewMeeting_Name.getEditText().getText().toString(), mNewMeeting_Time.getEditText().getText().toString(), mNewMeeting_Place.getEditText().getText().toString(), mNewMeeting_Participants.getEditText().getText().toString());

                       Injection.getMeetingApiService().addMeeting(mMeeting);

                       getActivity().finish();

                    }
                    });



        //================================  TIME PICKER

        mNewMeeting_Time.getEditText().setOnClickListener(new OnClickListener() {

//                public Button mValidateButton;

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

//todo : enregistrer l'heure dans une variable (PUBLIC ?) +++++++++++++++++++++++++

                        hourChossen = hourOfDay;

                        Toast.makeText(getContext(), Integer.toString(hourChossen), Toast.LENGTH_LONG).show();
//                        String Heure_String;
//                        mNewMeeting_Time_To_Record = hourOfDay;
//                        Heure_String = toString(Calendar.HOUR_OF_DAY);
//                        String Heure_String = new String(calendar.get(Calendar.HOUR_OF_DAY));
//                        String Heure_string = mNewMeeting_Time_To_Record.getText().toString();
                    }

                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }


 //================================ SAVE MEETING


//
//todo : recuperer tous les champs et les additionner a la liste de MeetingModel : meetings



//================================ ADD PARTICIPANTS
//todo : a chaque appuis sur ADD PARTICIPANT, l'email renseigne est ajoute a une arraylist
//       et displayed dans la TextView au dessous (entraine son update a chaque click ?)


            ArrayList participants = new ArrayList();
//            ou
//            String PanticipantsList [];

// OnClickListener sur bouton ADD PARTICIPANT

//                  mNewMeeting_Time.getEditText().setOnClickListener(new OnClickListener() {
//
//            }
//            mNewMeeting_Participants.getEditText().setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);


String newEmail;

            public void setNewEmail(String newEmail) {
                this.newEmail = newEmail;
            }
            public String getNewEmail() {
                return newEmail;
            }

//            participants.add(newEmail);

//            public ArrayList getParticipants() {
//                return participants;
//            }

//                      mValidateButton.setOnClickListener(v -> finish());


//@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//    super.onViewCreated(view, savedInstanceState);

    mNameEdit = view.findViewById(R.id.nameInput);



    //Set pickers


//    mMeetingViewModel = ViewModelProviders.of(this).get(MeetingViewModel.class);

    mValidateButton.setOnClickListener(onClickListener);
}

OnClickListener onClickListener = new OnClickListener() {
    @Override
    public void onClick(View view) {

        String mName = mNameEdit.getText().toString().trim();

//        if (!mName.equals("") && !mDate.equals("") && !mRoom.equals("") && !mParticipants.equals("")){

//        mMeeting = new MeetingModel(mName, mNewMeeting_Time, mNewMeeting_Place, mNewMeeting_Participants);
//            mMeetingViewModel.addMeeting(mMeeting);

            dismiss();
//        } else {
            Toast.makeText(getContext(), "Field missing", Toast.LENGTH_SHORT).show();
        }
//    }
};

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        });

//        Toast toast = Toast.makeText(getApplicationContext(), "mon message", Toast.LENGTH_LONG).show();

        return view;
//   todo OnTimeSetListener
//    set listener
//    instancier class du picker fragment
//     utiliser le fragment manager

    }

    @Override
    public void onClick(View v) {

//        mValidateButton.setOnClickListener(new View.OnClickListener()){
//        };

    }
}

