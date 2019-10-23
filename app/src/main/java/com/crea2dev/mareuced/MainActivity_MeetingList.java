package com.crea2dev.mareuced;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import com.crea2dev.mareuced.Events.SortMeetingByDateEvent;
import com.crea2dev.mareuced.Events.SortMeetingByNameEvent;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import com.crea2dev.mareuced.Events.SortMeetingByPlaceEvent;
import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.ui.main.MainFragment;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.List;
import butterknife.BindView;


public class MainActivity_MeetingList extends AppCompatActivity {

    @BindView(R.id.button_show_hide_participants) Button mExpand_participant_Button;

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

        mApiService = Injection.getMeetingApiService();
        mMeetings = mApiService.getMeetings();
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

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event){
        mApiService.deleteMeeting(event.meeting);
    }

///////////////////REACT
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

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
