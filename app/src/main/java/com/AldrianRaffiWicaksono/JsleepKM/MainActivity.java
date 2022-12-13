package com.AldrianRaffiWicaksono.JsleepKM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.AldrianRaffiWicaksono.JsleepKM.model.Account;
import com.AldrianRaffiWicaksono.JsleepKM.model.Room;
import com.AldrianRaffiWicaksono.JsleepKM.request.BaseApiService;
import com.AldrianRaffiWicaksono.JsleepKM.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The {@code MainActivity} class provides the UX for the main screen.
 *
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    public static Account cookies;

    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * The `ListView` used to display the list of rooms.
     */
    ListView listView;

    /**
     * The list of rooms to be displayed.
     */
    List<Room> activitylist;
    TextView home, search, profile, historyButton;
    public static ArrayList<Room> listRoom;

    /**
     * The index of the selected room.
     */
    public static int roomIndex;

    /**
     * The current page of rooms being displayed.
     */
    int current;
    Button next, prev;
    int currentPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        current = 0;
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);

        mApiService = UtilsApi.getApiService();
        mContext = this;
        activitylist = getRoomList(current);

        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);
        listView = (ListView) findViewById(R.id.listviewer);
        listView.setOnItemClickListener(this::onItemClick);

        home = findViewById(R.id.homeButton);
        search = findViewById(R.id.searchButton);
        profile = findViewById(R.id.accountButton);
        historyButton = findViewById(R.id.historyButton);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent (MainActivity.this, AboutMeActivity.class);
                startActivity(move);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent (MainActivity.this, CreateRoomActivity.class);
                startActivity(move);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current += 1;
                activitylist = getRoomList(current);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current == 0){
                    Toast.makeText(mContext, "You are at the first page", Toast.LENGTH_SHORT).show();
                }
                else{
                    current -= 1;
                    activitylist = getRoomList(current);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_button:
                Intent move = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(move);
                return true;
            case R.id.add_button:
                Intent go = new Intent(MainActivity.this, CreateRoomActivity.class);
                startActivity(go);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem register = menu.findItem(R.id.add_button);
        if(cookies.renter == null){
            register.setVisible(false);
        }
        else {
            register.setVisible(true);
        }
        return true;
    }




    /**
     * This Function is used to get the list of room
     * @param page is the page number
     * @return the list of room
     * @see Room
     * @author Bisma Alif Alghifari
     */

    protected List<Room> getRoomList(int page) {
        //System.out.println(pageSize);
        mApiService.getAllRoom(page, 5).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    activitylist = response.body();
                    assert activitylist != null;
                    listRoom = new ArrayList<Room>(activitylist);
                    ListviewCustomAdapter adapter = new ListviewCustomAdapter(mContext, listRoom);
                    listView.setAdapter(adapter);
                    System.out.println("Get Room Success");


                }
            }
            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "get room failed", Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }

    /**
     *
     * @param v is the view
     * @param position is the position of the room
     * @param id is the id of the room
     * @author Bisma Alif Alghifari
     */

    public void onItemClick (AdapterView<?> l, View v, int position, long id){
        System.out.println("onItemClick Success");
        Log.i("ListView", "You clicked Item np : " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent(this, DetailRoomActivity.class);
        roomIndex = position;
        System.out.println("clicked");
        intent.setClass(mContext, DetailRoomActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);
    }

}