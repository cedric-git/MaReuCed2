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
    }

    @Test
    public void sortMeetings_BY_DATE_WithSuccess() {
    }

}