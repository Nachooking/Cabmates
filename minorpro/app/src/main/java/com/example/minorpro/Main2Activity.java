package com.example.minorpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Main2Activity extends AppCompatActivity {
    private EditText e1, e2;
    private TextView t1;
    private Button b1,b2,b3;
    private String email1, password1;
    private Boolean EditTextStatus;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        e1= findViewById(R.id.emaildit1);
        e2= findViewById(R.id.emaildit2);
        t1= findViewById(R.id.textviewemail);
        b1= findViewById(R.id.tvLogin);
        b2= findViewById(R.id.tvNewUser);
        b3=findViewById(R.id.tvForgotPassword);

        mAuth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Main2Activity.this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               CheckEmpty();
                if(EditTextStatus)
                {
                    userlogin();
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"Please fill correct details",Toast.LENGTH_LONG ).show();
                }
                }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i2 = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(i2);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent i3= new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(i3);
            }
        });

    }
    public void  userlogin() {
        progressDialog.setMessage("PLEASE WAIT");
        progressDialog.show();
            mAuth.signInWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(Main2Activity.this, "LOGGING IN", Toast.LENGTH_LONG).show();
                                if(mAuth.getCurrentUser().isEmailVerified()) {
                                    Intent i6 = new Intent(Main2Activity.this, Main6Activity.class);
                                    startActivity(i6);
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(Main2Activity.this, "Please verify your email address", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Main2Activity.this, "Email or Password Incorrect, Please Try Again", Toast.LENGTH_LONG).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
        }

    public void CheckEmpty()
    {
        email1= e1.getText().toString().trim();
        password1= e2.getText().toString().trim();

        if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)) {
            EditTextStatus = false;
        }
        else{
            EditTextStatus = true;
        }
    }

}
