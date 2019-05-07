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
import android.widget.ListView;
import android.widget.Toast;

import com.appunite.appunitevideoplayer.PlayerActivity;
import com.khizar1556.mkvideoplayer.MKPlayerActivity;

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

import static android.graphics.Color.YELLOW;
import static android.widget.Toast.makeText;

@SuppressLint("ValidFragment")
public class replies extends Fragment {



    private MainActivity main;
    private String username, password;
    private String tag ="replies_tag";

    private String rep_user[]= new String[30];
    private String stream_id[]= new String[30];
    private String item_name[]= new String[30];
    private int request_id[]= new int[30];


private SwipeRefreshLayout swipeRefreshLayout;


    @SuppressLint("ValidFragment")
    public  replies(MainActivity main1, String user,String pass) {main=main1; username=user; password=pass;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.replies, container, false);



        httpGET task = new httpGET();
        task.execute("https://rashid.systemdev.org/php2/get_reply.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\"}");




        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refreshlayout_replies);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                httpGET task = new httpGET();
                task.execute("https://rashid.systemdev.org/php2/get_reply.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\"}");



                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        });






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
            Log.v(tag,jo.getString("rep_user"));
            Log.v(tag,jo.getString("item_name"));

            rep_user[i]=jo.getString("rep_user");
            item_name[i]=jo.getString("item_name");
            stream_id[i]=jo.getString("stream_id");
            request_id[i]=jo.getInt("request_id");


            list.add(item_name[i]);

        }

        ArrayAdapter adapter = new ArrayAdapter<String>(main,android.R.layout.simple_list_item_1,list);
        ListView listView = (ListView) main.findViewById(R.id.listview_replies);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

         //       Toast toast = makeText(main.getApplicationContext(), "keep pressing", Toast.LENGTH_SHORT);
         //       toast.show();

                ratingpage(position);



            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                String url="https://stream.mux.com/"+stream_id[position]+".m3u8";
                Log.v(tag,url);
          //      MKPlayerActivity.configPlayer(main).play(url);


          //      Intent intent=PlayerActivity.getVideoPlayerIntent(main,url,item_name[position]);



                startActivity(PlayerActivity.getVideoPlayerIntent(main, url, item_name[position]));




//todo: add counter to calc cost
                Log.v(tag, item_name[position]);

                return true;
            }});

    }







    public void ratingpage(int a){

        Intent intent=new Intent(main,rating.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("user2", rep_user[a]);
        intent.putExtra("item_name", item_name[a]);

        startActivity(intent);
    }











}//main