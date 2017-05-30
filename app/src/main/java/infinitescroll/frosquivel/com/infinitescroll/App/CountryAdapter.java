package infinitescroll.frosquivel.com.infinitescroll.App;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import infinitescroll.frosquivel.com.infinitescroll.App.Utilities.Country;
import infinitescroll.frosquivel.com.infinitescroll.Library.Adapter.InfiniteScrollAdapter;
import infinitescroll.frosquivel.com.infinitescroll.R;

/**
 * Created by Fabian on 27/05/2017.
 */

public class CountryAdapter extends InfiniteScrollAdapter {

    public CountryAdapter(Activity context, List<Object> values, int rowlayout){
        super(context, values, rowlayout);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        Country country = (Country)getItem(position);

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_row_item_list, null);
            view.setTag(view.findViewById(R.id.textView));
        }else{
            view = convertView;
        }

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textViewSecond = (TextView) view.findViewById(R.id.textViewSecond);
        textView.setText(country.getName());
        textViewSecond.setText(country.getAlpha2Code());

        return view;
    }
}
