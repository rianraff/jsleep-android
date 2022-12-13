package com.AldrianRaffiWicaksono.JsleepKM.model;

/**
 * A class that represents an invoice for a rental transaction.
 *
 * <p>This class extends the `Serializable` class and adds fields for the IDs of the buyer and renter, the payment status, and the rating of the rental.
 * It also provides a `print()` method that returns a string representation of the invoice.</p>
 *
 * @author Aldrian Raffi Wicaksono
 * @see Serializable
 */
public class Invoice extends Serializable
{
    public int buyerId;
    public int renterId;
    //public Date time;
    public PaymentStatus status;
    public RoomRating rating;

    public Invoice(int id) {
        super(id);
    }

    public enum RoomRating{
        NONE,BAD,NEUTRAL,GOOD
    }

    public enum PaymentStatus{
        FAILED,WAITING,SUCCESS
    }


}
