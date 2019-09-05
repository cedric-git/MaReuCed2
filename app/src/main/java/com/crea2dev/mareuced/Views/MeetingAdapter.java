package com.crea2dev.mareuced.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;

import java.util.List;

//public class MeetingAdapter {
//}
public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    // FOR DATA
    private List<MeetingModel> Meetings;

    // CONSTRUCTOR
    public MeetingAdapter(List<MeetingModel> Meetings) {
        this.Meetings = Meetings;
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
    public void onBindViewHolder(MeetingViewHolder viewHolder, int position) {
        viewHolder.updateWithMeeting(this.Meetings.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.Meetings.size();
    }
}