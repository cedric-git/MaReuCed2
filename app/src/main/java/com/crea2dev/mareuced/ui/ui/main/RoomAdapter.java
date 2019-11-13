package com.crea2dev.mareuced.ui.ui.main;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.crea2dev.mareuced.Model.RoomItemSpinner;
import com.crea2dev.mareuced.R;


public class RoomAdapter extends ArrayAdapter<RoomItemSpinner> {

    public RoomAdapter(@NonNull Context context, ArrayList<RoomItemSpinner> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.room_spinner_row, parent, false);
        }
//        ImageView imageView = convertView.findViewById(R.id.image_room);
        TextView textView = convertView.findViewById(R.id.name_room);

        RoomItemSpinner currentItem = getItem(position);

        if (currentItem != null){
//            imageView.setImageResource(currentItem.getRoomImage());
            textView.setText(currentItem.getRoomName());
        }

        return convertView;
    }
}
