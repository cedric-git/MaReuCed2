package com.crea2dev.mareuced.ui.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import com.crea2dev.mareuced.Events.SortMeetingByDateEvent;
import com.crea2dev.mareuced.Events.SortMeetingByNameEvent;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuItem;
import com.crea2dev.mareuced.Events.SortMeetingByPlaceEvent;
import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.List;
import butterknife.BindView;


public class MainActivity_MeetingList extends AppCompatActivity {

    private MeetingApiService mApiService;     //  <<<<< declare object based on ApiService
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
    // Switch-Case to sort meeting by name/hour/place
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
