package com.AldrianRaffiWicaksono.JsleepKM;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.AldrianRaffiWicaksono.JsleepKM.R;
import com.AldrianRaffiWicaksono.JsleepKM.model.Room;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<Room> {

    public CustomListAdapter(@NonNull Context context, ArrayList<Room> rooms) {
        super(context, 0, rooms);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View currentItemView = convertView;
        if(currentItemView == null){
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_layout,parent,false);
        }

        Room currentRoom = getItem(position);
        System.out.println(currentRoom.address);

        TextView name = currentItemView.findViewById(R.id.room_name);
        name.setText(currentRoom.name);

        TextView address = currentItemView.findViewById(R.id.room_address);
        address.setText(currentRoom.address);

        return currentItemView;
    }


}