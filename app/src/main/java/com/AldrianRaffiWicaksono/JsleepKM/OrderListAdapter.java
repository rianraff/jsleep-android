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

import java.util.ArrayList;

public class OrderListAdapter extends ArrayAdapter<Payment> {


    public OrderListAdapter(@NonNull Context context, ArrayList<Payment> order) {
        super(context, 0, order);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View currentItemView = convertView;
        if(currentItemView == null){
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.order_list_layout,parent,false);
        }

        Payment currentOrder = getItem(position);

        TextView date = currentItemView.findViewById(R.id.payment_date);
        TextView status = currentItemView.findViewById(R.id.payment_status);

        String dateText = currentOrder.to.toString() + " - " + currentOrder.from.toString();
        date.setText(dateText);

        String statusText = "Status: " + currentOrder.status;
        status.setText(statusText);


        return currentItemView;
    }


}