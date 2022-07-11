package com.example.book_e_sell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    Button sign_up,login,forgot_pwd;
    TextInputEditText email,pwd;
    FirebaseAuth mauth;
    ProgressDialog pd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sign_up=findViewById(R.id.Signup);
        login=findViewById(R.id.login);
        email=(TextInputEditText)findViewById(R.id.email);
        pwd=(TextInputEditText)findViewById(R.id.pass);
        forgot_pwd=findViewById(R.id.forgot_pwd);
        pd1=new ProgressDialog(this);

        mauth= FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                login_user();
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(login.this, registration.class));
                finish();
            }
        });
        forgot_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetemail= new EditText(v.getContext());
                AlertDialog.Builder pwdResetDialog = new AlertDialog.Builder(v.getContext());
                pwdResetDialog.setTitle("Reset Password?");
                pwdResetDialog.setMessage("Enter your registered email to get the link");
                pwdResetDialog.setView(resetemail);
                resetemail.setHint("email");

                pwdResetDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        String mail=resetemail.getText().toString();
                        if(mail.isEmpty())
                        {
                            resetemail.setError("enter valid email");
                        }
                        else
                        {
                            mauth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused)
                                {
                                    Toast.makeText(login.this, "reset link sent", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Toast.makeText(login.this, "failed to sent the link"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                pwdResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                pwdResetDialog.create().show();
            }
        });
    }
    private void login_user()
    {
        String email1= email.getText().toString();
        String pwd1=pwd.getText().toString();

        if(TextUtils.isEmpty(email1))
        {
            email.setError("cannot be empty");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(pwd1))
        {
            pwd.setError("cannot be empty");
            pwd.requestFocus();
        }
        else
        {
            pd1.setMessage("Just a movement.....");
            pd1.setTitle("Login");
            pd1.setCanceledOnTouchOutside(false);
            pd1.show();
            mauth.signInWithEmailAndPassword(email1,pwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        pd1.dismiss();
                        Toast.makeText(login.this, "user login successful", Toast.LENGTH_SHORT).show();
                        Intent homepage = new Intent(login.this,homepage.class);
                        startActivity(homepage);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(login.this, "error in login" , Toast.LENGTH_SHORT).show();
                        pd1.dismiss();
                    }
                }
            });
        }
    }
}