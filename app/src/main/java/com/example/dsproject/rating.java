package com.example.dsproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class rating extends AppCompatActivity {

    private String tag="tag_rating";
private String username, password, user2;
private int adj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating);



        Intent intent=getIntent();
         username=intent.getStringExtra("username");
         password=intent.getStringExtra("password");
         user2=intent.getStringExtra("user2");


        final RatingBar ratingBar=(RatingBar)findViewById(R.id.rating_bar);
        Button rating_btn=(Button)findViewById(R.id.rating_submit_btn);




        rating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(tag,"star rating: "+ratingBar.getNumStars());

                adj=ratingBar.getNumStars();


                httpPOST task = new httpPOST();
                task.execute("https://rashid.systemdev.org/php2/post_balance.php");
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

            if (result.equals("200"))
            {Toast toast = Toast.makeText(getApplicationContext(), "Payment done successfully", Toast.LENGTH_SHORT);
                toast.show();}
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



            Log.v(tag,"get request executed");

            finish();
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
                request.write("{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+username+"\",\"password\":\""+password+"\",\"adj\":\""+adj+"\",\"user\":\""+user2+"\"}");
                request.flush();
                request.close();
                Log.d(tag, "{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+username+"\",\"password\":\""+password+"\",\"adj\":\""+adj+"\",\"user\":\""+user2+"\"}");

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
