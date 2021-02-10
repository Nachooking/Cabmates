package com.example.minorpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main7Activity extends AppCompatActivity {

    private Button b1;
    public String uid;
    TextView editName,editPhone,editYear,editBatch;
    FirebaseUser currentuser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        editName=findViewById(R.id.editName);
        editPhone=findViewById(R.id.editPhone);
        editBatch=findViewById(R.id.editBatch);
        editYear=findViewById(R.id.editYear);
        currentuser= FirebaseAuth.getInstance().getCurrentUser();
        uid=currentuser.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("cabmatesuser").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot Mainsnapshot) {

                    String txt=Mainsnapshot.child("name").getValue().toString();
                    editName.setText(txt);
                    txt=Mainsnapshot.child("phoneno").getValue().toString();
                    editPhone.setText(txt);
                    txt=Mainsnapshot.child("batch").getValue().toString();
                    editBatch.setText(txt);
                    txt=Mainsnapshot.child("year").getValue().toString();
                    editYear.setText(txt);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Main7Activity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab3 = findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i14= new Intent(Main7Activity.this, Main2Activity.class);
                Toast.makeText(Main7Activity.this,"Logged out",Toast.LENGTH_SHORT).show();
                startActivity(i14);
            }
        });

        b1 = findViewById(R.id.editbutton);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i10= new Intent(Main7Activity.this, Main8Activity.class);
                startActivity(i10);
            }
        });

    }


}

