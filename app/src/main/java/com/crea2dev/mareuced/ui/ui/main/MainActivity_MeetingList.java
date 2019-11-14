package com.crea2dev.mareuced.ui.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.crea2dev.mareuced.Events.FilterMeetingByDateEvent;
import com.crea2dev.mareuced.Events.FilterMeetingByPlaceEvent;
import com.crea2dev.mareuced.Events.SortMeetingByDateEvent;
import com.crea2dev.mareuced.Events.SortMeetingByNameEvent;
import com.crea2dev.mareuced.Events.SortMeetingByPlaceEvent;
import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity_MeetingList extends AppCompatActivity {

    public static MeetingApiService mApiService;     //  <<<<< declare object based on ApiService  << private
    private List<MeetingModel> mMeetings;   //  <<<<< declare object based on meeting list
    String itemName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // display (replace) MainFragment inside container  //  <<<<<<<<<<<<<<<
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

        mApiService = Injection.getMeetingApiService();
        mMeetings = mApiService.getMeetings();
    }


    // Create Menu //  <<<<<<<<<
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
    }

    // Register to eventbus to handle item clicks onStart <<<<<
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        mMeetings = mApiService.getMeetings();
    }

    //UnRegister on Stop <<<<<
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // Delete meeting through event <<<<<<<<<<<<<
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        mApiService.deleteMeeting(event.meeting);
    }

    //  ---------------------------------------------------------------------------- EXTRA
    // Case to sort meeting by name/hour/place  //  *****EXTRA

    public boolean onOptionsItemSelected(MenuItem item) {  //  *****EXTRA

        switch (item.getItemId()){  //  *****EXTRA
            case R.id.menu_sort_by_name:
                mApiService = Injection.getMeetingApiService();  //  *****EXTRA
                mMeetings = mApiService.getMeetings();  //  *****EXTRA
                EventBus.getDefault().post(new SortMeetingByNameEvent(mMeetings));  //  *****EXTRA

                return true;  //  *****EXTRA

            case R.id.menu_sort_by_date:  //  *****EXTRA
                mApiService = Injection.getMeetingApiService();  //  *****EXTRA
                mMeetings = mApiService.getMeetings();  //  *****EXTRA
                EventBus.getDefault().post(new SortMeetingByDateEvent(mMeetings));  //  *****EXTRA

                return true;  //  *****EXTRA

            case R.id.menu_sort_by_place:  //  *****EXTRA
                mApiService = Injection.getMeetingApiService();  //  *****EXTRA
                mMeetings = mApiService.getMeetings();  //  *****EXTRA
                EventBus.getDefault().post(new SortMeetingByPlaceEvent(mMeetings));  //  *****EXTRA

    //  ------------------------------------------------------------------------------
    // Case to filter meeting by name/hour/place

            case R.id.menu_filter_by_time:
                configureAndShowAlertDate();

                return true;

            case R.id.menu_filter_by_place:
                configureAndShowAlertDialog();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //  =====================================================================FILTER BY PLACE

    private void configureAndShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.filter_list_dialog, null);

        final Spinner spinner = view.findViewById(R.id.spinner_choice);

        Set<String> set = new HashSet<>();
        for (MeetingModel r : mMeetings){
            set.add(r.getPlace());
        }
        List<String> arrayList = new ArrayList<>(set);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemName = spinner.getSelectedItem().toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        builder.setTitle("Select the place")
                .setView(view)
                .setPositiveButton("Filtrer",
                        (dialog, which) -> EventBus.getDefault().post(new FilterMeetingByPlaceEvent(itemName)))
                .setNegativeButton("Annuler",
                        (dialog, which) -> { });

        builder.create().show();
    }

//  =====================================================================FILTER BY DATE

    private void configureAndShowAlertDate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        @SuppressLint("InflateParams") View view = LayoutInflater.from(this).inflate(R.layout.filter_list_dialog, null);

        final Spinner spinner = view.findViewById(R.id.spinner_choice);
        Set<String> set = new HashSet<>();
        for (MeetingModel r : mMeetings){
            set.add(r.getHour());
        }
        List<String> arrayList = new ArrayList<>(set);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemName = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        builder.setTitle("Select the time")
                .setView(view)
                .setPositiveButton("Filter",
                        (dialog, which) -> EventBus.getDefault().post(new FilterMeetingByDateEvent(itemName)))
                .setNegativeButton("Cancel",
                        (dialog, which) -> { });

        builder.create().show();
    }
}