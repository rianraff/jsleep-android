package com.AldrianRaffiWicaksono.JsleepKM.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * The `Room` class represents a room in a hotel.
 *
 * <p>This class extends the `Serializable` class and adds additional attributes and methods related to the room,
 * such as the room number, the room type, and methods for checking the availability of a room and making a booking.</p>
 *
 * @author Aldrian Raffi Wicaksono
 * @see Serializable
 */
public class Room extends Serializable {
    public int size;
    public String name;
    public ArrayList<Facility> facility = new ArrayList<>();
    public Price price;
    public String address;
    public BedType bedType;
    public City city;
    public ArrayList<Date> booked;
    public int accountId;


    public Room(int id, int accountId, String name, int size, Price price,
                ArrayList<Facility> facility, City city, String address, BedType bedType) {
        super(id);
        this.accountId = accountId;
        this.city = city;
        this.address = address;
        this.name = name;
        this.size = size;
        this.price = price;
        this.facility.addAll(facility);
        this.booked = new ArrayList<Date>();
        this.bedType = bedType.SINGLE;
    }

}
