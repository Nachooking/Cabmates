package com.example.minorpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i8= new Intent(Main6Activity.this, Main7Activity.class);
                startActivity(i8);
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i13= new Intent(Main6Activity.this, Main2Activity.class);
                startActivity(i13);
                Toast.makeText(Main6Activity.this,"Logged out",Toast.LENGTH_SHORT).show();
            }
        });

        Spinner myspin = (Spinner) findViewById(R.id.spinner);
        Spinner myspin2 = (Spinner) findViewById(R.id.spinner2);
        Spinner myspin3 = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<String> myad = new ArrayAdapter<String>(Main6Activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Fromwhere));
        myad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspin.setAdapter(myad);

        ArrayAdapter<String> myad2 = new ArrayAdapter<String>(Main6Activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Timeslot));
        myad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspin2.setAdapter(myad2);

        ArrayAdapter<String> myad3 = new ArrayAdapter<String>(Main6Activity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.need));
        myad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspin3.setAdapter(myad3);
    }

}
