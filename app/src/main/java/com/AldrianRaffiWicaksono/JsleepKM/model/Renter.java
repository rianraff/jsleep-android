package com.AldrianRaffiWicaksono.JsleepKM.model;

import com.AldrianRaffiWicaksono.JsleepKM.model.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static final String REGEX_NAME = "^[A-Z]{1}\\w{4,20}$";
    public static final String REGEX_PHONE = "^\\d{9,12}$";

}
