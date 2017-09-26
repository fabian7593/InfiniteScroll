package frosquivel.com.infinitescrollapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 20/09/2017.
 */

public class CountryDetailAdapter extends ArrayAdapter<HashMap<String,String>> {


    private int rowLayout;
    private Activity activity;
    private List<HashMap<String,String>> values;

    public CountryDetailAdapter(Activity activity, List<HashMap<String,String>> values, @LayoutRes int resource) {
        super(activity, resource);
        rowLayout = resource;
        this.activity = activity;
        this.values = values;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        HashMap<String,String> curentValue = this.values.get(position);

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(rowLayout, null);
            view.setTag(view.findViewById(R.id.textView));
        }else{
            view = convertView;
        }

        String title = curentValue.get("title");
        String value = curentValue.get("value");

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textViewSecond = (TextView) view.findViewById(R.id.textViewSecond);
        textView.setText(title);
        textViewSecond.setText(value);

        return view;
    }
}
