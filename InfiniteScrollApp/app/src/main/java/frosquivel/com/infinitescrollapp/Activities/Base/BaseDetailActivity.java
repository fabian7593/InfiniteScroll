package frosquivel.com.infinitescrollapp.Activities.Base;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 16/06/2017.
 */

public class BaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected Activity addLayout(int layout){
        this.setContentView(layout);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        return this;
    }


    protected Activity addLayout(int layout, int toolbarId ){
        this.setContentView(layout);
        Toolbar toolbar = (Toolbar) this.findViewById(toolbarId);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        return this;
    }



    protected Activity addLayout(int layout, int toolbarId, String titleName){
        this.setContentView(layout);
        Toolbar toolbar = (Toolbar) this.findViewById(toolbarId);
        toolbar.setTitle(titleName);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        return this;
    }


}
