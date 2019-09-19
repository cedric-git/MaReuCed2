package com.crea2dev.mareuced.Service;
import com.crea2dev.mareuced.Model.MeetingModel;

        import java.util.List;

public interface MeetingApiService {

    List<MeetingModel> getMeetings();

    //TODO: Complete with : get all, add and delete
    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(MeetingModel meeting);

}


