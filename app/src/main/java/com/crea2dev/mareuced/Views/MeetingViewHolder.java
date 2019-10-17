package com.crea2dev.mareuced.Views;


import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.Service.Injection;

import com.crea2dev.mareuced.R;
import com.crea2dev.mareuced.repositories.MeetingRepository;
import com.crea2dev.mareuced.utils.SortMeetings;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

import butterknife.ButterKnife;
import butterknife.BindView;

import static com.crea2dev.mareuced.utils.SortMeetings.SortMethods.DATE_ORDER;
import static com.crea2dev.mareuced.utils.SortMeetings.SortMethods.NAME_ORDER;
import static java.security.AccessController.getContext;

public class MeetingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private MeetingRepository mMeetingRepository;

    @BindView(R.id.nameInput) TextView mName;
    @BindView(R.id.hourInput) TextView mHour;
    @BindView(R.id.placeInput) TextView mPlace;
    @BindView(R.id.emailsInput) TextView mMail;
    @BindView(R.id.item_list_delete_button) ImageButton mDeleteButton;
//    @BindView(R.id.participant_ListView) ListView mListview;

    public MeetingModel mMeeting;
    public MeetingApiService meetingApiService;

    public MeetingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);



        mDeleteButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(mMeeting));
            }
        });

//        Creer adater pour list itemView
//                new
//                        list view.set adapter

        ListView mListView;
        String[] prenoms = new String[]{
                "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
                "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
                "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
                "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
                "Yann", "Zoé"
        };
//
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prenoms);
//        ListView listView = (ListView) findViewById(R.id.participant_ListView);
//        listView.setAdapter(itemsAdapter);



//        public void setSortingOrder(SortMeetings.SortMethods sortingOrder) {
//
//            switch (sortingOrder){
//                case DATE_ORDER:
//                    mMeetings.setValue(SortMeetings.dateOrder(mMeetingRepository.getMeetings()));
//                    break;
//                case NAME_ORDER:
//                    mMeetings.setValue(SortMeetings.nameOrder(mMeetingRepository.getMeetings()));
//                    break;
//
//            }
//        }

    }

//    private ListView mListView;
//    private String[] prenoms = new String[]{
//            "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
//            "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
//            "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
//            "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
//            "Yann", "Zoé"
//    };



    public void updateWithMeeting(MeetingModel meeting){
        this.mName.setText(meeting.getName());
        this.mHour.setText(meeting.getHour());
        this.mPlace.setText(meeting.getPlace());
        this.mMail.setText(meeting.getMails());
//        this.mListview.setText(meeting.getParticipantsList());

        mMeeting = meeting;
    }

    @Override
    public void onClick(View view) {

    }
//@Override
//       mMail.setOnClickListener(
//               new OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(getContext(), "liste cliquee", Toast.LENGTH_SHORT).show();
//        }
//    });

//    public boolean onOptionsItemSelected(MenuItem item) {
//
////        switch (item.getItemId()){
////            case R.id.menu_sort_by_name:
////                Toast.makeText(MainActivity_MeetingList, "Field missing", Toast.LENGTH_SHORT).show();
////            case R.id.menu_sort_by_date:
////                Toast.makeText(getContext(), "Field missing", Toast.LENGTH_SHORT).show();
////            default:
////                return super.onOptionsItemSelected(item);
////        }
//    }
}