package com.AldrianRaffiWicaksono.JsleepKM.model;


import com.AldrianRaffiWicaksono.JsleepKM.model.Serializable;
import com.AldrianRaffiWicaksono.JsleepKM.model.Renter;

/**
 * A child class from Serializable.
 * use to store user's account data
 *
 * @author (Aldrian)
 * @version (27 September 2022)
 */
public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public double balance;
    public Renter renter;

    @Override
    public String toString(){
        return "Account{" +
                "balance=" + balance +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", renter=" + renter +
                '}';
    }
}