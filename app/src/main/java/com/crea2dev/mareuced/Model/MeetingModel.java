package com.crea2dev.mareuced.Model;

import java.util.ArrayList;
import java.util.List;

public class MeetingModel {

        private String name;
        private String hour;
        private String place;
        private String mails;
        private ArrayList participantsList;

//
    public MeetingModel(String name, String hour, String place, String mails, ArrayList participantsList) {
//    public MeetingModel(String name, String hour, String place, String mails) {
            this.name = name;
            this.hour = hour;
            this.place = place;
            this.mails = mails;
            this.participantsList = participantsList;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getHour() {
            return hour;
        }
        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getPlace() {
            return place;
        }
        public void setPlace(String place) {
            this.place = place;
        }

        public String getMails() {
            return mails;
        }
        public void setMails(String mails) {
            this.mails = mails;
        }

        public ArrayList getParticipantsList() {return participantsList; }
        public void setParticipantsList(ArrayList participantsList) { this.participantsList = participantsList; }
}

