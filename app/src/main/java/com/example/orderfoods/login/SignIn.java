package com.example.orderfoods.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoods.Activity.MainActivity;
import com.example.orderfoods.Model.User;
import com.example.orderfoods.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    EditText edtEmailSignIn, edtPasswordSignIn;
    Button btnSignIn;
    //   FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPasswordSignIn = findViewById(R.id.edit_Password_sign_In);

        edtEmailSignIn = findViewById(R.id.edit_Email_signin);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please waiting!...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        // check if  user not exist in database
                          if (snapshot.child(edtEmailSignIn.getText().toString()).exists()) {

                        // Get User information

                        mDialog.dismiss();
                        User user = snapshot.child(edtEmailSignIn.getText().toString()).getValue(User.class);
                        user.setPhone(edtEmailSignIn.getText().toString());
                        if (user.getPassword().equals(edtPasswordSignIn.getText().toString())) {
                            Toast.makeText(SignIn.this, "Sign in successfully !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignIn.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignIn.this, "Sign In failed!", Toast.LENGTH_SHORT).show();
                        }
                          }

                    else{
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}





















        /*
        edtPasswordSignIn = findViewById(R.id.edit_Password_sign_In);

        edtEmailSignIn = findViewById(R.id.edit_Email_signin);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmailSignIn.getText().toString();
                String password = edtPasswordSignIn.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignIn.this, "Please fill all date!", Toast.LENGTH_LONG).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignIn.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignIn.this, MainActivity.class));
                                    finish();
                                } else {
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(SignIn.this, errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}

         */