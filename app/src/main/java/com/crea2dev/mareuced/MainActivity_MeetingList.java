package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import com.crea2dev.mareuced.Events.SortMeetingByDateEvent;
import com.crea2dev.mareuced.Events.SortMeetingByNameEvent;

import java.util.Collection;
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
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;


public class MainActivity_MeetingList extends AppCompatActivity {

    @BindView(R.id.expand_participant_Button) Button mExpand_participant_Button;
    @BindView(R.id.textView_expanded_particicipant) TextView mTextView_expanded_particicipant;


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

//        ButterKnife.bind(this, view);
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

    // Manage Expand to show participant (option 2)


///////////////////REACT
    public boolean onOptionsItemSelected(MenuItem item) {

//        switch (item.getItemId()){
//            case R.id.menu_sort_by_name:
//                Injection.getMeetingApiService().getMeetings().setSortingOrder(SortMeetings.SortMethods.NAME_ORDER); // pas util
//                SortMeetings.nameOrder(Injection.getMeetingApiService().getMeetings());
////appel evenement declanchant methode dans main fragment
//
//                System.out.println("NAME - NAME - NAME - NAME - NAME - NAME - NAME - NAME - NAME - ");
//                return true;
//            case R.id.menu_sort_by_date:
//                System.out.println("DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - ");
////                mMeetingViewModel.setSortingOrder(SortMeetings.SortMethods.DATE_ORDER);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }

        switch (item.getItemId()){
            case R.id.menu_sort_by_name:
//                Injection.getMeetingApiService().getMeetings().setSortingOrder(SortMeetings.SortMethods.NAME_ORDER); // pas util
//                SortMeetings.nameOrder(Injection.getMeetingApiService().getMeetings());
//                mMeetingViewModel.setSortingOrder(SortMeetings.SortMethods.NAME_ORDER);
//appel evenement declanchant methode dans main fragment

//                EventBus.getDefault().post(new SortMeetings(mMeetings));
                mApiService = Injection.getMeetingApiService();
                mMeetings = mApiService.getMeetings();
                EventBus.getDefault().post(new SortMeetingByNameEvent(mMeetings));

//                EventBus.getDefault().post(new SortMeetingByNameEvent(SortMeetings.nameOrder(mMeetings)));
//                EventBus.getDefault().post(new SortMeetingByNameEvent(mMeetings));

                System.out.println("NAME - NAME - NAME - NAME - NAME - NAME - NAME - NAME - NAME - ");
                return true;
            case R.id.menu_sort_by_date:
                mApiService = Injection.getMeetingApiService();
                mMeetings = mApiService.getMeetings();
                EventBus.getDefault().post(new SortMeetingByDateEvent(mMeetings));
                System.out.println("DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - DATE - ");
//                mMeetingViewModel.setSortingOrder(SortMeetings.SortMethods.DATE_ORDER);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class SortMeetings {
//        public SortMeetings(List<MeetingModel> mMeetings) {
//        }
        public SortMeetings(Collection<MeetingModel> mMeetings) {
        }

        public enum SortMethods{
            DATE_ORDER,
            NAME_ORDER
        }

        public List<MeetingModel> nameOrder(List<MeetingModel> meetings){

            Collections.sort(meetings, new Comparator<MeetingModel>() {
                @Override
                public int compare(MeetingModel a, MeetingModel b) {
                    return a.getName().compareTo(b.getName());
                }
            });

            return meetings;
        }


        public List<MeetingModel> dateOrder(List<MeetingModel> meetings){

            Collections.sort(meetings, new Comparator<MeetingModel>() {
                @Override
                public int compare(MeetingModel a, MeetingModel b) {
                    return a.getHour().compareTo(b.getHour());
                }
            });
            return meetings;
        }
    }
}
