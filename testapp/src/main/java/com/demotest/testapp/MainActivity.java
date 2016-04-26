package com.demotest.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.progressDemo).setOnClickListener(this);
        findViewById(R.id.animatorsDemo).setOnClickListener(this);
        findViewById(R.id.adapterDemo).setOnClickListener(this);
        findViewById(R.id.fireTheHall).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.progressDemo:
                //TODO implement
                Intent i =new Intent(this,ProgressStyleDemoActivity.class);
                startActivity(i);
                break;
            case R.id.animatorsDemo:
                //TODO implement
                Intent j =new Intent(this,MarshonAnimatorActivity.class);
                startActivity(j);
                break;
            case R.id.adapterDemo:
                //TODO implement
                Intent k =new Intent(this,MarshonFastAdapterActivity.class);
                startActivity(k);
                break;
            case R.id.fireTheHall:
                //TODO implement
                Intent l =new Intent(this,MarshonRecyclerViewActivity.class);
                startActivity(l);
                break;
        }
    }
}
