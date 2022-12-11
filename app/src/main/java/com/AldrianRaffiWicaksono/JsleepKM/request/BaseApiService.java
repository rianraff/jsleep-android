package com.AldrianRaffiWicaksono.JsleepKM.request;

import com.AldrianRaffiWicaksono.JsleepKM.model.Account;
import com.AldrianRaffiWicaksono.JsleepKM.model.BedType;
import com.AldrianRaffiWicaksono.JsleepKM.model.City;
import com.AldrianRaffiWicaksono.JsleepKM.model.Facility;
import com.AldrianRaffiWicaksono.JsleepKM.model.Payment;
import com.AldrianRaffiWicaksono.JsleepKM.model.Renter;
import com.AldrianRaffiWicaksono.JsleepKM.model.Room;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

public interface BaseApiService {

    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> getLogin(
            @Query("email") String email,
            @Query("password") String password);

    @GET("room/{id}")
    Call<Room> getRoom (
            @Path("id") int id);

    @POST("account/register")
    Call<Account> getRegister(
            @Query("name") String username,
            @Query("email") String email,
            @Query("password") String password);

    @POST("account/{id}/registerRenter")
    Call<Renter> renter(@Path("id") int id,
                        @Query("username") String username,
                        @Query("address") String address,
                        @Query("phoneNumber") String phoneNumber);

    @POST("account/{id}/topUp")
    Call<Boolean> topUp(@Path("id") int id,
                        @Query("balance") double balance);

    @POST("room/create")
    Call<Room> room(@Query("accountId") int accountId,
                    @Query("name") String name,
                    @Query("size") int size,
                    @Query("price") int price,
                    @Query("facility") ArrayList<Facility> facility,
                    @Query("city") City city,
                    @Query("address") String address,
                    @Query("bedType") BedType bedType);

    @GET("room/getAllRoom")
    Call<List<Room>> getAllRoom(@Query("page") int page,
                                @Query("pageSize") int pageSize);

    /**
     * A method that calls the API to create a payment.
     * @param buyerId the buyer's id
     * @param renterId the renter's id
     * @param roomId the room's id
     * @param from the start date
     * @param to the end date
     * @return a call to the API
     */
    @POST("payment/create")
    Call<Payment> createPayment(@Query("buyerId") int buyerId,
                                @Query("renterId") int renterId,
                                @Query("roomId") int roomId,
                                @Query("from") String from,
                                @Query("to") String to);

    @POST("payment/{id}/accept")
    Call<Boolean> accept(@Path("id") int id);

    @POST("payment/{id}/cancel")
    Call<Boolean> cancel(@Path("id") int id);

    @GET("payment/getOrderForRenter")
    Call<List<Payment>> getOrderForRenter(@Query("renterId") int renterId,
                                          @Query("page") int page,
                                          @Query("pageSize") int pageSize);



}

