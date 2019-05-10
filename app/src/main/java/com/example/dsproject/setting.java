package com.example.dsproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

@SuppressLint("ValidFragment")
public class setting extends Fragment {


    private MainActivity main;
    private String username, password;

    private int balance=0;
    private String tag="setting_tag";

    private TextView balance_view;

    private Button my_requests;

    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("ValidFragment")
    public  setting(MainActivity main1, String user,String pass) {main=main1; username=user; password=pass;}





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);


        my_requests=(Button)view.findViewById(R.id.my_requests_btn);


//balacnce




        balance_view=(TextView)view.findViewById(R.id.balance_view);
        httpGET task = new httpGET();
        task.execute("https://rashid.systemdev.org/php2/get_balance.php", "{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+username+"\",\"password\":\""+password +"\"}");





        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refreshlayout_setting);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                httpGET task = new httpGET();
                task.execute("https://rashid.systemdev.org/php2/get_balance.php", "{\"prikey\":\"WUHhBpHAwCYSn%+8=Fsy\",\"name\":\""+username+"\",\"password\":\""+password +"\"}");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });





        my_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_request_page();

            }
        });




        //todo: my requests intent


        return view;
    }//oncreate





    public void my_request_page() {

        Intent intent=new Intent(main,my_requests.class);
        //intent.putExtra("stream_key", "3bf1c8f0-2414-766f-9f77-9b13f16097c0");
        intent.putExtra("username", username);
        intent.putExtra("password", password);

        startActivity(intent);
    }






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
        ConnectivityManager connMgr = (ConnectivityManager) main.getSystemService(Context.CONNECTIVITY_SERVICE);
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

        JSONArray jsonArray;

        jsonArray =new JSONArray(in);

        ArrayList<String> list = new ArrayList<String>();

        for (int i=0;i<jsonArray.length();i++){

            JSONObject jo= jsonArray.getJSONObject(i);
            Log.v(tag,Integer.toString(jo.getInt("balance")));

            balance=jo.getInt("balance");

        }

        balance_view.setText("Balance is:"+Integer.toString(balance));

        Log.v(tag,"Balance is: "+Integer.toString(balance));
    }







}//main