package com.crea2dev.mareuced;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.Service.DummyMeetingGenerator;
import com.crea2dev.mareuced.Service.Injection;
import com.crea2dev.mareuced.Service.MeetingApiService;
import com.crea2dev.mareuced.utils.SortMeetings;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingUnitTest {

    private MeetingApiService mApiService;
    @Before
    public void setup() {
        mApiService = Injection.getNewInstanceApiService();
    }

    @Test
    public void addNewMeetingWithSuccess() {

        int meetingsSize = mApiService.getMeetings().size();
        MeetingModel newMeeting = new MeetingModel("Réunion", "17:30PM", "amphi", "ced@sds.fr");
        mApiService.addMeeting(newMeeting);
        assertEquals(mApiService.getMeetings().size(), meetingsSize + 1);
    }

    @Test
    public void deleteMeetingWithSuccess() {

        MeetingModel meetingToDelete = mApiService.getMeetings().get(1);
        mApiService.deleteMeeting(meetingToDelete);
        assertFalse(mApiService.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void sortMeetings_BY_NAME_WithSuccess() {
        MeetingModel m1 = new MeetingModel("1 Reunion 1", "15:00PM", "Salle 7", "zoe@gmail.com");
        MeetingModel m2 = new MeetingModel("2 Reunion 2",  "14:00PM", "Salle 3", "bob@gmail.com");
        MeetingModel m3 = new MeetingModel("3 Reunion 3", "16:00PM", "Amphi", "ced@gmail.com");


        List<MeetingModel> meetings = new ArrayList<>();
        meetings.add(m1);
        meetings.add(m3);
        meetings.add(m2);

        SortMeetings.nameOrder(meetings);

        assertSame(m1, meetings.get(0));
        assertSame(m2, meetings.get(1));
        assertSame(m3, meetings.get(2));
    }

    @Test
    public void sortMeetings_BY_DATE_WithSuccess() {


        MeetingModel m1 = new MeetingModel("1 Reunion 1", "15:00PM", "Salle 7", "zoe@gmail.com");
        MeetingModel m2 = new MeetingModel("2 Reunion 2",  "16:00PM", "Salle 3", "bob@gmail.com");
        MeetingModel m3 = new MeetingModel("3 Reunion 3", "17:00PM", "Amphi", "ced@gmail.com");

        List<MeetingModel> meetings = new ArrayList<>();
        meetings.add(m3);
        meetings.add(m2);
        meetings.add(m1);

        SortMeetings.dateOrder(meetings);

        assertSame(m1, meetings.get(0));
        assertSame(m2, meetings.get(1));
        assertSame(m3, meetings.get(2));
    }

}