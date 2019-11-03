package com.crea2dev.mareuced.ui.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingRecycleViewAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    @BindView(R.id.nameInput) TextView mName;
    @BindView(R.id.hourInput) TextView mHour;
    @BindView(R.id.placeInput) TextView mPlace;
    @BindView(R.id.item_list_delete_button) ImageButton mDeleteButton;
    @BindView(R.id.button_show_hide_participants) Button mExpand_participant_Button;

    private ImageView imageView;
        private TextView titleTextView;
        private TextView mailsTextView;
        private TextView hourTextView;
        private ImageButton deleteButton;

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
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_list_delete_button) public Button mDeleteButton;
        @BindView(R.id.AddMeetingLayout) public ConstraintLayout mMeetingLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}