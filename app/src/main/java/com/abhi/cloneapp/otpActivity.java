 package com.abhi.cloneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.abhi.cloneapp.databinding.ActivityOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OnOtpCompletionListener;

import java.util.concurrent.TimeUnit;

 public class otpActivity extends AppCompatActivity {

ActivityOtpBinding  binding;

  FirebaseAuth auth;
  String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         // get phone number that come from intent


        auth = FirebaseAuth.getInstance(); // create a instance of it
        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        binding.phoneLabel.setText("Verify" + phoneNumber);
  // as we are dealing with phone authentication so we need to make options
        PhoneAuthOptions options =  PhoneAuthOptions.newBuilder(auth)
                // kis number pe hme auth set krna
        .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(otpActivity.this) // current activity


                // ya to verify hoga ya nahi tb kya krene

           .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
               @Override
               public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

               }

               @Override
               public void onVerificationFailed(@NonNull FirebaseException e) {

               }
//code send krne ke liye
               @Override
               public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                   super.onCodeSent(verifyId, forceResendingToken);

                   verificationId =verifyId;
               }
           }).build() ;

          // now verify number
        PhoneAuthProvider.verifyPhoneNumber(options);
        binding.otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,otp);

                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                           Toast.makeText(otpActivity.this,"Logged In",Toast.LENGTH_LONG).show();
                         }
                         else {
                             Toast.makeText(otpActivity.this,"Failed ",Toast.LENGTH_LONG).show();
                         }
                    }
                });
            }
        });


    }
}