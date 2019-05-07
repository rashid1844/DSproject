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
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import static android.widget.Toast.makeText;

@SuppressLint("ValidFragment")
public class requests extends Fragment {


    private MainActivity main;
    private String username, password, stream_key,stream_id;
    private String tag ="request_tag";
    private int click_position=0;


    private String item_name[]= new String[50];
    private int request_id[]=new int[50];
    private String user[]= new String[50];
    private String description[]= new String[50];

    private SwipeRefreshLayout swipeRefreshLayout;

private   ListView listView ;


    @SuppressLint("ValidFragment")
    public  requests(MainActivity main1, String user,String pass) {main=main1; username=user; password=pass;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.requests, container, false);

        listView = (ListView) view.findViewById(R.id.listview_request);


        httpGET task = new httpGET();
        task.execute("https://rashid.systemdev.org/php2/get_request.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\"}");


        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.refreshlayout_request);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                httpGET task = new httpGET();
                task.execute("https://rashid.systemdev.org/php2/get_request.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"category\":\"electronics\"}");


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            },2000);

            }
        });

        setHasOptionsMenu(true);

        return view;
    }//oncreate



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater=main.getMenuInflater();
        inflater.inflate(R.menu.request_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);


    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_electronics:

                httpGET task = new httpGET();
                task.execute("https://rashid.systemdev.org/php2/get_request.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"category\":\"electronics\"}");
                return true;


            case R.id.menu_clothing:

                httpGET task2 = new httpGET();
                task2.execute("https://rashid.systemdev.org/php2/get_request.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"category\":\"clothing\"}");
                return true;


            case R.id.menu_automotive_accessories:

                httpGET task3 = new httpGET();
                task3.execute("https://rashid.systemdev.org/php2/get_request.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"category\":\"automotive accessories\"}");
                return true;



            case R.id.menu_mobiles_accessories:

                httpGET task4 = new httpGET();
                task4.execute("https://rashid.systemdev.org/php2/get_request.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"category\":\"mobiles accessories\"}");
                return true;



            case R.id.menu_computers:

                httpGET task5 = new httpGET();
                task5.execute("https://rashid.systemdev.org/php2/get_request.php", "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"category\":\"computers\"}");
                return true;









        }

        return super.onOptionsItemSelected(item);
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

            try {

                key_parser(result.toString());

            } catch (Exception e) { e.printStackTrace(); }

            sendreply();

        }
    }






    private class httpGET3 extends AsyncTask {

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

            Toast toast1 = Toast.makeText(main.getApplicationContext(), "Reply sent", Toast.LENGTH_LONG);
            toast1.show();

            recordpage();
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


                if(myurl.equals("https://api.mux.com/video/v1/live-streams"))
                {
    conn.setRequestProperty("Content-Type", "application/json");
    conn.setRequestProperty("Authorization","Basic "+ "ZmJmY2E3NTItMjY4MC00YWU4LWJkZjMtN2RmMGI1MGQ3OTZjOnFLMnptNjFjRVFDcEs3RlgyL1J0a0szdmliYnNpS3Jpc2I5QjFRMXovdEEzOXVTUFgxZFdScXBnNGQyUzFTZmJPT3NsaVVlK1N0Tg==");
//ACCESS_TOKEN_ID: ACCESS_TOKEN_SECRET encoded using base64 using base64encode.org

                }
                else {
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                }

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

            request_id[i]=jo.getInt("request_id");
            item_name[i]=jo.getString("item_name");
            user[i]=jo.getString("user");
            description[i]=jo.getString("description");


            list.add(item_name[i]);

        }

        ArrayAdapter adapter = new ArrayAdapter<String>(main,android.R.layout.simple_list_item_1,list);
      //  ListView listView = (ListView) main.findViewById(R.id.listview_request);
        listView.setAdapter(adapter);









        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast toast = makeText(main.getApplicationContext(), description[position], Toast.LENGTH_LONG);
                toast.show();

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


              //  sendreply(position);

                click_position=position;
                getkey();
                Log.v(tag, item_name[position]);

                return true;
            }});

    }







    public void getkey(){

        httpGET2 task = new httpGET2();

        task.execute("https://api.mux.com/video/v1/live-streams",
                "{ \"playback_policy\": [\"public\"], \"new_asset_settings\": { \"playback_policy\": [\"public\"] } }");

    }





    public void sendreply(){

        httpGET3 task = new httpGET3();
        task.execute("https://rashid.systemdev.org/php2/post_reply.php",
                "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"item_name\":\""+item_name[click_position]+"\",\"request_id\":\""+request_id[click_position]+"\",\"req_user\":\""+user[click_position]+"\",\"stream_id\":\""+stream_id+"\"}");
Log.v(tag, "{\"name\":\""+username+"\",\"password\":\""+password +"\",\"item_name\":\""+item_name[click_position]+"\",\"request_id\":\""+request_id[click_position]+"\",\"req_user\":\""+user[click_position]+"\",\"stream_id\":\""+stream_id+"\"}");

    }







    public void key_parser(String in) throws Exception{

        JSONObject jsonObject,jsonObject1;
        JSONArray jsonArray;
        String playback_ids,data;


        jsonObject= new JSONObject(in);
        data=jsonObject.getString("data");
        jsonObject= new JSONObject(data);

        playback_ids=jsonObject.getString("playback_ids");

        stream_key=jsonObject.getString("stream_key");

        jsonArray=new JSONArray(playback_ids);

        jsonObject1=jsonArray.getJSONObject(0);


       // stream_id=jsonObject.getString("id"); //live
        stream_id=jsonObject1.getString("id");//playback

        Log.v(tag,"stream_key: "+stream_key);
        Log.v(tag,"stream_id: "+stream_id);

    }







    public void recordpage() {

        Intent intent=new Intent(main,record.class);
        //intent.putExtra("stream_key", "3bf1c8f0-2414-766f-9f77-9b13f16097c0");
        intent.putExtra("stream_key", stream_key);

        startActivity(intent);
    }











}//main