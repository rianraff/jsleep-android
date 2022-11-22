package com.AldrianRaffiWicaksono.JsleepKM.request;

import com.AldrianRaffiWicaksono.JsleepKM.model.Account;
import com.AldrianRaffiWicaksono.JsleepKM.model.Room;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {

    @GET("account/{id}")
    Call<Account> getAccount (@Path("id") int id);

    @POST("account/login")
    Call<Account> getLogin(@Query("email") String email, @Query("password") String password);

    @POST("account/register")
    Call<Account> getRegister(@Query("name") String username, @Query("email") String email, @Query("password") String password);

}

