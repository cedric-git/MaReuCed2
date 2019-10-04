package com.crea2dev.mareuced.utils;

import com.crea2dev.mareuced.Model.MeetingModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortMeetings {

    public enum SortMethods{

        DATE_ORDER,
        NAME_ORDER

    }

    public static List<MeetingModel> nameOrder(List<MeetingModel> meetings){

        Collections.sort(meetings, new Comparator<MeetingModel>() {
            @Override
            public int compare(MeetingModel a, MeetingModel b) {
                return a.getName().compareTo(b.getName());
            }
        });

        return meetings;
    }


    public static List<MeetingModel> dateOrder(List<MeetingModel> meetings){

        Collections.sort(meetings, new Comparator<MeetingModel>() {
            @Override
            public int compare(MeetingModel a, MeetingModel b) {
                return a.getHour().compareTo(b.getHour());
            }
        });
        return meetings;
    }
}