package com.example.dsproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class signup  extends AppCompatActivity {



private EditText username, password1, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Button Singupbtn=(Button)findViewById(R.id.SignupPgbtn);
        Button SingupBackbtn=(Button)findViewById(R.id.SingupBackbtn);

         username=(EditText)findViewById(R.id.SignupUser);
         password1=(EditText)findViewById(R.id.SignupPassword1);
         password2=(EditText)findViewById(R.id.SignupPassword2);





        Singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!username.getText().toString().equals(""))
             if(password1.getText().toString().equals(password2.getText().toString())) {

                 httpPOST task =new httpPOST();
                 task.execute("https://rashid.systemdev.org/php2/signup.php");


             }

             else {
                 Toast toast = Toast.makeText(getApplicationContext(), "password does not match", Toast.LENGTH_SHORT);
                 toast.show();
             }
             else {
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
                return null;
            } catch (Exception e) {
                return null;
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Object result) {
String output;


    if(result.equals("202"))
    output="User created.";
    else if(result.equals("409"))    //conflict
    output="user already exists";
    else
    output="failed connection";

            Toast toast = Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT);
            toast.show();

            Log.v("Tag","get request executed");
        }
    }









    public String createuser(String path) throws Exception {
String jsonusr="{\"name\":\""+username.getText().toString()+ "\",\"password\":\""+password1.getText().toString()+ "\"}";

Log.v("Tag",jsonusr);

//{"user:"rashid","password":"123123"}

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
        Log.d("network", "The POST RESPONSE is: " + response);
        return  Integer.toString(response);
    }

































}//main