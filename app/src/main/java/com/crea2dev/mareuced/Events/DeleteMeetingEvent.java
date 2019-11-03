package com.crea2dev.mareuced.Events;

import com.crea2dev.mareuced.Model.MeetingModel;



/**
 * Event fired when a user deletes a Meeting
 */
public class DeleteMeetingEvent {

    /**
     * Meeting to delete
     */
    public MeetingModel meeting;

    /**
     * Constructor.
     * @param meeting
     */
    public DeleteMeetingEvent(MeetingModel meeting) {
        this.meeting = meeting;
    }
}


