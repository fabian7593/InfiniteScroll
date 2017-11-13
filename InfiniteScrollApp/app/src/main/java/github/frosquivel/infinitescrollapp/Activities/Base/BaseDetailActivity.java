package github.frosquivel.infinitescrollapp.Activities.Base;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import github.frosquivel.infinitescrollapp.R;

/**
 * Created by Fabian on 16/06/2017.
 * A base detail activity of tool bar with back arrow
 */

public class BaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Add layout and return the current activity, with all necessary logic of tool bar
    protected Activity addLayout(int layout){
        this.setContentView(layout);

        //set the toolbar and back arrow
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
