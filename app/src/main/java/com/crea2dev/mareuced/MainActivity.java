package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.ui.main.MainFragment;
import com.crea2dev.mareuced.Model.MeetingModel;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MeetingApiService mApiService;
    private List<MeetingModel> mMeetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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
