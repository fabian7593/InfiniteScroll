package frosquivel.com.infinitescrollapp.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.glomadrian.loadingballs.BallView;

import java.util.List;

import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.R;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Fabian on 26/07/2017.
 */

public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.CountryHolder> {

    private List<Object> objectList;
    private Activity activity;

    public class CountryHolder extends RecyclerView.ViewHolder {
        public TextView textView,textViewSecond;
        public ImageView imageCountry;
        public BallView progressBar;

        public CountryHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            textViewSecond = (TextView) view.findViewById(R.id.textViewSecond);
            imageCountry = (ImageView) view.findViewById(R.id.imageCountry);
            progressBar = (BallView)view.findViewById(R.id.loadingBalls);
        }
    }

//constructor set the objetc list, and the activity
    public CountryRecyclerAdapter(List<Object> objectList, Activity activity) {
        this.objectList = objectList;
        this.activity = activity;
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_recycler_view, parent, false);

        return new CountryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        //convert object to country object
        Country country = (Country)objectList.get(position);

        //set necessary data into text view and image
        holder.textView.setText(country.getName());
        holder.textViewSecond.setText(country.getNativeName());
        holder.progressBar.setVisibility(View.VISIBLE);

        final CountryHolder holderFinal = holder;

        final String flagUrl = country.getFlagPng();

        Glide.with(activity)
                .load(flagUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .bitmapTransform(new RoundedCornersTransformation(activity,15,0))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holderFinal.progressBar.setVisibility(View.GONE);
                            }
                        });

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holderFinal.progressBar.setVisibility(View.GONE);
                            }
                        });
                        return false;
                    }
                })
                .into(holder.imageCountry);


    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

}
