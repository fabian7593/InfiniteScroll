package frosquivel.com.infinitescrollapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.glomadrian.loadingballs.BallView;

import java.util.List;

import frosquivel.com.infinitescroll.Adapter.InfiniteScrollAdapter;
import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.R;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Fabian on 02/06/2017.
 * The country adapter of the list of countries,
 * Call the pictures with urls, and set the necesarry information in the list
 */
public class CountryAdapter extends InfiniteScrollAdapter  {

    private int rowLayout;
    private Context context;
    private Activity activity;

    //set activity, list of objects and layout of row
    public CountryAdapter(Activity activity, List<Object> values, int rowlayout){
        super(activity, values, rowlayout);
        rowLayout = rowlayout;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View view = null;
        context =  getContext();
        //convert the object in country class
        Country country = (Country)getItem(position);

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(rowLayout, null);
            view.setTag(view.findViewById(R.id.textView));
        }else{
            view = convertView;
        }

        //set the necessary text
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textViewSecond = (TextView) view.findViewById(R.id.textViewSecond);
        textView.setText(country.getName());
        textViewSecond.setText(country.getNativeName());

        //obtain the url of image flag
        final String flagUrl = country.getFlagPng();
        final ImageView imageCountry = (ImageView)view.findViewById(R.id.imageCountry);

        //init the progress bar
        final BallView progressBar = (BallView)view.findViewById(R.id.loadingBalls);
        progressBar.setVisibility(View.VISIBLE);

        //call the url of image country, and use glide for charge this async
        Glide.with(activity)
                .load(flagUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .bitmapTransform(new RoundedCornersTransformation(context,15,0))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        //you need the ui thread
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        //you need the ui thread
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                        return false;
                    }
                })
                .into(imageCountry);


        return view;
    }

}
