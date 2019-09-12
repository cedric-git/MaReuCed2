package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.ui.main.MainFragment;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;


public class MainActivity_MeetingList extends AppCompatActivity {

    FloatingActionButton fab;

    private MeetingApiService mApiService;
    private List<MeetingModel> mMeetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        fab = (FloatingActionButton) findViewById(R.id.FABaddMeeting);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity_MeetingList.this,AddMeetingFragment.class));
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_fragment);

        TextView textView = findViewById(R.id.message);

        mApiService = Injection.getMeetingApiService();
        mMeetings = mApiService.getMeetings();

        String message = Arrays.toString(mMeetings.toArray());
//        TextView.setText(message);
    }
}
