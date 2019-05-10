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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.bytedeco.javacpp.presets.opencv_core;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.widget.Toast.makeText;



public class my_requests extends AppCompatActivity {

private ListView my_request_listview;

    private String item_name[]= new String[30];
    private String tag ="my_request_tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_requests);

Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");


        httpGET task = new httpGET();
        task.execute("https://rashid.systemdev.org/php2/get_my_request.php", "{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+username+"\",\"password\":\""+password +"\"}");












    }//oncreate







    private class httpGET extends AsyncTask {

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
            Log.v(tag,result.toString());

            try {
                jsonpr(result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }







    private  String GETRequest(String myurl, String params) throws IOException {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
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
                if (is != null) { is.close(); } }
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








    public void jsonpr(String in) throws Exception
    {
        //      JSONObject jsonObject;
        JSONArray jsonArray;

//        jsonObject =new JSONObject(in);

        jsonArray =new JSONArray(in);

        ArrayList<String> list = new ArrayList<String>();

        for (int i=0;i<jsonArray.length();i++){

            JSONObject jo= jsonArray.getJSONObject(i);
            Log.v(tag,jo.getString("request_id"));
            Log.v(tag,jo.getString("item_name"));

            item_name[i]=jo.getString("item_name");

            list.add(item_name[i]);

        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        ListView listView = (ListView) findViewById(R.id.my_request_listview);
        listView.setAdapter(adapter);




    }




















}//main