package com.example.book_e_sell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth mauth;
    FirebaseFirestore fstore;


    Button signup;
    TextInputEditText name,username,email,phone,pwd;
    String userid;
    String emailpattern= "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        signup = findViewById(R.id.Signup);
        name=(TextInputEditText)findViewById(R.id.name);
        username=(TextInputEditText)findViewById(R.id.username);
        email=(TextInputEditText)findViewById(R.id.email);
        phone=(TextInputEditText)findViewById(R.id.phone);
        pwd=(TextInputEditText)findViewById(R.id.password);
        mauth= FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        pd=new ProgressDialog(this);

        signup.setOnClickListener( view -> {
            create_user();
        });
    }
    private void create_user()
    {
        String name1= name.getText().toString();
        String username1= username.getText().toString();
        String email1= email.getText().toString();
        String phone1= phone.getText().toString();
        String pwd1= pwd.getText().toString();

        if(TextUtils.isEmpty(name1))
        {
            name.setError("can not be empty");
            name.requestFocus();
        }
        else if(TextUtils.isEmpty(username1))
        {
            username.setError("can not be empty");
            username.requestFocus();
        }
        else if(TextUtils.isEmpty(email1) || !email1.matches(emailpattern))
        {
            email.setError("enter correct email");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(phone1) || phone1.length()!=10)
        {
            phone.setError("Enter valid phone number");
            phone.requestFocus();
        }
        else if(TextUtils.isEmpty(pwd1) || pwd1.length()<6)
        {
            pwd.setError("enter valid password");
            pwd.requestFocus();
        }

        else
        {
            pd.setMessage("Just a movement.....");
            pd.setTitle("Registration");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            mauth.createUserWithEmailAndPassword(email1,pwd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(registration.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                        Intent homepage=new Intent(registration.this,homepage.class);
                        startActivity(homepage);
                        finish();
                        userid= mauth.getCurrentUser().getUid();
                        DocumentReference documentReference =fstore.collection("users").document(userid);
                        Map<String,Object> user=new HashMap<>();
                        user.put("fname", name1);
                        user.put("email",email1);
                        user.put("username",username1);
                        user.put("phone_no",phone1);
                        user.put("password",pwd1);
                        pd.dismiss();
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused)
                            {
                                Log.d(TAG,"user profile is created for "+ userid);
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(registration.this, "Registration Error"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}