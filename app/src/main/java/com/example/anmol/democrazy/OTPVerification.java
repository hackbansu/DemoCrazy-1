package com.example.anmol.democrazy;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anmol.democrazy.login.OTP;
import com.example.anmol.democrazy.login.getUserDetails;

import org.json.JSONException;
import org.json.JSONObject;


public class OTPVerification extends AppCompatActivity {

    EditText OtpEditText;
    String phoneNumber;
    Button btn;

    // Used to detect SMS
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.otp_verification_activity);

        phoneNumber = getIntent().getExtras().getString(getString(R.string.Phone_number));
        OtpEditText = (EditText) findViewById(R.id.OtpEditText);
        btn= (Button) findViewById(R.id.Login_OTP);


        broadcastReceiver = new BroadcastReceiver() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceive(Context context, Intent intent) {


                String senderName = intent.getExtras().getString("phoneNumber");

                String message = intent.getExtras().getString("message");

                //Printing message
                System.out.println(message);

                // Don't change otherwise it will not get right otp
                String otp = message.replace("Hi, your otp for democrazy is: ", "").substring(0, 6);

                //Printing otp
                System.out.println(otp);

                System.out.println("Phone Number : " + phoneNumber);


                sendOTP(otp);



            }
        };


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 sendButt(OtpEditText.getText().toString());
            }
        });

    }


    //Clicking Butt
    public void sendButt(String otp){



    }


    //sendOTP
    public void sendOTP(String otp){

        OTP otp1=new OTP(phoneNumber,otp,OTPVerification.this);
        otp1.sendOTP(new OTP.OTPCallback() {
            @Override
            public void getStatus(boolean status, String msg) {

                //checking status - true
                if (status){
                    // msg - old user
                    if (msg.equals("old user")){
                        getDetails();
                    }

                    // msg - new user
                    if (msg.equals("request other details")){
                        Intent i=new Intent(OTPVerification.this,UserDetails.class);
                        i.putExtra("PhoneNumber",phoneNumber);
                        startActivity(i);
                    }
                }
            }
        });
    }


    //checking Details
    public void getDetails(){

        //getUserDetails instance
        getUserDetails getUser=new getUserDetails(OTPVerification.this);

        getUser.getDetails(new getUserDetails.getDetailsOfUser() {
            @Override
            public void result(boolean status, JSONObject jsonObject) throws JSONException {


                /*status - false
                   sending user to submit there details
                 */
                if (!status){
                    String msg=jsonObject.getString("msg");
                    if (msg.equals("please complete first login process")){
                        Intent i=new Intent(OTPVerification.this,UserDetails.class);
                        i.putExtra("PhoneNumber",phoneNumber);
                        startActivity(i);
                    }
                }
                /* status - true
                  getting details from database successfully
                 */
                else{
                    // Redirecting To MainActivity
                    Intent i=new Intent(OTPVerification.this,MainActivity.class);
                    startActivity(i);
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("smsReceiver");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        super.onPause();
    }

}
