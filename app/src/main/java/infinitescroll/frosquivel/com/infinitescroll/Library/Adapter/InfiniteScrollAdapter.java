package infinitescroll.frosquivel.com.infinitescroll.Library.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Fabian on 09/05/2017.
 */

public class InfiniteScrollAdapter extends ArrayAdapter<Object> {


    //the values that you need to shown in the listview.
    protected List<Object> values;

    /**
     * This is the constructor method of the class
     * @param context the context that you come, to call the super of the class.
     * @param values the list of items that you need to load in the listview
     * @param rowlayout the layout of row items, to call the super of the class
     */
    public InfiniteScrollAdapter(Activity context, List<Object> values, int rowlayout) {
        super(context, rowlayout, values);
        this.values = values;
    }

    /**
     * @return the quantity of items showing in the list view.
     */
    @Override
    public int getCount() {
        return this.values.size();
    }

    /**
     * This method override is for load the data of the list in the items of the listview,
     * but in this case you have to write this override when you create a intance of this class.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        return view;
    }
}
