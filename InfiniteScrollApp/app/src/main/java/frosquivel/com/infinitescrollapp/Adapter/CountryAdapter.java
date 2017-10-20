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

 */
public class CountryAdapter extends InfiniteScrollAdapter  {

    private int rowLayout;
    private Context context;
    private Activity activity;

    public CountryAdapter(Activity activity, List<Object> values, int rowlayout){
        super(activity, values, rowlayout);
        rowLayout = rowlayout;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View view = null;
        context =  getContext();
        Country country = (Country)getItem(position);

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(rowLayout, null);
            view.setTag(view.findViewById(R.id.textView));
        }else{
            view = convertView;
        }

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textViewSecond = (TextView) view.findViewById(R.id.textViewSecond);
        textView.setText(country.getName());
        textViewSecond.setText(country.getNativeName());

        final String flagUrl = country.getFlagPng();
        final ImageView imageCountry = (ImageView)view.findViewById(R.id.imageCountry);

        final BallView progressBar = (BallView)view.findViewById(R.id.loadingBalls);
        progressBar.setVisibility(View.VISIBLE);

        Glide.with(activity)
                .load(flagUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .bitmapTransform(new RoundedCornersTransformation(context,15,0))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
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
