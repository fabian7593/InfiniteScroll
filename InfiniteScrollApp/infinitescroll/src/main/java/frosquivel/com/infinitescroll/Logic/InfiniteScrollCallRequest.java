package frosquivel.com.infinitescroll.Logic;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import frosquivel.com.infinitescroll.Model.InfiniteScrollObject;
import frosquivel.com.infinitescroll.Utils.InfiniteScrollUtil;

/**
 * Created by Fabian on 02/06/2017.
 */

public abstract class InfiniteScrollCallRequest extends InfiniteScroll {

    protected InfiniteScrollObject infiniteScrollObject;
    //int of value scroll state changes
    private static int scrollStatusCount;

    /**
     * Constructor overoad with infinite scroll object
     * @param infiniteScrollObject
     */
    public InfiniteScrollCallRequest(InfiniteScrollObject infiniteScrollObject) {
        this.infiniteScrollObject = infiniteScrollObject;
        scrollStatusCount=0;
    }

    //abstract method for call when you have the response service
    public abstract int onLoadMoreData(int page, int totalItemsCount, ListView view);

    //when the user realize scroll
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if(InfiniteScrollUtil.isNetworkAvailable(infiniteScrollObject.getActivity())) {
            //item that the user view
            this.infiniteScrollObject.setFirstVisibleItem(firstVisibleItem);
            //number of items view
            this.infiniteScrollObject.setVisibleItemCount(visibleItemCount);
            //all the items
            this.infiniteScrollObject.setTotalItemCount(totalItemCount);

            if (totalItemCount < this.infiniteScrollObject.getPreviousTotalItemCount()) {
                this.infiniteScrollObject.setCurrentPage(0, false);
                this.infiniteScrollObject.setPreviousTotalItemCount(totalItemCount);

                //if not have data, every time shown loading progress
                if (totalItemCount == 0) {
                    this.infiniteScrollObject.setLoading(true);
                }
            }

            //is items is more that totalItemCount and not have loading more data
            //obtain the size of the last request and set the value of new loading
            int size = 0;
            if(InfiniteScrollUtil.isNetworkAvailable(infiniteScrollObject.getActivity())) {
                if (!this.infiniteScrollObject.isLoading() &&
                        (firstVisibleItem + visibleItemCount + this.infiniteScrollObject.getMinimunNumberRowLoadingMore())
                                >= totalItemCount) {

                    size = onLoadMoreData(
                            this.infiniteScrollObject.getCurrentPage() + 1, totalItemCount, (ListView) view);
                    //set false if not have data or list have 0 items
                    this.infiniteScrollObject.setLoading((size > 0) ? true : false);
                }
            }

            //Hide or show the progress bar if is loading data or not
            if (this.infiniteScrollObject.getProgressBar() != null) {
                this.infiniteScrollObject.getProgressBar().setVisibility(
                        this.infiniteScrollObject.isLoading() ? View.VISIBLE : View.GONE);
            }

            if(InfiniteScrollUtil.isNetworkAvailable(infiniteScrollObject.getActivity())) {
                //if is loading and total new items is more than previous total items
                //set loading, sum one page to current page
                if (this.infiniteScrollObject.isLoading() && (totalItemCount > this.infiniteScrollObject.getPreviousTotalItemCount())) {
                    this.infiniteScrollObject.setLoading(false);
                    this.infiniteScrollObject.setPreviousTotalItemCount(totalItemCount);
                    this.infiniteScrollObject.setCurrentPage(this.infiniteScrollObject.getCurrentPage() + 1, false);
                    //if the status is state fling gone the progress bar
                } else if (scrollStatusCount == SCROLL_STATE_FLING) {
                    goneProgressBar();
                }
            }else{
                this.infiniteScrollObject.setLoading(false);
            }

            //if only have one group of request api (first time), hidden loading
            if(this.infiniteScrollObject.isLoading()){
                if(visibleItemCount < 14 & totalItemCount < 14){
                    goneProgressBar();
                }
            }
        }else{
            this.infiniteScrollObject.setLoading(false);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
        if(scrollState == SCROLL_STATE_TOUCH_SCROLL){
            scrollStatusCount = 0;
        }else if(scrollState == SCROLL_STATE_IDLE){
            scrollStatusCount++;
        }else if(scrollState == SCROLL_STATE_FLING){
            scrollStatusCount++;
        }

        //the final of the list view only call 2 states
        //when this happens hide the progress bar
        if(scrollStatusCount == 1){
            goneProgressBar();
        }
    }

    //hide the progress bar
    private void goneProgressBar(){
        try{
            this.infiniteScrollObject.getProgressBar().setVisibility(View.GONE);
        }catch(Exception e){}
    }

}
