package com.crea2dev.mareuced.Views;


import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.crea2dev.mareuced.Events.DeleteMeetingEvent;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.R;
import org.greenrobot.eventbus.EventBus;
import butterknife.ButterKnife;
import butterknife.BindView;

public class MeetingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.nameInput) TextView mName;
    @BindView(R.id.hourInput) TextView mHour;
    @BindView(R.id.placeInput) TextView mPlace;
    @BindView(R.id.item_list_delete_button) ImageButton mDeleteButton;
    @BindView(R.id.button_show_hide_participants) Button mButton_show_hide_participants;
    @BindView(R.id.participants_text) TextView mParticipants;

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

        mButton_show_hide_participants.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowHideParticipantsPanel();
            }
            private void ShowHideParticipantsPanel() {
                if (mParticipants.getVisibility() == View.GONE) {
                    mParticipants.setVisibility(View.VISIBLE);
                } else {
                    mParticipants.setVisibility(View.GONE);
                }
            }
        });
    }

    public void updateWithMeeting(MeetingModel meeting){
        this.mName.setText(meeting.getName());
        this.mHour.setText(meeting.getHour());
        this.mPlace.setText(meeting.getPlace());
        this.mParticipants.setText(meeting.getMails());
        mMeeting = meeting;
    }

    @Override
    public void onClick(View view) {
    }
}