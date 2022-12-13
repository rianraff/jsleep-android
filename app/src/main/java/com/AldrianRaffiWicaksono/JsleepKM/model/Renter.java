package com.AldrianRaffiWicaksono.JsleepKM.model;

/**
 * The `Renter` class represents a person who rents a room.
 *
 * @author Aldrian Raffi Wicaksono
 * @see Serializable
 */
public class Renter extends Serializable {
    public String phoneNumber;
    public String address;
    public String username;

    public Renter(int id) {
        super(id);
    }
}
