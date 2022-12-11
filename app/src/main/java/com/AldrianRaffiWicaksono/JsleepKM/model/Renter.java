package com.AldrianRaffiWicaksono.JsleepKM.model;

import com.AldrianRaffiWicaksono.JsleepKM.model.Serializable;

/**
 * A child class from Serializable.
 * use to store user's personal contact data
 *
 * @author (Aldrian)
 * @version (27 September 2022)
 */
public class Renter extends Serializable
{

    public String phoneNumber;
    public String address = "";
    public String username;

    public Renter(int id) {
        super(id);
    }
}
