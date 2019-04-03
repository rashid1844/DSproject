package com.example.dsproject;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

public class MainActivity extends AppCompatActivity {


    private sectionPageAdaptor msectionPageAdaptor;
    private ViewPager mViewPager;

    private String username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
         username = intent.getStringExtra("username");
         password = intent.getStringExtra("password");



        msectionPageAdaptor = new sectionPageAdaptor(getSupportFragmentManager());

        mViewPager= (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(mViewPager);



    }//oncreate



    private void setupViewPager(ViewPager viewPager){

        sectionPageAdaptor adaptor= new sectionPageAdaptor(getSupportFragmentManager());
        adaptor.addFragment(new items(this,username,password), "Home");
        adaptor.addFragment(new requests(this,username,password), "Requests");
        adaptor.addFragment(new replies(this,username,password), "Replies");
        adaptor.addFragment(new setting(this,username,password), "Setting");


        viewPager.setAdapter(adaptor); }


}//main
