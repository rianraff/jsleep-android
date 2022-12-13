package com.AldrianRaffiWicaksono.JsleepKM.model;

import java.util.Calendar;
import java.util.Date;
/**
 * A class representing a payment for booking a room.
 *
 * <p>This class extends the `Invoice` class and adds additional attributes and methods related to the payment,
 * such as the dates of the booking, the room ID, and methods for checking the availability of a room and making a booking.</p>
 *
 * @author Aldrian Raffi Wicaksono
 * @see Invoice
 */
public class Payment extends Invoice
{
    public Date to;
    public Date from;
    private int roomId;

    public Payment(int id) {
        super(id);
    }
}
