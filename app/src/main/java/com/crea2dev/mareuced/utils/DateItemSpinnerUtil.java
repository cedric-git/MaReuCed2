package com.crea2dev.mareuced.utils;

import android.view.View;
import android.widget.Spinner;

import com.crea2dev.mareuced.Model.RoomItem;
import com.crea2dev.mareuced.Model.RoomItemSpinner;
import com.crea2dev.mareuced.ui.ui.main.RoomAdapter;

import java.util.ArrayList;

public abstract class DateItemSpinnerUtil {

    private static ArrayList<RoomItemSpinner> mRoomItemSpinners;
    private static RoomItemSpinner roomItemSpinner;

    private static void initListSpinner(){
       mRoomItemSpinners = new ArrayList<>();

        for ( RoomItem item : RoomItem.values() ) {
            mRoomItemSpinners.add(new RoomItemSpinner(item.getName()));
        }
    }

    public static void initRoomSpinner(View view, Spinner spinner){
        initListSpinner();
        RoomAdapter mRoomAdapter = new RoomAdapter(view.getContext(), mRoomItemSpinners);
        spinner.setAdapter(mRoomAdapter);
    }

}
