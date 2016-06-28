package com.imaginat.theissues;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.imaginat.theissues.customFragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        toolbar.setTitleTextColor(Color.RED);
        toolbar.setTitle("My Government Officials");
        setSupportActionBar(toolbar);


        //Settinig up the initial fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.add(R.id.main_fragmentSpace, mainFragment);
        fragmentTransaction.commit();







    }


}
