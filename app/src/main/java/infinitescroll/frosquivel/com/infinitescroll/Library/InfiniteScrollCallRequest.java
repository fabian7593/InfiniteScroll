package infinitescroll.frosquivel.com.infinitescroll.Library;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import infinitescroll.frosquivel.com.infinitescroll.Library.InfiniteScroll;
import infinitescroll.frosquivel.com.infinitescroll.Library.Model.InfiniteScrollObject;

/**
 * Created by Fabian on 13/05/2017.
 */

public abstract class InfiniteScrollCallRequest extends InfiniteScroll {

    protected InfiniteScrollObject infiniteScrollObject;

    public InfiniteScrollCallRequest() {
        this.infiniteScrollObject = new InfiniteScrollObject();
    }

    public InfiniteScrollCallRequest(InfiniteScrollObject infiniteScrollObject) {
        this.infiniteScrollObject = infiniteScrollObject;
    }

    public InfiniteScrollCallRequest(int currentPage, int minimunNumberRowLoadingMore) {
        this.infiniteScrollObject = new InfiniteScrollObject();
        this.infiniteScrollObject.setCurrentPage(currentPage);
        this.infiniteScrollObject.setMinimunNumberRowLoadingMore(minimunNumberRowLoadingMore);
    }


    public abstract boolean onLoadMoreData(int page, int totalItemsCount, ListView view);

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        this.infiniteScrollObject.setFirstVisibleItem(firstVisibleItem);
        this.infiniteScrollObject.setVisibleItemCount(visibleItemCount);
        this.infiniteScrollObject.setTotalItemCount(totalItemCount);

        if (totalItemCount < this.infiniteScrollObject.getPreviousTotalItemCount()) {
            this.infiniteScrollObject.setCurrentPage(0);
            this.infiniteScrollObject.setPreviousTotalItemCount(totalItemCount);

            if (totalItemCount == 0) {
                this.infiniteScrollObject.setLoading(true);
            }
        }

        if (this.infiniteScrollObject.isLoading() && (totalItemCount > this.infiniteScrollObject.getPreviousTotalItemCount())) {
            this.infiniteScrollObject.setLoading(false);
            this.infiniteScrollObject.setPreviousTotalItemCount(totalItemCount);
            this.infiniteScrollObject.setCurrentPage(this.infiniteScrollObject.getCurrentPage()+1);
        }

        if (!this.infiniteScrollObject.isLoading() &&
                (firstVisibleItem + visibleItemCount + this.infiniteScrollObject.getMinimunNumberRowLoadingMore())
                        >= totalItemCount ) {
            this.infiniteScrollObject.setLoading(onLoadMoreData(this.infiniteScrollObject.getCurrentPage() + 1, totalItemCount, (ListView) view));
        }

        if(this.infiniteScrollObject.getProgressBar() != null){
            this.infiniteScrollObject.getProgressBar().setVisibility(
                    this.infiniteScrollObject.isLoading() ? View.VISIBLE : View.GONE);
        }
    }
}
