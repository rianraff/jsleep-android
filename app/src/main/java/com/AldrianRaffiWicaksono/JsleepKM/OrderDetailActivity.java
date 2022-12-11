package com.AldrianRaffiWicaksono.JsleepKM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AldrianRaffiWicaksono.JsleepKM.model.Invoice;
import com.AldrianRaffiWicaksono.JsleepKM.model.Payment;
import com.AldrianRaffiWicaksono.JsleepKM.request.BaseApiService;
import com.AldrianRaffiWicaksono.JsleepKM.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    TextView buyerId, from,to,status;
    Payment payment;
    Button accept,cancel;
    ImageView backOrderDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        mApiService = UtilsApi.getApiService();
        mContext = this;

        backOrderDetail = findViewById(R.id.backOrderDetail);

        payment = RenterOrderListActivity.orderListData.get(RenterOrderListActivity.orderIndex);
        buyerId = findViewById(R.id.orderdetail_filltextviewidbuyer);
        from = findViewById(R.id.orderdetail_filltextviewfromdate);
        to = findViewById(R.id.orderdetail_filltextviewtodate);
        status = findViewById(R.id.orderdetail_filltextviewstatus);
        accept = findViewById(R.id.orderdetail_buttonaccept);
        cancel = findViewById(R.id.orderdetail_buttoncancel);

        buyerId.setText(String.valueOf(payment.buyerId));
        from.setText(payment.from.toString());
        to.setText(payment.to.toString());
        status.setText(payment.status.toString());

        backOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(OrderDetailActivity.this, RenterOrderListActivity.class);
                startActivity(move);
            }
        });

        if(payment.status.equals(Invoice.PaymentStatus.WAITING)){
            accept.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.VISIBLE);
        }
        else{
            accept.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
        }

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptOrder(payment.id);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOrder(payment.id);
            }
        });
    }

    protected Boolean acceptOrder(int id) {
        //System.out.println(pageSize);
        mApiService.accept(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if(response.body()){
                        Toast.makeText(mContext, "Accept Order Success", Toast.LENGTH_SHORT).show();
                        Intent move = new Intent(OrderDetailActivity.this,RenterOrderListActivity.class);
                        startActivity(move);
                    }else {
                        Toast.makeText(mContext, "Accept Order Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "Accept Order Failed", Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }

    protected Boolean cancelOrder(int id) {
        //System.out.println(pageSize);
        mApiService.cancel(id).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    if(response.body()){
                        Toast.makeText(mContext, "Cancel Order Success", Toast.LENGTH_SHORT).show();
                        Intent move = new Intent(OrderDetailActivity.this,RenterOrderListActivity.class);
                        startActivity(move);
                    }else {
                        Toast.makeText(mContext, "Cancel Order Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "Cancel Order Failed", Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }


}