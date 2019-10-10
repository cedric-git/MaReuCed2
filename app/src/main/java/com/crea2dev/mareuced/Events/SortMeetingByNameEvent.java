package com.crea2dev.mareuced.Events;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.utils.SortMeetings;

import java.util.List;

public class SortMeetingByNameEvent {
    /**
     * Meeting list to sort
     */
    public List<MeetingModel> meetings;

    /**
     * Constructor.
     * @param meetings
     */
    public SortMeetingByNameEvent(List<MeetingModel> meetings) {
        this.meetings = meetings;
    }///////FAUX ????


}
