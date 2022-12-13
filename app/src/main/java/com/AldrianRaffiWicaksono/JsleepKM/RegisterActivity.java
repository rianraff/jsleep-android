package com.AldrianRaffiWicaksono.JsleepKM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AldrianRaffiWicaksono.JsleepKM.model.Account;
import com.AldrianRaffiWicaksono.JsleepKM.request.BaseApiService;
import com.AldrianRaffiWicaksono.JsleepKM.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * The RegisterActivity class is an Android activity that represents the register session to JSleep.
 *
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 */
public class RegisterActivity extends AppCompatActivity {
    BaseApiService mApiService;
    EditText name, email,  password;
    Context mContext;
    ImageView bgRegist;
    TextView regtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_register);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        name = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
//        bgRegist = findViewById(R.id.bg_register);

        Button mainActivity = findViewById(R.id.registerButton2);

        mainActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Account account = requestRegister();
            }
        });
    }

    /**
     * This function is used to request register to the server
     *
     * @return Account object
     * @see Account
     */
    protected Account requestRegister(){
        mApiService.register(name.getText().toString(), email.getText().toString(), password.getText().toString()).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){

                    MainActivity.cookies = response.body();

                    Intent go = new Intent(RegisterActivity.this,
                            LoginActivity.class);

                    startActivity(go);
                    Toast.makeText(mContext, "Register Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t){
                System.out.println(t.toString());

                Toast.makeText(mContext, "Account Already Exist",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }




}