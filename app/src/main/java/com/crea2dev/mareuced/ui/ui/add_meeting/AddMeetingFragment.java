package com.crea2dev.mareuced.ui.ui.add_meeting;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.Service.Injection;
import com.google.android.material.textfield.TextInputLayout;


import butterknife.BindView;
import butterknife.ButterKnife;


public class AddMeetingFragment extends DialogFragment implements OnClickListener {

    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    String amPm;

    @BindView(R.id.newMeeting_Name)
    TextInputLayout mNewMeeting_Name;
    @BindView(R.id.newMeeting_Place)
    TextInputLayout mNewMeeting_Place;
    @BindView(R.id.newMeeting_Time)
    TextInputLayout mNewMeeting_Time;
    @BindView(R.id.newMeeting_Participants)
    TextInputLayout mNewMeeting_Participants;
    @BindView(R.id.button_add_meeting)
    Button mValidateButton;
    @BindView(R.id.button_add_participant)
    Button mAddParticipantButton;
    @BindView(R.id.display_participant_list_text_view)
    TextView mDisplayParticipantListTesxtView;

//    public AddMeetingFragment() {// <<<<<<<<<<<<< ?
//    }

    // 3 - build <<<<<<<<<<<<<<<<<<<<<<
    public static AddMeetingFragment newInstance() {
        return new AddMeetingFragment();
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

        initPicker();
        addParticipant();
        saveMeeting();

        return view;
    }

    @Override
    public void onClick(View v) {
    }

 //================================ PICKER
    private void initPicker() {
        mNewMeeting_Time.getEditText().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        mNewMeeting_Time.getEditText().setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }

                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

    }

//================================ ADD PARTICIPANTS
    private void addParticipant() {
// on click on "add participant" button ...
        mAddParticipantButton.setOnClickListener(
                new OnClickListener() {
                    String newParticipant = "";

                    @Override
                    public void onClick(View v) {
                        String inputParticipant = mNewMeeting_Participants.getEditText().getText().toString();
                        newParticipant = newParticipant + inputParticipant + "\n"; // add participant to string + new line caracter


                        mDisplayParticipantListTesxtView.setText(newParticipant);  // display participants added in textview preview below
                        mNewMeeting_Participants.getEditText().getText().clear(); // clear the field
                    }
                });
    }

//================================ SAVE MEETING
    private void saveMeeting() {

        mValidateButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {   //  avoid missing fields
                        if (!mNewMeeting_Name.getEditText().getText().toString().equals("")
                                && !mNewMeeting_Time.getEditText().getText().toString().equals("")
                                && !mDisplayParticipantListTesxtView.getText().toString().equals("Participant's list")
                                && !mNewMeeting_Place.getEditText().getText().toString().equals("")) {

                            mMeeting = new MeetingModel(    //  get meeting data (from filled fields)
                                    mNewMeeting_Name.getEditText().getText().toString(),
                                    mNewMeeting_Time.getEditText().getText().toString(),
                                    mNewMeeting_Place.getEditText().getText().toString(),
                                    mDisplayParticipantListTesxtView.getText().toString()
                            );

                            Injection.getMeetingApiService().addMeeting(mMeeting);  // add meeeting
                            getActivity().finish();
                        } else {

                            // Missing field(s) toast
                            Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<big><b>WARNING : Field(s) missing !</b>"), Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 300);
                            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                            toastMessage.setTextColor(Color.RED);
                            toast.show();

                        }
                    }
                });
    }

}