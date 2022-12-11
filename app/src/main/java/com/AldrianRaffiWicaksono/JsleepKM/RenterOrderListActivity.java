package com.AldrianRaffiWicaksono.JsleepKM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.AldrianRaffiWicaksono.JsleepKM.model.Payment;
import com.AldrianRaffiWicaksono.JsleepKM.request.BaseApiService;
import com.AldrianRaffiWicaksono.JsleepKM.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RenterOrderListActivity extends AppCompatActivity {

    ListView orderList;
    public static int orderIndex;
    public static ArrayList<Payment> orderListData;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_order_list);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        orderList = findViewById(R.id.seeorder_ListView);
        orderList.setOnItemClickListener(this::onItemClick);
        getOrderForRenter(MainActivity.loginToMain.id);




    }

    protected void getOrderForRenter(int renterId){
        mApiService.getOrderForRenter(renterId,0,10).enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                if(response.isSuccessful()){
                    List<Payment> orderlist = response.body();
                    assert orderlist != null;
                    orderListData = new ArrayList<Payment>(orderlist);
                    Toast.makeText(mContext, "Get Order Success", Toast.LENGTH_SHORT).show();
                    OrderListAdapter adapter = new OrderListAdapter(mContext,orderListData);
                    orderList.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Toast.makeText(mContext, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void onItemClick (AdapterView<?> l, View v, int position, long id){
        System.out.println("onItemClick Success");
        Log.i("ListView", "You clicked Item np : " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        orderIndex = position;
        System.out.println("clicked");
        intent.setClass(mContext, OrderDetailActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);
    }


}