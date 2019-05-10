package com.example.dsproject;

import android.content.Context;
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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.widget.Toast.makeText;

public class signup  extends AppCompatActivity {



private EditText username, password1, password2;

    private String tag ="signup_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup2);

        Button Singupbtn=(Button)findViewById(R.id.btn_signup);
        TextView SingupBackbtn=(TextView) findViewById(R.id.link_login);

         username=(EditText)findViewById(R.id.input_name);
         password1=(EditText)findViewById(R.id.input_password1);
         password2=(EditText)findViewById(R.id.input_password2);





        Singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().startsWith("+9715") || username.getText().toString().startsWith("009715") ){

             if(password1.getText().toString().equals(password2.getText().toString())) {
                 Log.v(tag,"password match");
                 httpPOST task =new httpPOST();
                 task.execute("https://rashid.systemdev.org/php2/signup.php");


             }

             else {
                 Log.v(tag,"password doesn't match");
                 Toast toast = Toast.makeText(getApplicationContext(), "password does not match", Toast.LENGTH_SHORT);
                 toast.show();
             }
                }

             else {Log.v(tag,"please enter correct phone number");
                    Toast toast = Toast.makeText(getApplicationContext(), "please type in username", Toast.LENGTH_SHORT);
                    toast.show();
                }




            }});













        SingupBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });













    }//oncreate





    private class httpPOST extends AsyncTask {

        // arguments are given by execute() method call (defined in the parent): params[0] is the url.
        protected String doInBackground(Object... params) {
            try {
                String out=   createuser((String) params[0]);
                return out;
            } catch (IOException e) {
                Log.v(tag,e.getMessage().toString());
                return null;
            } catch (Exception e) {
                Log.v(tag,e.getMessage().toString());
                return null;
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Object result) {
String output;
            Log.v(tag,"connection code: "+result);

    if(result.equals("202"))
    output="User created.";
    else if(result.equals("409"))    //conflict
    output="user already exists";
    else
    output="failed connection";

            Toast toast = Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT);
            toast.show();

            Log.v(tag,"get request executed");
        }
    }









    public String createuser(String path) throws Exception {
String jsonusr="{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+username.getText().toString()+ "\",\"password\":\""+password1.getText().toString()+ "\"}";

Log.v(tag,jsonusr);

//{"user:"rashid","password":"123123"}

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
try {
    URL url = new URL(path);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    connection.setRequestMethod("POST");

    OutputStreamWriter request = new OutputStreamWriter(
            connection.getOutputStream());
    request.write(jsonusr);
    request.flush();
    request.close();
    connection.connect();
    int response = connection.getResponseCode();
    Log.d(tag, "The POST RESPONSE is: " + response);
    return Integer.toString(response);
}catch (Exception e){return "404";}}

    else {

            Log.v(tag,"no internet");
            return null;

    }

    }

































}//main