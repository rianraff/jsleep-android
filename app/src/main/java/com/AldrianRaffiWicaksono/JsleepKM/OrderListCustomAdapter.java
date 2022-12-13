package com.AldrianRaffiWicaksono.JsleepKM;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.AldrianRaffiWicaksono.JsleepKM.model.Payment;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * This class is an adapter used to display a list of orders in a ListView.
 *
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 */
public class OrderListCustomAdapter extends ArrayAdapter<Payment> {

    /**
     * This class is an adapter used to display a list of orders in a ListView.
     *
     * @author Aldrian Raffi Wicaksono
     * @version 1.0
     */
    public OrderListCustomAdapter(@NonNull Context context, ArrayList<Payment> order) {
        super(context, 0, order);
    }

    /**
     * This method is used to create the view for an item in the list of orders.
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.order_list_custom_layout,parent,false);
        }

        Payment orderList = getItem(position);

        TextView date = currentItemView.findViewById(R.id.payment_date);
        TextView status = currentItemView.findViewById(R.id.payment_status);

        String dateText = DateFormat.getDateInstance().format(orderList.to) + " - " + DateFormat.getDateInstance().format(orderList.from);
        date.setText(dateText);

        String statusText = "Status: " + orderList.status;
        status.setText(statusText);


        return currentItemView;
    }


}