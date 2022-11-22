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
    public Facility facility;
    public String name;

    public BedType bedType;
    public City city;
    public String address;
    public int accountId;

    public ArrayList<Date> booked = new ArrayList<>();

}