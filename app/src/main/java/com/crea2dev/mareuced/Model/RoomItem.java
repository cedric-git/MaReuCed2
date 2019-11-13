package com.crea2dev.mareuced.Model;

import com.crea2dev.mareuced.R;

public enum RoomItem {
    SALLE1("Salle 1"),
    SALLE2("Salle 2"),
    SALLE3( "Salle 3"),
    SALLE4( "Salle 4"),
    SALLE5( "Salle 5"),
    SALLE6("Salle 6"),
    SALLE7("Salle 7"),
    SALLE8("Salle 8"),
    SALLE9("Salle 9"),
    SALLE10("Salle 10");

    private int idDrawable;
    private String name;

//    RoomItem(int idDrawable, String name) {
    RoomItem(String name) {
//        this.idDrawable = idDrawable;
        this.name = name;
    }

//    public int getIdDrawable() {
//        return idDrawable;
//    }

    public String getName() {
        return name;
    }

}
