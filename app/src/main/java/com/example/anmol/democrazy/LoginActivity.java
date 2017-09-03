package com.example.anmol.democrazy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.democrazy.login.PhoneNumber;



public class LoginActivity extends AppCompatActivity {

    EditText editText;
    Button Submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);
        initValues();

        SubmitClick();


    }

    public void initValues(){
        editText= (EditText) findViewById(R.id.PhoneNumber);
        Submit= (Button) findViewById(R.id.SubmitPhoneNumber);
    }

    public void SubmitClick(){

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number=editText.getText().toString();

                Toast.makeText(getApplicationContext(),number,Toast.LENGTH_LONG).show();

                PhoneNumber phoneNumber=new PhoneNumber(number,LoginActivity.this);
                phoneNumber.sendNumber(new PhoneNumber.phNoCallback() {
                    @Override
                    public void getResult(boolean status, String msg) {

                        if (status==true && msg.equals("message(s) queued")){
                            Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
                        }

                    }
                });



            }
        });

    }

}
