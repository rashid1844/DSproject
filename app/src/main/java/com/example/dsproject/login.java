package com.example.dsproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {


    private Button Loginbtn;

    private EditText UserNameText, PasswordText;

    private TextView Signupbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Loginbtn=(Button)findViewById(R.id.Loginbtn);

        UserNameText=(EditText)findViewById(R.id.UserNameText);
        PasswordText=(EditText)findViewById(R.id.PasswordText);

        Signupbtn=(TextView)findViewById(R.id.SignupText);



        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (logincheck() )
                { loginpage();}
                else
                { Log.v("Tag","user: "+UserNameText.getText().toString());
                    Log.v("Tag","password: "+PasswordText.getText().toString());
                    Toast toast = Toast.makeText(getApplicationContext(), "Worng Username or password", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }});



       Signupbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               signuppage();
           }
       });







    }//oncreate











public boolean logincheck(){

String user= UserNameText.getText().toString();
String pass=PasswordText.getText().toString();

    if (user.equals("rashid") && pass.equals("123123"))
        return true;
        else {
        return false;}

}


    public void loginpage() {

        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("username", UserNameText.getText());

        startActivity(intent);
    }




public void signuppage(){

    Intent intent=new Intent(this,signup.class);

    startActivity(intent);
}





















}//main