package com.crea2dev.mareuced.Service;

import com.crea2dev.mareuced.Model.MeetingModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    private static List<MeetingModel> DUMMY_MEETINGS = Arrays.asList(

            new MeetingModel("Design (3)", "15:00PM", "Salle 7", "zoe@gmail.com" +"\n" +"romain@gmail.com" +"\n" +"luigi@gmail.com"),
            new MeetingModel("Budget (1)", "14:00PM", "Salle 3", "bob@gmail.com"),
            new MeetingModel("Coding (2)", "16:00PM", "Amphi", "ced@gmail.com")
    );

    static List<MeetingModel> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}