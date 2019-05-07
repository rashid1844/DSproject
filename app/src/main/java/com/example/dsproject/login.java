package com.example.dsproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.widget.Toast.makeText;

public class login extends AppCompatActivity {


    private Button Loginbtn;

    private EditText UserNameText, PasswordText;

    private TextView Signupbtn, activation_btn;

    private static String tag ="login_tag";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);

        Loginbtn=(Button)findViewById(R.id.btn_login);

        UserNameText=(EditText)findViewById(R.id.input_email);
        PasswordText=(EditText)findViewById(R.id.input_password);

        Signupbtn=(TextView)findViewById(R.id.link_signup);


        activation_btn=(TextView)findViewById(R.id.login_toactivation_btn);


        activation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activationpage();
            }
        });



        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            UserNameText.setText( prefs.getString("name", ""));
            PasswordText.setText( prefs.getString("pass", ""));
        }



        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                httpPOST task = new httpPOST();
                task.execute("https://rashid.systemdev.org/php2/login.php");

                SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
                editor.putString("name", UserNameText.getText().toString());
                editor.putString("pass", PasswordText.getText().toString());
                editor.apply();

            }});



       Signupbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               signuppage();
           }
       });







    }//oncreate













    public void loginpage() {

        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("username", UserNameText.getText().toString());
        intent.putExtra("password", PasswordText.getText().toString());
        startActivity(intent);
    }




public void signuppage(){

    Intent intent=new Intent(this,signup.class);

    startActivity(intent);
}


    public void activationpage(){

        Intent intent=new Intent(this,activation.class);

        startActivity(intent);
    }









    private class httpPOST extends AsyncTask {

        // arguments are given by execute() method call (defined in the parent): params[0] is the url.
        protected String doInBackground(Object... urls) {
            try {
                String out=   POSTRequest((String) urls[0]);
                return out;
            } catch (IOException e) {
                return null;
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Object result) {

            if (result.equals("202"))
loginpage();
            else if (result.equals("401"))
            {                    Toast toast = Toast.makeText(getApplicationContext(), "Worng User or password", Toast.LENGTH_SHORT);
                toast.show();}
            else if (result.equals("406"))
            {                    Toast toast = Toast.makeText(getApplicationContext(), "Account not activated", Toast.LENGTH_SHORT);
                toast.show();}

            else {
                Toast toast = Toast.makeText(getApplicationContext(), "Connection issue", Toast.LENGTH_SHORT);
                toast.show();
            }



            Log.v("lab-2.4","get request executed");
        }
    }






    private  String POSTRequest(String myurl) throws IOException {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            InputStream is = null;


            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                OutputStreamWriter request = new OutputStreamWriter(
                        conn.getOutputStream());
                request.write("{\"name\":\""+UserNameText.getText().toString()+"\",\"password\":\""+PasswordText.getText().toString() +"\"}");
                request.flush();
                request.close();
                Log.d("Lab-2.4", "{\"name\":\""+UserNameText.getText().toString()+"\",\"password\":\""+PasswordText.getText().toString() +"\"}");

                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d("Lab-2.4", "The response is: " + response);

        return Integer.toString(response);
            }catch (Exception e){ return "404";}

        }

        else {
            Log.v(tag, "no internet");
            return "404";
        }

    }





























}//main