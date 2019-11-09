package com.crea2dev.mareuced.Service;

import com.crea2dev.mareuced.Model.MeetingModel;
import java.util.List;

public interface MeetingApiService {

    /**
     * List meetings
     */
    List<MeetingModel> getMeetings();

    /**
     * Delete a meeting
     * @param meeting
     */
    void deleteMeeting(MeetingModel meeting);

    // ==========================================================================ADD

    /**
     * Add a meeting
     * @param meeting
     */
    void addMeeting(MeetingModel meeting);

    // =========================================================================SORT

    /**
     * sort meetings by Name/Date/Place
     */
    void sortMeetingsByName();

    void sortMeetingsByDate();

    void sortMeetingsByPlace();
}