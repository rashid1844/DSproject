package com.example.dsproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.makeText;


@SuppressLint("ValidFragment")
public class items extends Fragment {


    private MainActivity main;
    private String username, password;

    private String tag="tag_items";

    private String item_name[]= new String[30];
    private int item_id[]=new int[30];

    @SuppressLint("ValidFragment")
    public  items(MainActivity main1, String user,String pass) {main=main1; username=user; password=pass;}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.items, container, false);


        httpGET task = new httpGET();
        task.execute("https://rashid.systemdev.org/php2/get_item.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\"}");






        return view;
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

                Toast toast1 = Toast.makeText(main.getApplicationContext(), "Request sent", Toast.LENGTH_LONG);
                toast1.show();



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






    public void jsonpr(String in) throws Exception
    {
  //      JSONObject jsonObject;
        JSONArray jsonArray;

//        jsonObject =new JSONObject(in);

        jsonArray =new JSONArray(in);

        ArrayList<String> list = new ArrayList<String>();

        for (int i=0;i<jsonArray.length();i++){

            JSONObject jo= jsonArray.getJSONObject(i);
            Log.v(tag,jo.getString("item_id"));
            Log.v(tag,jo.getString("item_name"));

            item_id[i]=jo.getInt("item_id");
            item_name[i]=jo.getString("item_name");

            list.add(item_name[i]);

        }

        ArrayAdapter adapter = new ArrayAdapter<String>(main,android.R.layout.simple_list_item_1,list);
        ListView listView = (ListView) main.findViewById(R.id.listview_items);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast toast = makeText(main.getApplicationContext(), "keep pressing", Toast.LENGTH_SHORT);
                toast.show();

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                sendrequest(position);
                Log.v(tag, item_name[position]);

                return true;
            }});

    }






    public void sendrequest(int position){


        httpGET2 task = new httpGET2();
        task.execute("https://rashid.systemdev.org/php2/post_request.php",
                "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"item_id\":\""+item_id[position]+"\",\"item_name\":\""+item_name[position]+"\"}");




    }






}//main