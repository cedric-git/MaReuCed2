package com.crea2dev.mareuced.Service;

import com.crea2dev.mareuced.Model.MeetingModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    private static List<MeetingModel> DUMMY_MEETINGS = Arrays.asList(

            new MeetingModel("Design", "15:00PM", "Salle 2", "zoe@gmail.com" +"\n" +"romain@gmail.com" +"\n" +"luigi@gmail.com"),
            new MeetingModel("Budget", "14:00PM", "Salle 3", "bob@gmail.com"+"\n" +"yoyoy@gmail.com" ),
            new MeetingModel("Coding", "16:00PM", "Amphi", "ced@gmail.com"),
            new MeetingModel("Compta", "15:00PM", "Salle 1", "pierre@gmail.com" +"\n" +"paul@gmail.com"),
            new MeetingModel("Anglais", "15:00PM", "Amphi", "annie@gmail.com" +"\n" +"romane@gmail.com")



            );

    static List<MeetingModel> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}