package com.crea2dev.mareuced.Service;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.utils.SortMeetings;
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

    @Override
    public void sortMeetingsByName()
    { this.meetings=SortMeetings.nameOrder(meetings); }

    @Override
    public void sortMeetingsByDate() {
        this.meetings=SortMeetings.dateOrder(meetings);
    }

    @Override
    public void sortMeetingsByPlace() {
        this.meetings=SortMeetings.placeOrder(meetings);
    }

}