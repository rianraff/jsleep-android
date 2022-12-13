package com.AldrianRaffiWicaksono.JsleepKM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.AldrianRaffiWicaksono.JsleepKM.model.BedType;
import com.AldrianRaffiWicaksono.JsleepKM.model.City;
import com.AldrianRaffiWicaksono.JsleepKM.model.Facility;
import com.AldrianRaffiWicaksono.JsleepKM.model.Room;
import com.AldrianRaffiWicaksono.JsleepKM.request.BaseApiService;
import com.AldrianRaffiWicaksono.JsleepKM.request.UtilsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The {@code CreateRoomActivity} class provides the UX for creating a new room.
 *
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 *
 */
public class CreateRoomActivity extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;
    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * The {@link RadioButton} that the renter can choose from the room's facility- ac, refrigerator, bath tub,
     * balcony, restaurant, pool, and fitness center- is provided with the room.
     */
    RadioButton ac, refrig, wifi, bathub, balcony, restaurant, pool, fitness;
    Spinner city, bed;
    EditText nameInput, priceInput, sizeInput, addressInput;
    Button createRoom, cancelCreateRoom;

    /**
     * Arraylist of facilities to store the facilities provided.
     */
    ArrayList<Facility> facility = new ArrayList<Facility>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mApiService = UtilsApi.getApiService();
        mContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);


        //button
        createRoom = findViewById(R.id.createRoomButton);
        cancelCreateRoom = findViewById(R.id.cancelCreateRoomButton);

        //spinner
        city = findViewById(R.id.citySpinner);
        bed = findViewById(R.id.bedSpinner);

        //facility checkbox
        ac = findViewById(R.id.ac);
        refrig = findViewById(R.id.refrigerator);
        wifi = findViewById(R.id.wifi);
        bathub = findViewById(R.id.bathub);
        balcony = findViewById(R.id.balcony);
        restaurant = findViewById(R.id.restaurant);
        pool = findViewById(R.id.pool);
        fitness = findViewById(R.id.fitness);

        //text room details
        nameInput = findViewById(R.id.addName);
        priceInput = findViewById(R.id.addPrice);
        sizeInput = findViewById(R.id.addSize);
        addressInput = findViewById(R.id.addAddress);

        bed.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
        city.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));

        createRoom.setOnClickListener(v -> {

            if (ac.isChecked()) {
                facility.add(Facility.AC);
            }
            if (refrig.isChecked()) {
                facility.add(Facility.Refrigerator);
            }
            if (wifi.isChecked()) {
                facility.add(Facility.WiFi);
            }
            if (bathub.isChecked()) {
                facility.add(Facility.Bathtub);
            }
            if (balcony.isChecked()) {
                facility.add(Facility.Balcony);
            }
            if (restaurant.isChecked()) {
                facility.add(Facility.Restaurant);
            }
            if (pool.isChecked()) {
                facility.add(Facility.SwimmingPool);
            }
            if (fitness.isChecked()) {
                facility.add(Facility.FitnessCenter);
            }
            String bedType = bed.getSelectedItem().toString();
            String cityStr = city.getSelectedItem().toString();

            BedType bedtype = BedType.valueOf(bedType);
            City city = City.valueOf(cityStr);

            int size = Integer.parseInt(sizeInput.getText().toString());
            int price = Integer.parseInt(priceInput.getText().toString());

            requestRoom(MainActivity.cookies.id, nameInput.getText().toString(), size,price, facility, city, addressInput.getText().toString(), bedtype);
        });
    }


    /**
     * This function is used to to request a new room.
     *
     * @param id  the id
     * @param name  the name of the room
     * @param size  the size of the room
     * @param price  the price of the room
     * @param facility the facilities provided with the room
     * @param city  the city the room located to
     * @param address   the address the room located to
     * @param bedType the bed type the room has
     * @return Room
     */
    protected Room requestRoom(int id, String name, int size, int price, ArrayList<Facility> facility, City city, String address, BedType bedType) {
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Size: " + size);
        System.out.println("facility: " + facility);
        System.out.println("Address: " + address);
        System.out.println("Bed: " + bedType);
        mApiService.room(id, name, size, price, facility, city, address, bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Berhasil buat room", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Toast.makeText(mContext, "gagal buat room", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}