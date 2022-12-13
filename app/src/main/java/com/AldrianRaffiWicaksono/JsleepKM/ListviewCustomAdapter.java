package com.AldrianRaffiWicaksono.JsleepKM;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.AldrianRaffiWicaksono.JsleepKM.model.Room;

import java.util.ArrayList;

/**
 * The {@code ListviewCustomAdapter} class provides the UX for the list of rooms.
 *
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 */
public class ListviewCustomAdapter extends ArrayAdapter<Room> {

    /**
     * Constructor for OrderListAdapter.
     *
     * @param context The context in which the adapter is being used.
     * @param rooms The list of rooms to be displayed.
     */
    public ListviewCustomAdapter(@NonNull Context context, ArrayList<Room> rooms) {
        super(context, 0, rooms);
    }


    /**
     * This method is used to create the view for an item in the list of rooms.
     *
     * @param position The position of the item in the list.
     * @param convertView The view to be converted.
     * @param parent The parent view group.
     * @return The view for the item at the specified position.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View currentItemView = convertView;
        if(currentItemView == null){
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_custom_layout,parent,false);
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