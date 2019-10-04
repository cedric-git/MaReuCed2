package com.crea2dev.mareuced.Service;
import com.crea2dev.mareuced.Model.MeetingModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<MeetingModel> DUMMY_MEETINGS = Arrays.asList(
            new MeetingModel("Strategie", "16H20", "Salle 7", "zoe@gmail.com, luigi@yahoo.com"),
            new MeetingModel("Marketing", "9H20", "Salle 3", "bob@gmail.com, ninja@hotmail.com, barnabe@free.fr"),
            new MeetingModel("Design", "17H00", "Amphi", "ced@gmail.com, soph@gr.com")
    );

    static List<MeetingModel> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}