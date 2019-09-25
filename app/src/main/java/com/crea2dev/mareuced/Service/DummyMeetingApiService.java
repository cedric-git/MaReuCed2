package com.crea2dev.mareuced.Service;

import com.crea2dev.mareuced.Model.MeetingModel;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<MeetingModel> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<MeetingModel> getMeetings() {
        return meetings; }

    @Override
    public void addMeeting(MeetingModel meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(MeetingModel meeting) {
        meetings.remove(meeting);
    }



}