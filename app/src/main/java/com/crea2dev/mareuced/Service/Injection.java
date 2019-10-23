package com.crea2dev.mareuced.Service;


public class Injection {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() { return service; }

    public static MeetingApiService getNewInstanceApiService(){
        return new DummyMeetingApiService();
    }
}