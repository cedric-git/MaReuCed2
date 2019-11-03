package com.crea2dev.mareuced.Service;

import com.crea2dev.mareuced.Model.MeetingModel;
import java.util.List;

public interface MeetingApiService {

    List<MeetingModel> getMeetings();

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(MeetingModel meeting);
    /**
     * Add a meeting
     * @param meeting
     */
    void addMeeting(MeetingModel meeting);

    /**
     * sort meetings by Name/Date/Place
     */
    void sortMeetingsByName();

    void sortMeetingsByDate();

    void sortMeetingsByPlace();
}


