package com.crea2dev.mareuced.Views;


import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;

import butterknife.ButterKnife;
import butterknife.BindView;

public class MeetingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.nameInput) TextView mName;
    @BindView(R.id.hourInput) TextView mHour;
    @BindView(R.id.placeInput) TextView mPlace;
    @BindView(R.id.emailsInput) TextView mMail;
    @BindView(R.id.item_list_delete_button) ImageButton mDeleteButton;

    //Idem pour autres data

    public MeetingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithMeeting(MeetingModel meeting){
        this.mName.setText(meeting.getName());
        this.mHour.setText(meeting.getHour());
        this.mPlace.setText(meeting.getPlace());
        this.mMail.setText(meeting.getMails());
    }

    @Override
    public void onClick(View view) {
//        ????????????????
    }
}