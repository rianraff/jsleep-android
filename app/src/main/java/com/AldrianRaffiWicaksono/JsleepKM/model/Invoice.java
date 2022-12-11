package com.AldrianRaffiWicaksono.JsleepKM.model;

public class Invoice extends Serializable
{
    public int buyerId;
    public int renterId;
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
