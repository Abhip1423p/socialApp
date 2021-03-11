package com.abhi.cloneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.abhi.cloneapp.databinding.ActivityPhoneNumberBinding;

public class phoneNumber extends AppCompatActivity {



    ActivityPhoneNumberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.conyinueB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(phoneNumber.this,otpActivity.class);
                // along side intent i wnated to pass data to i use pur extre with bundle name and reference
                 intent.putExtra("PhoneNumber",binding.phoneN.getText().toString());


                 // here phoneN is id of edit text where we will enter our number for verification

             startActivity(intent);
            }
        });
    }
}