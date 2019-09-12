package com.crea2dev.mareuced.Views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.AddMeetingActivity;
import com.crea2dev.mareuced.AddMeetingFragment;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import com.crea2dev.mareuced.MainActivity_MeetingList;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.core.content.ContextCompat.startActivity;

//public class MeetingRecycleViewAdapter {
//}
public class MeetingRecycleViewAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    // FOR DATA
    private List<MeetingModel> mMeetings;

    // CONSTRUCTOR
    public MeetingRecycleViewAdapter(List<MeetingModel> mMeetings) {this.mMeetings = mMeetings;}

    @Override
    public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new MeetingViewHolder(view);
    }



    // UPDATE VIEW HOLDER WITH A MEETING
    @Override
    public void onBindViewHolder(final MeetingViewHolder viewHolder, int position) {
        viewHolder.updateWithMeeting(this.mMeetings.get(position));


//        FloatingActionButton fab;
//        fab = (FloatingActionButton) findViewById(R.id.FABaddMeeting);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity_MeetingList.this, AddMeetingActivity.class));
//            }
//        });
////
//        viewHolder.mMeetingLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent AddNewMeeting = new Intent(viewHolder.mName.getContext(), AddMeetingFragment.class);
////                AddNewMeeting.putExtra("Meeting", meeting);
//                v.getContext().startActivity(AddNewMeeting);
//
//            }
//        });

    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        @BindView(R.id.AddMeetingLayout)
        public ConstraintLayout mMeetingLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}