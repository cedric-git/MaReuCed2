package com.crea2dev.mareuced.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import android.support.v7.app.AppCompatActivity;
import com.crea2dev.mareuced.AddMeetingActivity;
import com.crea2dev.mareuced.MainActivity_MeetingList;
import com.crea2dev.mareuced.Views.MeetingRecycleViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.Service.Injection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView; // 1 - Declare RecyclerView
//    @BindView(R.id.FABaddMeeting)
//    public android.support.design.widget.FloatingActionButton mFavFab;
    private FloatingActionButton mCreateMeetingFloatingActionButton;
    private FloatingActionButton fab;



    private MainViewModel mViewModel;

    //FOR DATA
//    private Disposable disposable;
    // 2 - Declare list of users (GithubUser) & Adapter
    private List<MeetingModel> Meetings;
    private MeetingRecycleViewAdapter adapter;

    public MainFragment() { }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        ButterKnife.bind(this, view);
        this.configureRecyclerView(); // - 4 Call during UI creation

        fab = (FloatingActionButton) view.findViewById(R.id.FABaddMeeting);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AddMeetingActivity.class));
            }
        });

//        mCreateMeetingFloatingActionButton = getActivity().findViewById(R.id.FABaddMeeting);
//        mCreateMeetingFloatingActionButton.setOnClickListener(v -> mPresenter.createMeeting());

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


//*****************************************************
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }
//******************************************************


    // -----------------
    // CONFIGURATION
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.Meetings = new ArrayList<>();
        Injection.getMeetingApiService().getMeetings();
        this.Meetings=Injection.getMeetingApiService().getMeetings();
        // 3.2 - Create adapter passing the list of users
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
        Meetings.addAll(users);
        adapter.notifyDataSetChanged();
    }

}
