package com.example.orderfoods.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoods.Model.User;
import com.example.orderfoods.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    private EditText edtName, edtPhone, edtPassword;
    Button btnSignUP;
/*
    private EditText edtEmailSignUp, edtPasswordSignUp, edtSignUpRepassword;
    Button btnSignUP;
    FirebaseAuth auth = FirebaseAuth.getInstance();

 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtName=findViewById(R.id.edit_Email_signup);
        edtPhone= findViewById(R.id.edit_Password_sign_up);
        edtPassword=findViewById(R.id.edit_rePassword_signup);

        btnSignUP=findViewById(R.id.btn_sign_up);
        final FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference table_user=database.getReference("User");
        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog =new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if (snapshot.child(edtPhone.getText().toString()).exists())
                       {
                           mDialog.dismiss();
                           Toast.makeText(SignUp.this, "phone Number already register", Toast.LENGTH_SHORT).show();
                       }
                       else
                       {
                           mDialog.dismiss();
                           User user=new User(edtName.getText().toString(),edtPassword.getText().toString());
                           table_user.child(edtPhone.getText().toString()).setValue(user);
                           Toast.makeText(SignUp.this, "Sign up successfully !", Toast.LENGTH_SHORT).show();
                           finish();

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
        edtEmailSignUp=findViewById(R.id.edit_Email_signup);
        edtPasswordSignUp= findViewById(R.id.edit_Password_sign_up);
        edtSignUpRepassword=findViewById(R.id.edit_rePassword_signup);


        btnSignUP=(Button)findViewById(R.id.btn_sign_up);


        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        String email = edtEmailSignUp.getText().toString();
        String password = edtPasswordSignUp.getText().toString();
        String repassword = edtSignUpRepassword.getText().toString();
        if (email.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
            Toast.makeText(this, "Please fill all date!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(repassword)) {
            Toast.makeText(this, "Pleas Write matching password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Please write long password", Toast.LENGTH_SHORT).show();
            return;
        }


        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(SignUp.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUp.this, MainActivity.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });
    }

}

         */

