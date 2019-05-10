package com.example.dsproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class activation extends AppCompatActivity {

    private static String tag ="activation_tag";


    private EditText activation_name, activation_pass, activation_code;
    private Button activation_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activation);

        activation_name=(EditText)findViewById(R.id.activation_name);
        activation_pass=(EditText)findViewById(R.id.activation_password);
        activation_code=(EditText)findViewById(R.id.activation_code);
        activation_btn=(Button)findViewById(R.id.activation_btn);


    activation_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            httpPOST task = new httpPOST();
            task.execute("https://rashid.systemdev.org/php2/activation.php");

        }
    });




    }//oncreate







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
            {   Toast toast = Toast.makeText(getApplicationContext(), "Account activated succesfully", Toast.LENGTH_LONG);
                toast.show();}
            else if (result.equals("208"))
            {   Toast toast = Toast.makeText(getApplicationContext(), "Account already active", Toast.LENGTH_LONG);
                toast.show();}
            else if (result.equals("406"))
            {                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong code, new code is generated", Toast.LENGTH_LONG);
                toast.show();}

            else if (result.equals("401"))
            {                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong code, five attemps only allowed", Toast.LENGTH_LONG);
                toast.show();}



            else {
                Toast toast = Toast.makeText(getApplicationContext(), "Connection issue", Toast.LENGTH_LONG);
                toast.show();
            }



            Log.v(tag,"get request executed");
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
                request.write("{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+activation_name.getText().toString()+"\",\"password\":\""+activation_pass.getText().toString() +"\",\"code\":\""+activation_code.getText().toString()+"\"}");
                request.flush();
                request.close();
                Log.d(tag, "{\"name\":\""+activation_name.getText().toString()+"\",\"password\":\""+activation_pass.getText().toString() +"\",\"code\":\""+activation_code.getText().toString()+"\"}");

                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(tag, "The response is: " + response);

                return Integer.toString(response);
            }catch (Exception e){ return "404";}

        }

        else {
            Log.v(tag, "no internet");
            return "404";
        }

    }














}//main
