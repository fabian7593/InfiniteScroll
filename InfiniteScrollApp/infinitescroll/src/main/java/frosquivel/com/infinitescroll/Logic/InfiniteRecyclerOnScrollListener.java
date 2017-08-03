package frosquivel.com.infinitescroll.Logic;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import frosquivel.com.infinitescroll.Model.InfiniteScrollObject;
import frosquivel.com.infinitescroll.Utils.InfiniteScrollUtil;

/**
 * Created by Fabian on 26/07/2017.
 */

public abstract class InfiniteRecyclerOnScrollListener extends InfiniteScrollRecycler{


    protected InfiniteScrollObject infiniteScrollObject;
    private LinearLayoutManager mLinearLayoutManager;

    public InfiniteRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager, InfiniteScrollObject infiniteScrollObject) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.infiniteScrollObject =  infiniteScrollObject;

        this.infiniteScrollObject.setLoading(true);
        this.infiniteScrollObject.setPreviousTotalItemCount(0);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        this.infiniteScrollObject.setVisibleItemCount(recyclerView.getChildCount());
        this.infiniteScrollObject.setTotalItemCount(mLinearLayoutManager.getItemCount());
        this.infiniteScrollObject.setFirstVisibleItem(mLinearLayoutManager.findFirstVisibleItemPosition());


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
                //this.infiniteScrollObject.setLoading(true);
                this.infiniteScrollObject.setLoading((size > 0) ? true : false);
            }
        }
    }

    public abstract int onLoadMore(int currentPage, int totalItemsCount);
}
