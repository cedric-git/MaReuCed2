package com.crea2dev.mareuced.Service;
import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.utils.SortMeetings;

import java.util.List;

public interface MeetingApiService {

    List<MeetingModel> getMeetings();

    //TODO: Complete with : get all, add and delete
    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(MeetingModel meeting);

    void addMeeting(MeetingModel meeting);

    void sortMeetingsByName(); // ++++++++++++++++++++++++

    void sortMeetingsByDate(); // ++++++++++++++++++++++++
}


