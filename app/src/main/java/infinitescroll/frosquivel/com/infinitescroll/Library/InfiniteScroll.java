package infinitescroll.frosquivel.com.infinitescroll.Library;

import android.widget.AbsListView;

/**
 * Created by Fabian on 09/05/2017.
 * https://gist.github.com/rogerhu/17aca6ad4dbdb3fa5892
 * Builder pattern
 * https://picodotdev.github.io/blog-bitix/2015/09/ejemplo-del-patron-de-diseno-builder/
 */

public class InfiniteScroll implements AbsListView.OnScrollListener {

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {}

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) { }

}
