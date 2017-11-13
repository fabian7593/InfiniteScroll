package github.frosquivel.infinitescroll.Logic;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import github.frosquivel.infinitescroll.Model.InfiniteScrollObject;
import github.frosquivel.infinitescroll.Utils.InfiniteScrollUtil;

/**
 * Created by Fabian on 26/07/2017.
 */

public abstract class InfiniteRecyclerOnScrollListener extends InfiniteScrollRecycler{

    //set the infinite scroll object
    protected InfiniteScrollObject infiniteScrollObject;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private static int scrollStatusCount;
    private static boolean scrollFinalStatus = false;

    //construct method
    public InfiniteRecyclerOnScrollListener(RecyclerView recyclerView, Activity activity,
                                            InfiniteScrollObject infiniteScrollObject) {
        this.recyclerView = recyclerView;
        this.infiniteScrollObject = infiniteScrollObject;

        //set the values of infinite scroll object
        this.infiniteScrollObject.setLoading(true);
        this.infiniteScrollObject.setPreviousTotalItemCount(0);
        this.infiniteScrollObject.setCurrentPage(this.infiniteScrollObject.getCurrentPage()+1, false);

        linearLayoutManager = new LinearLayoutManager(activity);
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        //this code view the state of the scrolled change
        //and define if is necessary hide the progress bar or not
        if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
            scrollStatusCount = 0;
        }else if(newState == RecyclerView.SCROLL_STATE_SETTLING){
            scrollStatusCount++;
        }else if(newState == RecyclerView.SCROLL_STATE_IDLE){
            scrollStatusCount++;
        }

        //the final of the list view only call 2 states
        //when this happens hide the progress bar
        if(scrollStatusCount == 1){
           // scrollFinalStatus = true;
            goneProgressBar();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        //when the user scroller, set the item visible in the device
        this.infiniteScrollObject.setVisibleItemCount(recyclerView.getChildCount());
        //set all items that have in the list
        this.infiniteScrollObject.setTotalItemCount(linearLayoutManager.getItemCount());
        //set the number of the first item showing
        this.infiniteScrollObject.setFirstVisibleItem(linearLayoutManager.findFirstVisibleItemPosition());

        //if is in state loading
        if (this.infiniteScrollObject.isLoading()) {
            //if total item count is more than prev total item count
            //state loading is false, and set previous total item count
            if (this.infiniteScrollObject.getTotalItemCount() > this.infiniteScrollObject.getPreviousTotalItemCount()) {
                this.infiniteScrollObject.setLoading(false);
                this.infiniteScrollObject.setPreviousTotalItemCount(this.infiniteScrollObject.getTotalItemCount());
            }
        }

        //return this variable when call onLoadMore
        int size = 0;
        if (!this.infiniteScrollObject.isLoading() && (this.infiniteScrollObject.getTotalItemCount() - this.infiniteScrollObject.getVisibleItemCount())
                <= (this.infiniteScrollObject.getFirstVisibleItem() + this.infiniteScrollObject.getMinimunNumberRowLoadingMore())) {
            // End has been reached
            //if have network for call the next page of service
            if(InfiniteScrollUtil.isNetworkAvailable(infiniteScrollObject.getActivity())) {
                //set the current page
                this.infiniteScrollObject.setCurrentPage(this.infiniteScrollObject.getCurrentPage() + 1, false);
                //call the load More
                size =  onLoadMore(this.infiniteScrollObject.getCurrentPage(), this.infiniteScrollObject.getTotalItemCount());
                this.infiniteScrollObject.setLoading((size > 0) ? true : false);
            }
        }

        //if the scroll final status is false
        if(!scrollFinalStatus){
            //show or hide the progress bar
            if(this.infiniteScrollObject.getProgressBar()!=null) {
                this.infiniteScrollObject.getProgressBar().setVisibility(
                        this.infiniteScrollObject.isLoading() ? View.VISIBLE : View.GONE);
            }
        }

        if(this.infiniteScrollObject.isLoading()){
            if(this.infiniteScrollObject.getVisibleItemCount() < 14 & this.infiniteScrollObject.getTotalItemCount() < 14){
                goneProgressBar();
            }
        }
    }

    //hide the progress bar
    private void goneProgressBar(){
        try{
            if(this.infiniteScrollObject.getProgressBar()!=null){
                this.infiniteScrollObject.getProgressBar().setVisibility(View.GONE);
            }
        }catch(Exception e){}
    }

    public abstract int onLoadMore(int currentPage, int totalItemsCount);
}
