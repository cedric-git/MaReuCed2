package com.crea2dev.mareuced.Events;

import com.crea2dev.mareuced.Model.MeetingModel;

import java.util.List;

public class SortMeetingByPlaceEvent {
    /**
     * Meeting list to sort
     */
    public List<MeetingModel> meetings;

    /**
     * Constructor.
     * @param meetings
     */
    public SortMeetingByPlaceEvent(List<MeetingModel> meetings) {
        this.meetings = meetings;
    }


}
