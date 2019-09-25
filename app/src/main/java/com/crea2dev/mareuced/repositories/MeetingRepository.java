package com.crea2dev.mareuced.repositories;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;

import java.util.List;

public class MeetingRepository {
    private static MeetingRepository instance;
    private MeetingApiService apiService;

    private MeetingRepository(){
        //No constructor allowed
    }

    public static MeetingRepository getInstance(){
        if (instance == null){
            instance = new MeetingRepository();
        }
        return instance;
    }

    public List<MeetingModel> getMeetings(){

        if (apiService == null){
            apiService = Injection.getMeetingApiService();
        }
        return apiService.getMeetings();
    }

    public void deleteMeeting(MeetingModel meeting){
        if (apiService == null){
            apiService = Injection.getMeetingApiService();
        }
        apiService.deleteMeeting(meeting);
    }


    public void addMeeting(MeetingModel meeting){
        if (apiService == null){
            apiService = Injection.getMeetingApiService();
        }
        apiService.addMeeting(meeting);
    }
}
