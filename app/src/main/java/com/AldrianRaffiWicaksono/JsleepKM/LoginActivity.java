package com.AldrianRaffiWicaksono.JsleepKM;

import static android.widget.Toast.*;

import androidx.appcompat.app.AppCompatActivity;
import com.AldrianRaffiWicaksono.JsleepKM.request.*;
import com.AldrianRaffiWicaksono.JsleepKM.model.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLOutput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The {@code LoginActivity} class provides the UX for logging in.
 *
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * A {@link BaseApiService} instance for making API requests.
     */
    BaseApiService mApiService;

    /**
     * The {@link Context} of the activity.
     */
    Context mContext;

    /**
     * The {@link EditText} where the user can enter their email and password.
     */
    EditText email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_login);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        TextView register = findViewById(R.id.registerButton);
        email = findViewById(R.id.emailEdit);
        password = findViewById(R.id.passwordEdit);
        Button mainActivity = findViewById(R.id.login);

        mainActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Account account = requestLogin();
            }
        });


        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent move = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(move);
            }
        });

    }

    protected Account requestAccount(){
        mApiService.getAccount(0).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                    Account account;
                    account = response.body();
                    System.out.println(account.toString());
                    Intent move = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(mContext, "no Account id = 0", LENGTH_SHORT).show();
            }
        });
        return null;
    }

    /**
     * This function is used to request login to the server
     *
     * @return Account object
     * @see Account
     */
    protected Account requestLogin(){
        mApiService.login(email.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){

                    MainActivity.cookies = response.body();

                    Intent go = new Intent(LoginActivity.this,
                            MainActivity.class);

                    startActivity(go);
                    Toast.makeText(mContext, "Login Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t){
                System.out.println(t.toString());

                Toast.makeText(mContext, "Invalid email or password",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }


}