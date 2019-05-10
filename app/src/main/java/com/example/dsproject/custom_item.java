package com.example.dsproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;


public class custom_item extends AppCompatActivity {


    private String username, password;

    private String tag="tag_custom_item";


    private Button custom_item_submit_btn;
    private EditText custom_item_description, custom_item_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_item);

        Intent intent=getIntent();
        final String username=intent.getStringExtra("username");
        final String password=intent.getStringExtra("password");


        custom_item_submit_btn=(Button)findViewById(R.id.custom_item_submit_btn);
        custom_item_description=(EditText)findViewById(R.id.custom_item_describtion);
        custom_item_name=(EditText)findViewById(R.id.custom_item_item_name);



        custom_item_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(custom_item_description.equals("") ||custom_item_name.equals("")){
                 Toast toast = Toast.makeText(getApplicationContext(), "Please fill in the blanks", Toast.LENGTH_SHORT);
                 toast.show();
             }


             else {
                 httpGET2 task = new httpGET2();
                 task.execute("https://rashid.systemdev.org/php2/post_request.php",
"{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+username+"\",\"password\":\""+password+"\",\"item_id\":\"-1\",\"category\":\"null\",\"description\":\"" + custom_item_description.getText().toString() + "\",\"item_name\":\"" + custom_item_name.getText().toString() + "\"}");

             }            }});





    }//oncreate








private class httpGET2 extends AsyncTask {

    // arguments are given by execute() method call (defined in the parent): params[0] is the url.
    protected String doInBackground(Object... urls) {
        try {
            String out=   GETRequest((String) urls[0], (String)urls[1]);
            return out;
        } catch (IOException e) {
            return null;
        }
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(Object result) {

        Toast toast1 = Toast.makeText(getApplicationContext(), "Category is "+result.toString(), Toast.LENGTH_LONG);
        toast1.show();
        finish();



    }
}





    private  String GETRequest(String myurl, String params) throws IOException {
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            InputStream is = null;


            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);


                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");

                OutputStreamWriter request = new OutputStreamWriter(
                        conn.getOutputStream());
                request.write(params);
                request.flush();
                request.close();

                // Starts the query
                conn.connect();
                int  response = conn.getResponseCode();


                Log.d(tag, "The response is: " + response);
                is = conn.getInputStream();

                Log.d(tag, "The response content: " + is);


                if(is != null)
                    return readIt(is);
                else
                    return null;


            } finally {
                if (is != null) {
                    is.close();
                }



            }
        }

        else {
            Log.v(tag,"no internet");
            return null;
            // display error
        }



    }






    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[5000];
        reader.read(buffer);
        Log.v(tag, buffer.toString());
        return new String(buffer);

    }

















}//main