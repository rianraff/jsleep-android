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

public class CreatePaymentActivity extends AppCompatActivity {
    private CalendarView calendarView;
    public static String enddate;
    public static String startdate;
    Button paymentdetail_button;
    ImageView paymentdetail_image, backCreatePayment;
    EditText paymentdetail_edittext_start, paymentdetail_edittext_end;
    DatePickerDialog datePickerDialogEnd,datePickerDialogStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_payment);
        calendarView = findViewById(R.id.paymentdetail_calendar);
        calendarView.setWeekDayTextAppearance(R.style.CalendarViewDateTextAppearance);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

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
                        paymentdetail_edittext_start.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        startdate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);

        datePickerDialogEnd = new DatePickerDialog(CreatePaymentActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        paymentdetail_edittext_end.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        enddate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    }
                }, mYear, mMonth, mDay);

        paymentdetail_edittext_start = findViewById(R.id.paymentdetail_edittext_start);
        paymentdetail_edittext_end = findViewById(R.id.paymentdetail_edittext_end);

        paymentdetail_edittext_start.setOnClickListener(v -> {
            datePickerDialogStart.show();
        });

        paymentdetail_edittext_end.setOnClickListener(v -> {
            datePickerDialogEnd.show();
        });


        paymentdetail_button = findViewById(R.id.paymentdetail_button);
        paymentdetail_image = findViewById(R.id.paymentdetail_title_icon);
        paymentdetail_button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                startdate = paymentdetail_edittext_start.getText().toString();
                enddate = paymentdetail_edittext_end.getText().toString();
                Intent move = new Intent(CreatePaymentActivity.this,PaymentInvoiceActivity.class);
                startActivity(move);
            }
        });
//
//        // Set the text color of the CalendarView
//        calendarView.setDateTextAppearance(R.style.CalendarViewDateTextAppearance);
//
//        // Create a custom WeekDayFormatter
//        WeekDayFormatter weekDayFormatter = new WeekDayFormatter() {
//            @Override
//            public CharSequence format(int dayOfWeek) {
//                // Use the text color specified in the CalendarViewDateTextAppearance style
//                SpannableString spannableString = new SpannableString(String.valueOf(dayOfWeek));
//                spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.calendar_view_date_text_color)),
//                        0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                return spannableString;
//            }
//        };
//
//
//        // Set the WeekDayFormatter on the CalendarView
//        calendarView.weekDayFormatter = weekDayFormatter
    }
}