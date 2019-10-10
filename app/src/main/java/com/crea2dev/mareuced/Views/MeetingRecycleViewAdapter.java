package com.crea2dev.mareuced.Views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.AddMeetingActivity;
import com.crea2dev.mareuced.AddMeetingFragment;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import com.crea2dev.mareuced.MainActivity_MeetingList;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static androidx.core.content.ContextCompat.startActivity;

//public class MeetingRecycleViewAdapter {
//}


public class MeetingRecycleViewAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    @BindView(R.id.nameInput) TextView mName;
    @BindView(R.id.hourInput) TextView mHour;
    @BindView(R.id.placeInput) TextView mPlace;
    @BindView(R.id.emailsInput) TextView mMail;
    @BindView(R.id.item_list_delete_button) ImageButton mDeleteButton;
    @BindView(R.id.expand_participant_Button) Button mExpand_participant_Button;
    @BindView(R.id.textView_expanded_particicipant) TextView mTextView_expanded_particicipant;

    private ImageView imageView;
        private TextView titleTextView;
        private TextView mailsTextView;
        private TextView hourTextView;
        private ImageButton deleteButton;

//    public MeetingsViewHolder(@NonNull View itemView) {
//        super(itemView);
//
////        imageView = itemView.findViewById(R.id.imageView_item_meeting);
////        titleTextView = itemView.findViewById(R.id.textView_item_main);
//        mailsTextView = itemView.findViewById(R.id.emailsInput);
//        hourTextView = itemView.findViewById(R.id.hourInput);
//        deleteButton = itemView.findViewById(R.id.item_list_delete_button);
//    }

    // FOR DATA
    private List<MeetingModel> mMeetings;

    // CONSTRUCTOR
    public MeetingRecycleViewAdapter(List<MeetingModel> mMeetings) {
        this.mMeetings = mMeetings;
    }

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
    public void onBindViewHolder(final MeetingViewHolder viewHolder, final int position) {
        viewHolder.updateWithMeeting(this.mMeetings.get(position));



//        MeetingViewHolder.imageView.setImageResource(R.drawable.ic_launcher_background);
//        MeetingViewHolder.titleTextView.setText(mMeetings.get(position).getName());
//        MeetingViewHolder.setText(mMeetings.get(position).getMails());
//
//            MeetingViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Using our EventBus library to communicate. Publish
//                    EventBus.getDefault().post(new DeleteMeetingEvent(mMeetings.get(position)));
//                }
//            });


    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_list_delete_button)
        public Button mDeleteButton;

        @BindView(R.id.AddMeetingLayout)
        public ConstraintLayout mMeetingLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mExpand_participant_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTextView_expanded_particicipant.setVisibility(View.VISIBLE);
                }
            });

        }
    }


}