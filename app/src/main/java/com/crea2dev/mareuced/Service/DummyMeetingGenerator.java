package com.crea2dev.mareuced.Service;
import com.crea2dev.mareuced.Model.MeetingModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public abstract class DummyMeetingGenerator {


    private static ArrayList list;
    public static void main(String[] args) {
        ArrayList <String> list = new ArrayList<>();
        list.add("zoe@gmail.com");
        list.add("soph@gr.com");
        list.add("ced@gmail.com");
        list.add("ninja@hotmail.com"); }


    private static List<MeetingModel> DUMMY_MEETINGS = Arrays.asList(
//            new MeetingModel("3 Reunion 3", "15:00PM", "Salle 7", "zoe@gmail.com"),
//            new MeetingModel("1 Reunion 1", "14:00PM", "Salle 3", "bob@gmail.com"),
//            new MeetingModel("2 Reunion 2", "16:00PM", "Amphi", "ced@gmail.com")

            new MeetingModel("Strategie", "16H20", "Salle 7", "zoe@gmail.com, luigi@yahoo.com", list)
//            new MeetingModel("Marketing", "9H20", "Salle 3", "bob@gmail.com, ninja@hotmail.com, barnabe@free.fr", (ArrayList) list)
//            new MeetingModel("Design", "17H00", "Amphi", "ced@gmail.com, soph@gr.com", (ArrayList) list))
//            new MeetingModel("Design", "17H00", "Amphi", "ced@gmail.com, soph@gr.com", ArrayList ['ds','sds','dsd'])
//            new MeetingModel("Design", "17H00", "Amphi", "ced@gmail.com, soph@gr.com")

    );

    static List<MeetingModel> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}