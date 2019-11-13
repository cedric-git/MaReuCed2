package com.crea2dev.mareuced.Model;


public class DateItemSpinner {

//    private String roomName;
    private String meetingDate;////    //  <<<<<
    private int roomImage;

    public DateItemSpinner(String meetingDate) {
        this.meetingDate = meetingDate;
//        this.roomImage = roomImage;
    }

//    public DateItemSpinnerUtil(String meetingDate) {
//        this.meetingDate = meetingDate;
////        this.roomImage = roomImage;
//    }

    public String getMeetingDate() {
        return meetingDate;
    }

//    public int getRoomImage() {
//        return roomImage;
//    }

}
