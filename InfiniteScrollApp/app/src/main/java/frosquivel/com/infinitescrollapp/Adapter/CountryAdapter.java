package frosquivel.com.infinitescrollapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import frosquivel.com.infinitescroll.Adapter.InfiniteScrollAdapter;
import frosquivel.com.infinitescrollapp.Classes.RequestApi;
import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.R;

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
        textViewSecond.setText(country.getAlpha2Code());

        final View asd = view;
       // imageCountry
        final String flagUrl = country.getFlag();
        new AsyncTask<Void, Void, Drawable>() {
            final ImageView imageCountry = (ImageView)asd.findViewById(R.id.imageCountry);
            protected void onPreExecute() {
            }

            protected Drawable doInBackground(Void... params) {

                try {
                    final URL url = new URL(flagUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    SVG svg = SVGParser. getSVGFromInputStream(inputStream);
                    Drawable drawable = svg.createPictureDrawable();
                    return drawable;
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage(), e);
                }

                return null;
            }

            protected void onPostExecute(Drawable imageResponse) {
                updateImageView(imageResponse, imageCountry);
            }
        }.execute();


        return view;
    }


    @SuppressLint("NewApi")
    private void updateImageView(Drawable drawable, ImageView imageCountry){
        if(drawable != null){
            // Try using your library and adding this layer type before switching your SVG parsing
            imageCountry.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            imageCountry.setImageDrawable(drawable);
        }
    }
}
