package com.crea2dev.mareuced.Events;


public class FilterMeetingByDateEvent {
    /**
     * Meeting list to sort
     */
    public String filter;

    /**
     * Constructor.
     * @param filter
     */
    public FilterMeetingByDateEvent(String filter) {
        this.filter = filter;
    }

}