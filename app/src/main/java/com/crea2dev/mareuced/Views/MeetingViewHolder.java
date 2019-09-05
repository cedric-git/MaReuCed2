package com.crea2dev.mareuced.Views;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;

import butterknife.ButterKnife;
import butterknife.BindView;

public class MeetingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.nameInput) TextView mName;
    //Idem pour autres data

    public MeetingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithMeeting(MeetingModel meeting){
        this.mName.setText(meeting.getName());
        //Idem pour autres data
    }

    @Override
    public void onClick(View view) {
//        ????????????????
    }
}