package frosquivel.com.infinitescrollapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import frosquivel.com.infinitescrollapp.Activities.Base.BaseDetailActivity;
import frosquivel.com.infinitescrollapp.Adapter.CountryDetailAdapter;
import frosquivel.com.infinitescrollapp.Classes.Const;
import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.R;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Fabian on 06/08/2017.
 */

public class CountryDetailActivity extends BaseDetailActivity {

    private FloatingActionButton btnMap;
    private ImageView imgCountry;
    private TextView txtTitle;

    private Activity activity;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Country country = ( Country ) getIntent().getSerializableExtra("Country");

        //  activity = addLayout(R.layout.activity_country_detail, R.id.toolbar, country.getName());
        activity = addLayout(R.layout.activity_country_detail, R.id.toolbar, "");

        btnMap = (FloatingActionButton) activity.findViewById(R.id.btnMap);

        txtTitle = (TextView) activity.findViewById(R.id.txtTitle);
        imgCountry = (ImageView) activity.findViewById(R.id.imgCountry);

        lv = (ListView) findViewById(R.id.listViewCountryDetail);
        List<HashMap<String,String>> listHashMap = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> hashmap = new HashMap<String, String>();

        hashmap.put(Const.C_TITLE_HASHMAP, "Alpha code 2");
        hashmap.put(Const.C_VALUE_HASHMAP, country.getAlpha2Code());
        listHashMap.add(hashmap);

        hashmap.put(Const.C_TITLE_HASHMAP, "Alpha code 3");
        hashmap.put(Const.C_VALUE_HASHMAP, country.getAlpha3Code());
        listHashMap.add(hashmap);

        hashmap.put(Const.C_TITLE_HASHMAP, "CurrencyName");
        hashmap.put(Const.C_VALUE_HASHMAP, country.getCurrencyName());
        listHashMap.add(hashmap);

        CountryDetailAdapter arrayAdapter = new CountryDetailAdapter(this, listHashMap, R.layout.row_item_static_list_view);
        lv.setAdapter(arrayAdapter);

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
