package com.crea2dev.mareuced.Service;

//public class Injection {
//}

import com.crea2dev.mareuced.Service.DummyMeetingApiService;
import com.crea2dev.mareuced.Service.MeetingApiService;

public class Injection {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() { return service; }

    public static MeetingApiService getNewInsanceApiService(){
        return new DummyMeetingApiService();
    }
}