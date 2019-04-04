package com.example.dsproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("ValidFragment")
public class setting extends Fragment {


    private MainActivity main;
    private String username, password;

    private Button my_requests;

    @SuppressLint("ValidFragment")
    public  setting(MainActivity main1, String user,String pass) {main=main1; username=user; password=pass;}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);


        my_requests=(Button)view.findViewById(R.id.my_requests_btn);



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


















}//main