package com.example.anuraag.todot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;

    ArrayList imageId,Titles,event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        adapter = new CustomAdapter(this,imageId,Titles,event);

    }

    public void add(View view) {

        Intent i = new Intent(this,MakeToDo.class);
        i.putExtra("request","blank");
        startActivity(i);

    }
}
