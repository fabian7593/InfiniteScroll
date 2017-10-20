package frosquivel.com.infinitescrollapp.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 20/09/2017.
 */

public class CountryDetailAdapter extends ArrayAdapter<String> {
    private int rowLayout;
    private Activity activity;
    private List<String> values;
    private int xAutoIncrement;

    public CountryDetailAdapter(Activity activity, List<String> values, int resource) {
        super(activity, resource, values);
        rowLayout = resource;
        this.activity = activity;
        this.values = values;
        this.xAutoIncrement = 0;
    }

    @Override
    public int getCount() {
        return this.values.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);
        View view;
        String currentValue = this.values.get(this.xAutoIncrement);
        this.xAutoIncrement ++;

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(rowLayout, null);
            view.setTag(view.findViewById(R.id.textView));
        }else{
            view = convertView;
        }

        String title = null;
        if(position == 0){
            title = activity.getString(R.string.country_detail_name);
        }else if(position == 1){
            title = activity.getString(R.string.country_detail_native_name);
        }else if(position == 2){
            title = activity.getString(R.string.country_detail_region);
        }else if(position == 3){
            title = activity.getString(R.string.country_detail_sub_region);
        }else if(position == 4){
            title = activity.getString(R.string.country_detail_alpha_code_2);
        }else if(position == 5){
            title = activity.getString(R.string.country_detail_alpha_code_3);
        }else if(position == 6){
            title = activity.getString(R.string.country_detail_native_language);
        }else if(position == 7){
            title = activity.getString(R.string.country_detail_numeric_code);
        }else if(position == 8){
            title = activity.getString(R.string.country_detail_area);
        }else if(position == 9){
            title = activity.getString(R.string.country_detail_currency_name);
        }else if(position == 10){
            title = activity.getString(R.string.country_detail_currency_code);
        }else if(position == 11){
            title = activity.getString(R.string.country_detail_currency_symbol);
        }

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textViewSecond = (TextView) view.findViewById(R.id.textViewSecond);
        textView.setText(title);
        textViewSecond.setText(currentValue);

        return view;
    }

}
