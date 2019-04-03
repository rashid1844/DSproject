package com.example.dsproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class setting extends Fragment {


    private MainActivity main;
    private String username, password;

    @SuppressLint("ValidFragment")
    public  setting(MainActivity main1, String user,String pass) {main=main1; username=user; password=pass;}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);

        //todo: my requests intent


        return view;
    }


}