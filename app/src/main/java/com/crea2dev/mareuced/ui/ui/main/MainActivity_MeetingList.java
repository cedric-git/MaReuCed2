package com.crea2dev.mareuced.ui.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.crea2dev.mareuced.Events.SortMeetingByDateEvent;
import com.crea2dev.mareuced.Events.SortMeetingByNameEvent;
import com.crea2dev.mareuced.Events.SortMeetingByPlaceEvent;
import com.crea2dev.mareuced.Model.RoomItemSpinner;
import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import com.crea2dev.mareuced.utils.RoomItemSpinnerUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity_MeetingList extends AppCompatActivity {

    public static MeetingApiService mApiService;     //  <<<<< declare object based on ApiService  << private
    private List<MeetingModel> mMeetings;   //  <<<<< declare object based on meeting list

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

    private void initListAdapter(List<MeetingModel> Meetings) { //  reunions
        mRecyclerView.setAdapter(new MeetingRecycleViewAdapter(Meetings));//  reunions
        Log.i("meeting Fragment", String.valueOf(Meetings.size()));//  reunions
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
    // ------------------------------ EVENTS ----------------------------------------

    // Delete meeting through event <<<<<<<<<<<<<
    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        mApiService.deleteMeeting(event.meeting);
    }

    //  ------------------------------------------------------------------------------
    // Case to sort meeting by name/hour/place
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sort_by_name:

                mApiService = Injection.getMeetingApiService();
                mMeetings = mApiService.getMeetings();
                EventBus.getDefault().post(new SortMeetingByNameEvent(mMeetings));

                return true;

            case R.id.menu_sort_by_date:
                mApiService = Injection.getMeetingApiService();
                mMeetings = mApiService.getMeetings();
                EventBus.getDefault().post(new SortMeetingByDateEvent(mMeetings));

                return true;

            case R.id.menu_sort_by_place:
                mApiService = Injection.getMeetingApiService();
                mMeetings = mApiService.getMeetings();
                EventBus.getDefault().post(new SortMeetingByPlaceEvent(mMeetings));

    //  ------------------------------------------------------------------------------
    // Case to filter meeting by name/hour/place

            case R.id.menu_filter_by_time:

//                startActivity(new Intent(this, AddMeetingActivity.class));    // fonctionne en 1 ligne

//                configureAndShowAlertDate();

                return true;

            case R.id.menu_filter_by_place:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, FilterByPlaceDialogFragment.newInstance())
                        .commitNow();

//                configureAndShowAlertDialog();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //  =====================================================================FILTER PLACE
//
    private void configureAndShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(this()).inflate(R.layout.filter_list_dialog, null);

        final Spinner spinner = view.findViewById(R.id.spinner_choice); //  <<<< Oter 'final'
        RoomItemSpinnerUtil.initRoomSpinner(view, spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RoomItemSpinner roomItemSpinner = (RoomItemSpinner) spinner.getSelectedItem();
                itemName = roomItemSpinner.getRoomName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        builder.setTitle("Sélectionnez une valeur")
                .setView(view)
                .setPositiveButton("Filtrer",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ArrayList<MeetingModel> meeting = MainActivity_MeetingList// ReunionListActivity
                                        .mApiService.filter(itemName); //       mReunionListService
                                initListAdapter(meeting);
                            }
                        })
                .setNegativeButton("Annuler",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

        builder.create().show();
    }

//  =====================================================================FILTER DATE

    private void configureAndShowAlertDate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(this).inflate(R.layout.filter_list_dialog, null);

        final Spinner spinner = view.findViewById(R.id.spinner_choice);
        List<String> arrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (MeetingModel r : mMeetings){
            set.add(r.getHour());
        }
        for (String s : set){
            arrayList.add(s);
        }

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

        builder.setTitle("Sélectionnez une valeur")
                .setView(view)
                .setPositiveButton("Filtrer",
                        (dialog, which) -> {
                            ArrayList<MeetingModel> reunion = MainActivity_MeetingList// ReunionListActivity
                                    .mApiService.filter(itemName);
                            initListAdapter(reunion);
                        })
                .setNegativeButton("Annuler",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });



        builder.create().show();
    }
    //  ==================================================================================================


}
