package com.example.minorpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Year;

public class Main8Activity extends AppCompatActivity {

    private String name1,batch1,year1,uid,email1;
    private EditText e1,e2,e3,e4;
    private long phone1;
    private Button b1,b2;
    private String name,phoneno,batch,year;
    DatabaseReference Reff;
    cabmatesuser users;
    FirebaseAuth Mauth;
    FirebaseUser user;
    FirebaseUser currentuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        b1 = findViewById(R.id.subbutton);
        b2 = findViewById(R.id.backbutton);
        e1=findViewById(R.id.editName);
        e2=findViewById(R.id.editPhone);
        e3=findViewById(R.id.editYear);
        e4=findViewById(R.id.editBatch);
        currentuser= FirebaseAuth.getInstance().getCurrentUser();
        uid=currentuser.getUid();
        Reff= FirebaseDatabase.getInstance().getReference().child("cabmatesuser").child(uid);
        FloatingActionButton fab3 = findViewById(R.id.fab4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1=e1.getText().toString().trim();
                phoneno=e2.getText().toString().trim();
                year=e3.getText().toString().trim();
                batch =e4.getText().toString().trim();
                if(TextUtils.isEmpty(name1)==false)
                {

                    Reff.child("name").setValue(name1);
                }
                if(TextUtils.isEmpty(phoneno)==false)
                {
                    Reff.child("phoneno").setValue(phoneno);
                }
                if(TextUtils.isEmpty(batch)==false)
                {
                    Reff.child("batch").setValue(batch);
                }
                if(TextUtils.isEmpty(year)==false)
                {
                    Reff.child("year").setValue(year);
                }
                Toast.makeText(Main8Activity.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                Intent i10= new Intent(Main8Activity.this, Main6Activity.class);
                startActivity(i10);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i10= new Intent(Main8Activity.this, Main6Activity.class);
                startActivity(i10);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i14= new Intent(Main8Activity.this, Main2Activity.class);
                Toast.makeText(Main8Activity.this,"Logged out",Toast.LENGTH_SHORT).show();
                startActivity(i14);
            }
        });

    }
}

