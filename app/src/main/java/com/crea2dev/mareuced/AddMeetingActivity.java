package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

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
    }
}