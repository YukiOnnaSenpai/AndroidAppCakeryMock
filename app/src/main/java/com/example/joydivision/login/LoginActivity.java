package com.example.joydivision.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.joydivision.menu.MenuActivityDrawer;
import com.example.joydivision.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ornach.nobobutton.NoboButton;

public class LoginActivity extends AppCompatActivity {

    private EditText phone,password;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView = findViewById(R.id.imageView);
        phone = findViewById(R.id.editPhoneNumber);
        password = findViewById(R.id.editTextPassword);
        NoboButton btnLogin = findViewById(R.id.buttonSignin);
        NoboButton btnSignup = findViewById(R.id.buttonSignup);

       // Picasso.get().load().into(imageView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tableUser = database.getReference("users");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                tableUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        try {



                        if(dataSnapshot.child(phone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(String.valueOf(phone.getText())).getValue(User.class);
                            if (user.getPassword().equals(password.getText().toString())) {

                                Toast.makeText(LoginActivity.this, "Sign in successfully!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MenuActivityDrawer.class);
                                Common.currentUser = user;
                                startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User not exist!", Toast.LENGTH_SHORT).show();
                        }

                        } catch(NullPointerException e) {
                            Toast.makeText(LoginActivity.this, "Please fill empty fields!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                //intent.putExtra("position", position);
                LoginActivity.this.startActivity(intent);

            }

        });


    }
}
