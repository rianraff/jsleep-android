package com.AldrianRaffiWicaksono.JsleepKM;

import com.AldrianRaffiWicaksono.JsleepKM.model.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;

import java.io.*;
import java.lang.*;
import java.util.*;

import android.view.*;
import android.widget.*;
import android.content.*;
import android.os.Bundle;

import com.google.gson.Gson;

public class MainActivity<v> extends AppCompatActivity {

    public static Account loginToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        InputStream filepath = null;
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Room> roomList  = new ArrayList<Room>();
        try {
            filepath = getAssets().open("randomRoomList.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
            Room[] acc = gson.fromJson(reader, Room[].class);
            Collections.addAll(roomList, acc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Room room : roomList) {
            name.add(room.name);
        }
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name);
        ListView view = (ListView) findViewById(R.id.list_view);
        view.setAdapter(roomAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.person_button) {
            Intent move = new Intent(MainActivity.this, AboutMe.class);
            startActivity(move);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}