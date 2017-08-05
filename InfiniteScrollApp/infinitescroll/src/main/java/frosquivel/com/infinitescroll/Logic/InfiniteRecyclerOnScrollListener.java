package frosquivel.com.infinitescroll.Logic;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import frosquivel.com.infinitescroll.Model.InfiniteScrollObject;
import frosquivel.com.infinitescroll.Utils.InfiniteScrollUtil;

/**
 * Created by Fabian on 26/07/2017.
 */

public abstract class InfiniteRecyclerOnScrollListener extends InfiniteScrollRecycler{


    protected InfiniteScrollObject infiniteScrollObject;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private static int scrollStatusCount;
    private static boolean scrollFinalStatus = false;

    public InfiniteRecyclerOnScrollListener(RecyclerView recyclerView, Activity activity,
                                            InfiniteScrollObject infiniteScrollObject) {
        this.recyclerView = recyclerView;
        this.infiniteScrollObject = infiniteScrollObject;

        this.infiniteScrollObject.setLoading(true);
        this.infiniteScrollObject.setPreviousTotalItemCount(0);
        this.infiniteScrollObject.setCurrentPage(this.infiniteScrollObject.getCurrentPage()+1, false);

        linearLayoutManager = new LinearLayoutManager(activity);
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

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

        this.infiniteScrollObject.setVisibleItemCount(recyclerView.getChildCount());
        this.infiniteScrollObject.setTotalItemCount(linearLayoutManager.getItemCount());
        this.infiniteScrollObject.setFirstVisibleItem(linearLayoutManager.findFirstVisibleItemPosition());

        if (this.infiniteScrollObject.isLoading()) {
            if (this.infiniteScrollObject.getTotalItemCount() > this.infiniteScrollObject.getPreviousTotalItemCount()) {
                this.infiniteScrollObject.setLoading(false);
                this.infiniteScrollObject.setPreviousTotalItemCount(this.infiniteScrollObject.getTotalItemCount());
            }
        }

        int size = 0;
        if (!this.infiniteScrollObject.isLoading() && (this.infiniteScrollObject.getTotalItemCount() - this.infiniteScrollObject.getVisibleItemCount())
                <= (this.infiniteScrollObject.getFirstVisibleItem() + this.infiniteScrollObject.getMinimunNumberRowLoadingMore())) {
            // End has been reached
            if(InfiniteScrollUtil.isNetworkAvailable(infiniteScrollObject.getActivity())) {
                this.infiniteScrollObject.setCurrentPage(this.infiniteScrollObject.getCurrentPage() + 1, false);
                size =  onLoadMore(this.infiniteScrollObject.getCurrentPage(), this.infiniteScrollObject.getTotalItemCount());
                this.infiniteScrollObject.setLoading((size > 0) ? true : false);
            }
        }

        if(!scrollFinalStatus){
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
