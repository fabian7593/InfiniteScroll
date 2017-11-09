package frosquivel.com.infinitescrollapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
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

/**
 * Created by Fabian on 06/08/2017.
 * The class to show the detail of country, set all data of the country selected
 * In a list, with the respective picture, and the map localization
 */

public class CountryDetailActivity extends BaseDetailActivity {

    private FloatingActionButton btnMap;
    private ImageView imgCountry;
    private TextView txtTitle;
    private TextView txtNativeName;
    private TextView txtRegion;
    private TextView txtSubRegion;
    private TextView txtDetailAlphaCode2;
    private TextView txtDetailAlphaCode3;
    private TextView txtNativeLanguage;
    private TextView txtNumericCode;
    private TextView txtArea;
    private TextView txtCurrencyName;
    private TextView txtCurrencyCode;
    private TextView txtCurrencySymbol;

    private Activity activity;
    private Country country = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        country = ( Country ) getIntent().getSerializableExtra("Country");

        activity = addLayout(R.layout.activity_country_detail, R.id.toolbar, "");

        btnMap = (FloatingActionButton) activity.findViewById(R.id.btnMap);
        txtTitle = (TextView) activity.findViewById(R.id.txtTitle);
        imgCountry = (ImageView) activity.findViewById(R.id.imgCountry);
        txtNativeName = (TextView) activity.findViewById(R.id.txtNativeName);
        txtRegion = (TextView) activity.findViewById(R.id.txtRegion);
        txtSubRegion = (TextView) activity.findViewById(R.id.txtSubRegion);
        txtDetailAlphaCode2 = (TextView) activity.findViewById(R.id.txtDetailAlphaCode2);
        txtDetailAlphaCode3 = (TextView) activity.findViewById(R.id.txtDetailAlphaCode3);
        txtNativeLanguage = (TextView) activity.findViewById(R.id.txtNativeLanguage);
        txtNumericCode = (TextView) activity.findViewById(R.id.txtNumericCode);
        txtArea = (TextView) activity.findViewById(R.id.txtArea);
        txtCurrencyName = (TextView) activity.findViewById(R.id.txtCurrencyName);
        txtCurrencyCode = (TextView) activity.findViewById(R.id.txtCurrencyCode);
        txtCurrencySymbol = (TextView) activity.findViewById(R.id.txtCurrencySymbol);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(CountryDetailActivity.this, GoogleMapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Country", country);
                mainIntent.putExtras(bundle);
                startActivity(mainIntent);
            }
        });

        Glide.with(this)
                .load(country.getFlagPng())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                        return false;
                    }
                })
                .into(imgCountry);

        txtTitle.setText(country.getName());
        txtNativeName.setText(country.getNativeName());
        txtRegion.setText(country.getRegion());
        txtSubRegion.setText(country.getSubRegion());
        txtDetailAlphaCode2.setText(country.getAlpha2Code());
        txtDetailAlphaCode3.setText(country.getAlpha3Code());
        txtNativeLanguage.setText(country.getNativeLanguage());
        txtNumericCode.setText(String.valueOf(country.getNumericCode()));
        txtArea.setText(String.valueOf(country.getArea()));
        txtCurrencyName.setText(country.getCurrencyName());
        txtCurrencyCode.setText(country.getCurrencyCode());
        txtCurrencySymbol.setText(country.getCurrencySymbol());
    }
}
