package com.AldrianRaffiWicaksono.JsleepKM;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;

/**
 * The CreatePaymentActivity class is an Android activity that represents a booking session.
 *
 * <p>It displays a calendar and allows the user to select a start and end date for the payment, and then
 * navigates to the `PaymentDetailActivity` to display the payment details.</p>
 * @author Aldrian Raffi Wicaksono
 * @version 1.0
 */
public class CreatePaymentActivity extends AppCompatActivity {
    /**
     * The calendar view used to display the dates for the payment.
     */
    private CalendarView calendarView;

    /**
     * The start end date for the payment, as a string in the format "yyyy-MM-dd".
     */
    public static String enddate;
    public static String startdate;

    /**
     * Button to continue to payment invoice page
     */
    Button continueInvoiceButton;
    ImageView backCreatePayment;
    /**
     * The {@link EditText} where the user can enter the start date and the end date when book a room.
     */
    EditText checkInDate, checkOutDate;

    /**
     * The {@link DatePickerDialog} used to choose the range date for booking a room and for the payment.
     */
    DatePickerDialog datePickerDialogEnd,datePickerDialogStart;


    /**
     The CreatePaymentActivity class represents the view that allows the user to create a new payment.
     It sets the content view to an activity layout and hides the support action bar. It also initializes the
     calendar view and sets its appearance.
     The activity provides two date picker dialogs to let the user select a check-in and check-out date. The chosen
     dates are displayed in two text fields and stored in the startdate and enddate variables, respectively.
     When the user clicks the "Continue to Invoice" button, the activity starts the PaymentInvoiceActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_payment);

        calendarView = findViewById(R.id.pdCalender);
        calendarView.setWeekDayTextAppearance(R.style.CalendarViewDateTextAppearance);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        backCreatePayment = findViewById(R.id.backCreatePayment);

        backCreatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(CreatePaymentActivity.this, DetailRoomActivity.class);
                startActivity(move);
            }
        });


        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialogStart = new DatePickerDialog(CreatePaymentActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        checkInDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        startdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);

        datePickerDialogEnd = new DatePickerDialog(CreatePaymentActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        checkOutDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        enddate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);

        checkInDate = findViewById(R.id.checkInDate);
        checkOutDate = findViewById(R.id.chechOutDate);

        checkInDate.setOnClickListener(v -> {
            datePickerDialogStart.show();
        });

        checkOutDate.setOnClickListener(v -> {
            datePickerDialogEnd.show();
        });

        continueInvoiceButton = findViewById(R.id.paymentdetail_button);

        continueInvoiceButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                startdate = checkInDate.getText().toString();
                enddate = checkOutDate.getText().toString();
                Intent move = new Intent(CreatePaymentActivity.this, PaymentInvoiceActivity.class);
                startActivity(move);
            }
        });
    }
}