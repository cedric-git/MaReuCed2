package com.crea2dev.mareuced.Events;


public class FilterMeetingByPlaceEvent {
    /**
     * Meeting list to sort
     */
    public String filter;

    /**
     * Constructor.
     * @param filter
     */
    public FilterMeetingByPlaceEvent(String filter) {
        this.filter = filter;
    }

}