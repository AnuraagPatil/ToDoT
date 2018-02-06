package com.example.anuraag.todot;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MakeToDo extends AppCompatActivity {

    EditText editdate,edittime;
    TextView textremainder;
    Calendar calendar;
    com.rey.material.widget.Switch aSwitch;
    static int year,day,hour,minute;
    static String month;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_to_do);

        editdate = findViewById(R.id.edtDate);
        edittime = findViewById(R.id.edtTime);

        aSwitch = findViewById(R.id.alertswitch);

        //hiding remainder message
        textremainder = findViewById(R.id.txtreminder);
        textremainder.setVisibility(View.GONE);

        //get request
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String request = b.getString("request");

        //Toast.makeText(this, ""+request, Toast.LENGTH_SHORT).show();

        if (request.equals("blank")){

            //making a new todo task

            SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");


            calendar = Calendar.getInstance();

            //get all the live values
            year = calendar.get(Calendar.YEAR);
            month = monthFormat.format(calendar.getTime());
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);

            set(year,month,day,hour,minute);

            //Toast.makeText(this, "Blank ready", Toast.LENGTH_SHORT).show();

        }else{

        }

        //Setting action for remainder switch
        aSwitch.setOnCheckedChangeListener(new com.rey.material.widget.Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(com.rey.material.widget.Switch buttonView, boolean isChecked) {

                if(isChecked){
                    textremainder.setVisibility(View.VISIBLE);
                    textremainder.setText("Remainder set for "+day+"th "+month+","+year+" @"+hour+":"+minute);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
                    Date date1;

                    try {
                        date1 = simpleDateFormat.parse(day+"/"+month+"/"+year+" "+hour+":"+minute+":00");

                        Toast.makeText(MakeToDo.this, ""+date1.getMinutes(), Toast.LENGTH_SHORT).show();

                        long difference = Math.abs(date1.getTime() - System.currentTimeMillis());
                        //set alarm
                        Alarm.setAlarm(getApplicationContext(),difference);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }else {
                    textremainder.setVisibility(View.GONE);

                    Alarm.cancelAlarm(getApplicationContext());

                    set(year, month, day, hour, minute);

                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addDate(View view) {

        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(editdate.getWindowToken(),0);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Get date from user

                DateFormatSymbols symbols = new DateFormatSymbols();

                MakeToDo.year = year;
                MakeToDo.month = symbols.getMonths()[(month)];
                MakeToDo.day = dayOfMonth;


                //set current time to edittext
                set(year, MakeToDo.month, day, hour, minute);

            }
        }, year,calendar.get(Calendar.MONTH),day);

        dialog.show();

    }

    public void addTime(View view) {
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Get time from user
                hour = hourOfDay;
                MakeToDo.minute = minute;

                //set current time to edittext
                set(year, MakeToDo.month, day, hour, minute);

            }
        },hour,minute,false);

        dialog.show();
    }

    public void add(View view) {



    }

    public void set(int year, String month, int day, int hour, int minute){
        
        //set 
        editdate.setText(day +"th "+ month.substring(0,3)+","+ year);
        edittime.setText(hour+":"+minute);
    }

/*
    public void show(View view) {
        if(aSwitch.isEnabled()){
            textremainder.setVisibility(View.VISIBLE);
        }else {
            textremainder.setVisibility(View.GONE);
        }
    }*/
}
