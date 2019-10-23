package com.crea2dev.mareuced.Events;

import com.crea2dev.mareuced.Model.MeetingModel;
import java.util.List;

public class SortMeetingByDateEvent {
        /**
         * Meeting list to sort
         */
        public List<MeetingModel> meetings;

        /**
         * Constructor.
         * @param meetings
         */
        public SortMeetingByDateEvent(List<MeetingModel> meetings) {this.meetings = meetings;}

}




