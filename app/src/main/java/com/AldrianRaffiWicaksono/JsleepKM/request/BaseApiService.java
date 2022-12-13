package com.AldrianRaffiWicaksono.JsleepKM.request;

import com.AldrianRaffiWicaksono.JsleepKM.model.Account;
import com.AldrianRaffiWicaksono.JsleepKM.model.BedType;
import com.AldrianRaffiWicaksono.JsleepKM.model.City;
import com.AldrianRaffiWicaksono.JsleepKM.model.Facility;
import com.AldrianRaffiWicaksono.JsleepKM.model.Payment;
import com.AldrianRaffiWicaksono.JsleepKM.model.Renter;
import com.AldrianRaffiWicaksono.JsleepKM.model.Room;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * The {@code BaseApiService} interface is the base API service for the JSleep Android app.
 * <p>It defines the API endpoints for all data operations.</p>
 *
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 */
public interface BaseApiService {

    /**
     * The {@code BaseApiService} interface is the base API service for the JSleep Android app.
     * <p>It defines the API endpoints for all data operations.</p>
     *
     * @author Amrita Deviayu Tunjungbiru (2106636584)
     * @version 1.0
     */
    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    /**
     * The {@code BaseApiService} interface is the base API service for the JSleep Android app.
     * <p>It defines the API endpoints for all data operations.</p>
     *
     * @author Amrita Deviayu Tunjungbiru (2106636584)
     * @version 1.0
     */
    @POST("account/login")
    Call<Account> login (@Query("email") String email, @Query("password") String Password);


    @GET("room/{id}")
    Call<Room> getRoom (@Path("id") int id);

    /**
     * Registers a new user with the specified name, email, and password.
     *
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @return a {@link Call} object representing the API call
     */
    @POST("account/register")
    Call<Account> register  (@Query("name") String name,
                             @Query("email") String email,
                             @Query("password") String password);

    /**
     * Registers a new renter for the account with the specified id.
     *
     * @param id the id of the account to register the renter for
     * @param username the username of the renter
     * @param address the address of the renter
     * @param phoneNumber the phone number of the renter
     * @return a {@link Call} object representing the API call
     */
    @POST("account/{id}/registerRenter")
    Call<Renter> renter(@Path("id") int id,
                        @Query("username") String username,
                        @Query("address") String address,
                        @Query("phoneNumber") String phoneNumber);

    /**
     * Adds the specified balance to the account with the specified id.
     *
     * @param id the id of the account to add balance to
     * @param balance the amount of balance to add
     * @return a {@link Call} object representing the API call
     */
    @POST("account/{id}/topUp")
    Call<Boolean> topUp(@Path("id") int id,
                        @Query("balance") double balance);

    /**
     * Creates a new room in the system.
     *
     * @param accountId the id of the account that will own the new room
     * @param name the name of the new room
     * @param size the size of the new room
     * @param price the price of the new room
     * @param facility the facilities available in the new room
     * @param city the city where the new room is located
     * @param address the address of the new room
     * @param bedType the type of bed in the new room
     * @return a {@link Call} that can be enqueued to make the API request
     */
    @POST("room/create")
    Call<Room> room(@Query("accountId") int accountId,
                    @Query("name") String name,
                    @Query("size") int size,
                    @Query("price") int price,
                    @Query("facility") ArrayList<Facility> facility,
                    @Query("city") City city,
                    @Query("address") String address,
                    @Query("bedType") BedType bedType);


    /**
     * Gets a paginated list of all rooms in the system.
     *
     * @param page the page number
     * @param pageSize the number of items per page
     * @return a {@link Call} that can be enqueued to make the API request
     */
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

    /**
     * Accepts the payment with the specified id.
     *
     * @param id the id of the payment to accept
     * @return {@code true} if the payment was accepted, {@code false} otherwise
     */
    @POST("payment/{id}/accept")
    Call<Boolean> accept(@Path("id") int id);

    /**
     * Cancels the payment with the specified id.
     *
     * @param id the id of the payment to cancel
     * @return {@code true} if the payment was cancelled, {@code false} otherwise
     */
    @POST("payment/{id}/cancel")
    Call<Boolean> cancel(@Path("id") int id);

    /**
     * Gets a paginated list of all payments in the system.
     * @param renterId the renter's id
     * @param page the page number
     * @param pageSize the number of items per page
     * @return
     */
    @GET("payment/getOrderForRenter")
    Call<List<Payment>> getOrderForRenter(@Query("renterId") int renterId,
                                          @Query("page") int page,
                                          @Query("pageSize") int pageSize);

}