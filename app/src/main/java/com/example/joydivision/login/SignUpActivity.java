package com.example.joydivision.login;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.joydivision.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ornach.nobobutton.NoboButton;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUpActivity extends AppCompatActivity {

    MaterialEditText editPhone,editName,editEmail,editPassword,editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        editName = findViewById(R.id.editTextName);
        editPhone = findViewById(R.id.editTextPhone2);
        editEmail = findViewById(R.id.editTextEmail);
        editPassword = findViewById(R.id.editTextPassword2);
        editAddress = findViewById(R.id.editTextAddress);
        NoboButton btnSignup = findViewById(R.id.buttonSignup2);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("users");

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();


                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.child(editPhone.getText().toString()).exists()) {
                                    mDialog.dismiss();

                                    //Toast.makeText(SignUpActivity.this, "Something is wrong!", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    mDialog.dismiss();
                                    if(editName.getText().toString().trim().length()== 0 || editPassword.getText().toString().trim().length()== 0 || editEmail.getText().toString().trim().length()== 0 || editPhone.getText().toString().trim().length()== 0|| editAddress.getText().toString().trim().length()== 0  ) {
                                        Toast.makeText(SignUpActivity.this, "Fill the empty fields!", Toast.LENGTH_SHORT).show();
                                    }else
                                        {
                                            User user = new User(editName.getText().toString(), editPhone.getText().toString(), editPassword.getText().toString(), editEmail.getText().toString(), editAddress.getText().toString());
                                            table_user.child(editPhone.getText().toString()).setValue(user);
                                            Toast.makeText(SignUpActivity.this, "Sign up successfully!", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                }


                            }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



            }
        });


    }
}
