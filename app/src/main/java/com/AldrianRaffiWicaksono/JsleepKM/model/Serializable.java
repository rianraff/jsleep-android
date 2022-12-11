package com.AldrianRaffiWicaksono.JsleepKM.model;

/**
 * A parent class for Invoice, Renter, Account, Voucher, Room
 * so that all of those child class have id variable.
 *
 * @author (Aldrian)
 * @version (27 September 2022)
 */
public class Serializable  {
    public final int id;

    protected Serializable(int id) {
        this.id = id;
    }
}