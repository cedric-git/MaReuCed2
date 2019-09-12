package com.crea2dev.mareuced.Service;
import com.crea2dev.mareuced.Model.MeetingModel;

        import java.util.List;

public interface MeetingApiService {

    List<MeetingModel> getMeetings();

    /**
     * Deletes a neighbour
     * @param meeting
     */
    void deleteNeighbour(MeetingModel meeting);

}


//TODO: Complete with : get all, add and delete