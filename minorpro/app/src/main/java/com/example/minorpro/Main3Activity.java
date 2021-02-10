package com.example.minorpro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {

    private String uid;
    private String email1, password1,name1,batch1,year1,phone1;
    private Boolean EditTextStatus;
    private EditText e1, e2,e3,e4,e5,e6;
    private TextView t1, t2;
    private Button b2, b3;
    private ImageView image1;
    private ProgressDialog progressDialog;
    DatabaseReference Reff;
    cabmatesuser users;
    private FirebaseAuth Mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        b2 = findViewById(R.id.Verify);
        b3 = findViewById(R.id.idbackbutton10);
        e1 = findViewById(R.id.edittext1);
        e2 = findViewById(R.id.edittext2);
        e3 = findViewById(R.id.edittext3);
        e4 = findViewById(R.id.edittext4);
        e5 = findViewById(R.id.edittext5);
        e6 = findViewById(R.id.edittext6);
        t1 = findViewById(R.id.signuptext1);
        t2 = findViewById(R.id.textview1);
        image1 = findViewById(R.id.imageimage);
        Mauth = FirebaseAuth.getInstance();
        users= new cabmatesuser();
        Reff= FirebaseDatabase.getInstance().getReference().child("cabmatesuser");
        progressDialog = new ProgressDialog(Main3Activity.this);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckEmpty();
                if (EditTextStatus) {
                    progressDialog.setMessage("PLEASE WAIT");
                    progressDialog.show();
                    progressDialog.dismiss();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(Main3Activity.this, "Please fill correct details", Toast.LENGTH_LONG).show();
                }

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i15 = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(i15);
            }
        });


    }

    public void CheckEmpty() {
        email1 = e1.getText().toString().trim();
        password1 = e2.getText().toString().trim();

        if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)) {
            EditTextStatus = false;
            progressDialog.dismiss();
            Toast.makeText(Main3Activity.this, "PLease enter all the details!!!", Toast.LENGTH_LONG).show();

        } else {
                    EditTextStatus = true;
                    RegisterUser();
                }
            }

    public void RegisterUser() {
        progressDialog.setMessage("PLEASE WAIT WHILE WE ARE ADDING YOU TO OUR GROUP");
        progressDialog.show();
        Mauth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener
                (Main3Activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> register) {
                        if (register.isSuccessful()) {
                            Mauth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(Main3Activity.this,new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Main3Activity.this, "Registered successfully. Please check your email for verification", Toast.LENGTH_LONG).show();
                                                name1=e3.getText().toString().trim();
                                                phone1=e4.getText().toString().trim();
                                                batch1=e5.getText().toString().trim();
                                                year1=e6.getText().toString().trim();
                                                users.setName(name1);
                                                users.setPhoneno(phone1);
                                                users.setBatch(batch1);
                                                users.setYear(year1);
                                                uid=Mauth.getUid();
                                                Reff.child(uid).setValue(users);
                                            } else {
                                                Toast.makeText(Main3Activity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                            progressDialog.dismiss();
                                        }
                                    });
                        }
                        else
                        {
                            Toast.makeText(Main3Activity.this, register.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                        }
                    });
    }
}