package com.crea2dev.mareuced.ui.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.Events.FilterMeetingByDateEvent;
import com.crea2dev.mareuced.Events.FilterMeetingByPlaceEvent;
import com.crea2dev.mareuced.ui.ui.add_meeting.AddMeetingActivity;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import com.crea2dev.mareuced.Events.SortMeetingByDateEvent;
import com.crea2dev.mareuced.Events.SortMeetingByNameEvent;
import com.crea2dev.mareuced.Events.SortMeetingByPlaceEvent;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.Service.Injection;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;

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

        return view;
    }

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
    public void onSortMeetingsByName (SortMeetingByNameEvent eventSortName){
        MeetingApiService meetingApiService = Injection.getMeetingApiService();
        meetingApiService.sortMeetingsByName();
        this.Meetings=Injection.getMeetingApiService().getMeetings();
        updateUI(this.Meetings);
    };

    @Subscribe
    public void onSortMeetingsByDate(SortMeetingByDateEvent eventSortDate){
        MeetingApiService meetingApiService = Injection.getMeetingApiService();
        meetingApiService.sortMeetingsByDate();
        this.Meetings=Injection.getMeetingApiService().getMeetings();
        updateUI(this.Meetings);
    };

    @Subscribe
    public void onSortMeetingsByPlace (SortMeetingByPlaceEvent eventSortPlace){
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
}