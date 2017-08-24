package frosquivel.com.infinitescrollapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import frosquivel.com.infinitescrollapp.Activities.Base.BaseDetailActivity;
import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.R;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Fabian on 06/08/2017.
 */

public class CountryDetailActivity extends BaseDetailActivity {

    private FloatingActionButton btnMap;
    private ImageView imgCountry;
    private TextView txtTitleDescription;
    private TextView txtTitle;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Country country = ( Country ) getIntent().getSerializableExtra("Country");

      //  activity = addLayout(R.layout.activity_country_detail, R.id.toolbar, country.getName());
        activity = addLayout(R.layout.activity_country_detail, R.id.toolbar, "");

        btnMap = (FloatingActionButton) activity.findViewById(R.id.btnMap);
        txtTitleDescription = (TextView) activity.findViewById(R.id.txtTitleDescription);
        txtTitle = (TextView) activity.findViewById(R.id.txtTitle);
        imgCountry = (ImageView) activity.findViewById(R.id.imgCountry);

        Glide.with(this)
                .load(country.getFlagPng())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                       /* activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });*/

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        /*activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });*/
                        return false;
                    }
                })
                .into(imgCountry);

        txtTitle.setText(country.getName());
       // txtTitleDescription.setText(country.getName());
    }
}
