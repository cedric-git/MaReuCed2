package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.MenuInflater;
import android.widget.Toast;
import com.crea2dev.mareuced.Views.MeetingViewHolder;
import com.crea2dev.mareuced.utils.SortMeetings;
import java.util.Collections;

import android.view.Menu;
import android.os.Bundle;
import android.widget.TextView;
import android.view.MenuItem;

import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.ui.main.MainFragment;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.ui.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.List;

import static java.security.AccessController.getContext;


public class MainActivity_MeetingList extends AppCompatActivity {

//    private MeetingsApiService mMeetingsApiService;
////    mMeetingsApiService = Service.getNewInstanceMeetingsApiService();

    public MainViewModel mMeetingViewModel;

    FloatingActionButton fab;

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

        TextView textView = findViewById(R.id.message);

        mApiService = Injection.getMeetingApiService();
        mMeetings = mApiService.getMeetings();

        String message = Arrays.toString(mMeetings.toArray());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Register to eventbus to handle item clicks
        EventBus.getDefault().register(this);

        mMeetings = mApiService.getMeetings();


    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

//Manage Delete Click
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        mApiService.deleteMeeting(event.meeting);
    }

//    MeetingViewHolder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            EventBus.getDefault().post(new DeleteMeetingEvent(MeetingModel));
//        }
//    };


///////////////////REAGIT
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sort_by_name:
//                Injection.getMeetingApiService().getMeetings().setSortingOrder(SortMeetings.SortMethods.NAME_ORDER); // pas util
                SortMeetings.nameOrder(Injection.getMeetingApiService().getMeetings());
//appel evenement declanchant methode dans main fragment

//                System.out.println("NAME - NAME - NAME - NAME - NAME - NAME - NAME - NAME - NAME - ");
                return true;
            case R.id.menu_sort_by_date:
                System.out.println("DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - ");
//                mMeetingViewModel.setSortingOrder(SortMeetings.SortMethods.DATE_ORDER);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
