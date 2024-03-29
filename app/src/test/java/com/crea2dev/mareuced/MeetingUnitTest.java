///*
// * Copyright 2017 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package org.gradle.api.artifacts;
//
//import org.gradle.api.Incubating;
//
///**
// * Describes the dependencies of a variant declared in a resolved component's metadata, which typically originate from
// * a component descriptor (Gradle metadata file, Ivy file, Maven POM). This interface can be used to adjust the dependencies
// * of a published component via metadata rules (see {@link org.gradle.api.artifacts.dsl.ComponentMetadataHandler}.
// *
// * @since 4.5
// */
//@Incubating
//public interface DirectDependenciesMetadata extends DependenciesMetadata<DirectDependencyMetadata> {
//
//}
//                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
package com.crea2dev.mareuced;

import com.crea2dev.mareuced.Events.FilterMeetingByPlaceEvent;
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
// through apiService, check size before then add meeeting and check the size count 1 more after

        int meetingsSize = mApiService.getMeetings().size();
        MeetingModel newMeeting = new MeetingModel("Réunion", "17:30PM", "amphi", "ced@sds.fr");
        mApiService.addMeeting(newMeeting);
        assertEquals(mApiService.getMeetings().size(), meetingsSize + 1);
    }

    @Test
    public void deleteMeetingWithSuccess() {
// trough apiService, get one meeting, delete it ans check it is not belong anymore to meetings list

        MeetingModel meetingToDelete = mApiService.getMeetings().get(1);
        mApiService.deleteMeeting(meetingToDelete);
        assertFalse(mApiService.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterPlaceWithSuccess() {
        String text = "salle 1";
        MeetingModel newMeeting = new MeetingModel("Reunion_Test", "17:30PM","salle 1",
                "jose@gmail.com");
        mApiService.addMeeting(newMeeting);
        ArrayList<MeetingModel> reunionSorted = mApiService.filter(text);

        assertEquals(2, reunionSorted.size());

    }

    @Test
    public void filterDateWithSuccess() {
        String text = "15:00PM";
        MeetingModel newMeeting = new MeetingModel("Reunion_Test", "15:00PM","salle 1",
                "jose@gmail.com");
        mApiService.addMeeting(newMeeting);
        ArrayList<MeetingModel> reunionSorted = mApiService.filter(text);

        assertEquals(4, reunionSorted.size());

    }

//
//    @Test
//    public void sortMeetings_BY_NAME_WithSuccess() {
//
//// create 3 meetings ordered by name, put them in a list, <<<<<<<<<<<<<<
//// sort meetings with nameOrder method <<<<<<<<<<<<<<<
//// and check it is the same as previously creates list <<<<<<<<<<<<<<<
//
//        MeetingModel m1 = new MeetingModel("1 Reunion 1", "15:00PM", "Salle 7", "zoe@gmail.com");
//        MeetingModel m2 = new MeetingModel("2 Reunion 2",  "14:00PM", "Salle 3", "bob@gmail.com");
//        MeetingModel m3 = new MeetingModel("3 Reunion 3", "16:00PM", "Amphi", "ced@gmail.com");
//
//        List<MeetingModel> meetings = new ArrayList<>();
//        meetings.add(m1);
//        meetings.add(m3);
//        meetings.add(m2);
//
//        SortMeetings.nameOrder(meetings);
//
//        assertSame(m1, meetings.get(0));
//        assertSame(m2, meetings.get(1));
//        assertSame(m3, meetings.get(2));
//    }
//
//    @Test
//    public void sortMeetings_BY_DATE_WithSuccess() {
//
//
//        MeetingModel m1 = new MeetingModel("4 Reunion 4", "15:00PM", "Salle 7", "zoe@gmail.com");
//        MeetingModel m2 = new MeetingModel("2 Reunion 2",  "16:00PM", "Salle 3", "bob@gmail.com");
//        MeetingModel m3 = new MeetingModel("3 Reunion 3", "17:00PM", "Amphi", "ced@gmail.com");
//
//        List<MeetingModel> meetings = new ArrayList<>();
//        meetings.add(m3);
//        meetings.add(m2);
//        meetings.add(m1);
//
//        SortMeetings.dateOrder(meetings);
//
//        assertSame(m1, meetings.get(0));
//        assertSame(m2, meetings.get(1));
//        assertSame(m3, meetings.get(2));
//    }
//
//    @Test
//    public void sortMeetings_BY_PLACE_WithSuccess() {
//        MeetingModel m1 = new MeetingModel("5 Reunion 5", "15:00PM", "Amphi", "zoe@gmail.com");
//        MeetingModel m2 = new MeetingModel("2 Reunion 2",  "14:00PM", "Salle 3", "bob@gmail.com");
//        MeetingModel m3 = new MeetingModel("3 Reunion 3", "16:00PM", "Salle 7", "ced@gmail.com");
//
//        List<MeetingModel> meetings = new ArrayList<>();
//        meetings.add(m1);
//        meetings.add(m3);
//        meetings.add(m2);
//
//        SortMeetings.placeOrder(meetings);
//
//        assertSame(m1, meetings.get(0));
//        assertSame(m2, meetings.get(1));
//        assertSame(m3, meetings.get(2));
//    }

}