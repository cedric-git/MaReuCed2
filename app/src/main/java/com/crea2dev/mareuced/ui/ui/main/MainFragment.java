package com.crea2dev.mareuced.ui.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.Events.FilterMeetingByDateEvent;
import com.crea2dev.mareuced.Events.FilterMeetingByPlaceEvent;
import com.crea2dev.mareuced.Model.RoomItemSpinner;
import com.crea2dev.mareuced.ui.ui.add_meeting.AddMeetingActivity;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import com.crea2dev.mareuced.Events.SortMeetingByDateEvent;
import com.crea2dev.mareuced.Events.SortMeetingByNameEvent;
import com.crea2dev.mareuced.Events.SortMeetingByPlaceEvent;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.Service.Injection;

import com.crea2dev.mareuced.utils.RoomItemSpinnerUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    String itemName = "";
    List<MeetingModel> mMeetings;
    private RecyclerView mRecyclerView;

    // FOR DESIGN
    // 1 - Declare/Bind RecyclerView & fab  <<<<<<<<<<<<<<<<<<<<<
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.FAB_add_Meeting) FloatingActionButton fab;

    //FOR DATA

    // 2 - Declare list of Meetings & Adapter
    private List<MeetingModel> Meetings;
    private MeetingRecycleViewAdapter adapter;

    // 3 - build <<<<<<<<<<<<<<<<<<<<<<
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        ButterKnife.bind(this, view);

        // - 4 Call during UI creation
        this.configureRecyclerView();

        // - 5 Fab action   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< FAB add meeting
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AddMeetingActivity.class));
            }
        });




//        initListAdapter(Meetings);

        return view;
    }
//
//    private void initListAdapter(List<MeetingModel> Meetings) { //  reunions
//        mRecyclerView.setAdapter( new MeetingRecycleViewAdapter(Meetings));//  reunions
//        Log.i("meeting Fragment", String.valueOf(Meetings.size()));//  reunions
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.Meetings = new ArrayList<>();
        Injection.getMeetingApiService().getMeetings();
        this.Meetings=Injection.getMeetingApiService().getMeetings();

        // 3.2 - Create adapter passing the list of meetings
        this.adapter = new MeetingRecycleViewAdapter(this.Meetings);

        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);

        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<MeetingModel> users){
        Meetings = new ArrayList<>();
        Meetings.addAll(users);
        adapter.filterMeetings(Meetings);
        adapter.notifyDataSetChanged();


    }
@Override
    public void onStart (){
        super.onStart();
    EventBus.getDefault().register(this);

    this.Meetings=Injection.getMeetingApiService().getMeetings();
        updateUI(this.Meetings);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    // -------------------
    // DELETE MEETING
    // -------------------

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
    MeetingApiService meetingApiService = Injection.getMeetingApiService();
    meetingApiService.deleteMeeting(event.meeting);
    this.Meetings=Injection.getMeetingApiService().getMeetings();
    updateUI(this.Meetings);
    }

    // -------------------
    // SORT MEETINGS
    // -------------------

    // Sorting methods through events
    @Subscribe
    public void onSortMeetingsByName (SortMeetingByNameEvent eventSortname){
        MeetingApiService meetingApiService = Injection.getMeetingApiService();
        meetingApiService.sortMeetingsByName();
        this.Meetings=Injection.getMeetingApiService().getMeetings();
        updateUI(this.Meetings);
    };

    @Subscribe
    public void onSortMeetingsByDate(SortMeetingByDateEvent eventSortdate){
        MeetingApiService meetingApiService = Injection.getMeetingApiService();
        meetingApiService.sortMeetingsByDate();
        this.Meetings=Injection.getMeetingApiService().getMeetings();
        updateUI(this.Meetings);
    };

    @Subscribe
    public void onSortMeetingsByPlace (SortMeetingByPlaceEvent eventSortplace){
        MeetingApiService meetingApiService = Injection.getMeetingApiService();
        meetingApiService.sortMeetingsByPlace();
        this.Meetings=Injection.getMeetingApiService().getMeetings();
        updateUI(this.Meetings);
    };
    // -------------------
    // FILTER MEETINGS
    // -------------------

    @Subscribe
    public void onFilterMeetingsByPlace (FilterMeetingByPlaceEvent eventFilterPlace){
        MeetingApiService meetingApiService = Injection.getMeetingApiService();
        this.Meetings=Injection.getMeetingApiService().filter(eventFilterPlace.filter);
        updateUI(this.Meetings);
    };

    @Subscribe
    public void onFilterMeetingsByDate (FilterMeetingByDateEvent eventFilterDate){
        MeetingApiService meetingApiService = Injection.getMeetingApiService();
        this.Meetings=Injection.getMeetingApiService().filter(eventFilterDate.filter);
        updateUI(this.Meetings);
    };


////  =====================================================================FILTER PLACE
////
//    private void configureAndShowAlertDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_list_dialog, null);
//
//        final Spinner spinner = view.findViewById(R.id.spinner_choice); //  <<<< Oter 'final'
//        RoomItemSpinnerUtil.initRoomSpinner(view, spinner);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                RoomItemSpinner roomItemSpinner = (RoomItemSpinner) spinner.getSelectedItem();
//                itemName = roomItemSpinner.getRoomName();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });
//
//        builder.setTitle("Sélectionnez une valeur")
//                .setView(view)
//                .setPositiveButton("Filtrer",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ArrayList<MeetingModel> meeting = MainActivity_MeetingList// ReunionListActivity
//                                        .mApiService.filter(itemName); //       mReunionListService
//                                initListAdapter(meeting);
//                            }
//                        })
//                .setNegativeButton("Annuler",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        });
//
//        builder.create().show();
//    }
//
////  =====================================================================FILTER DATE
//
//    private void configureAndShowAlertDate(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_list_dialog, null);
//
//        final Spinner spinner = view.findViewById(R.id.spinner_choice);
//        List<String> arrayList = new ArrayList<>();
//        Set<String> set = new HashSet<>();
//        for (MeetingModel r : mMeetings){
//            set.add(r.getHour());
//        }
//        for (String s : set){
//            arrayList.add(s);
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, arrayList);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                itemName = spinner.getSelectedItem().toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {}
//        });
//
//        builder.setTitle("Sélectionnez une valeur")
//                .setView(view)
//                .setPositiveButton("Filtrer",
//                        (dialog, which) -> {
//                            ArrayList<MeetingModel> reunion = MainActivity_MeetingList// ReunionListActivity
//                                    .mApiService.filter(itemName);
//                            initListAdapter(reunion);
//                        })
//                .setNegativeButton("Annuler",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        });
//
//
//
//        builder.create().show();
//    }
//        //  ==================================================================================================


}