package com.AldrianRaffiWicaksono.JsleepKM.model;
import com.AldrianRaffiWicaksono.JsleepKM.model.Serializable;

import java.util.ArrayList;
import java.util.Date;


/**
 * A child class from Serializable.
 * use to store user's room data
 *
 * @author (Aldrian)
 * @version (27 September 2022)
 */
public class Room extends Serializable
{

    public int size;
    public Price price;
    public ArrayList<Facility> facility = new ArrayList<>();
    public String name;

    public BedType bedType;
    public City city;
    public String address;
    public int accountId;

    public ArrayList<Date> booked;

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