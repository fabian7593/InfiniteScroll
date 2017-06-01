package infinitescroll.frosquivel.com.infinitescroll.Library.Model;

import android.widget.ProgressBar;

/**
 * Created by Fabian on 13/05/2017.
 */

public class InfiniteScrollObject {
    // The minimum number of items to have below your current scroll position
    // before loading more.
    private int minimunNumberRowLoadingMore;
    // The current offset index of data you have loaded
    private int currentPage;
    // The total number of items in the dataset after the last load
    private int previousTotalItemCount;
    // True if we are still waiting for the last set of data to load.
    private boolean loading;

    private int isFinalItem;

    private ProgressBar progressBar;

    private int firstVisibleItem, visibleItemCount, totalItemCount;

    public InfiniteScrollObject(){
        this.minimunNumberRowLoadingMore = 3;
        this.currentPage = 0;
        this.previousTotalItemCount = 0;
        this.loading = true;
        this.progressBar = null;
        this.firstVisibleItem = 0;
        this.visibleItemCount = 0;
        this.totalItemCount = 0;
        this.isFinalItem = 1;
    }

    public int getFinalItem() {
        return isFinalItem;
    }

    public void setFinalItem(int finalItem) {
        isFinalItem = finalItem;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public int getMinimunNumberRowLoadingMore() {
        return minimunNumberRowLoadingMore;
    }

    public void setMinimunNumberRowLoadingMore(int minimunNumberRowLoadingMore) {
        this.minimunNumberRowLoadingMore = minimunNumberRowLoadingMore;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPreviousTotalItemCount() {
        return previousTotalItemCount;
    }

    public void setPreviousTotalItemCount(int previousTotalItemCount) {
        this.previousTotalItemCount = previousTotalItemCount;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public int getFirstVisibleItem() {
        return firstVisibleItem;
    }

    public void setFirstVisibleItem(int firstVisibleItem) {
        this.firstVisibleItem = firstVisibleItem;
    }

    public int getVisibleItemCount() {
        return visibleItemCount;
    }

    public void setVisibleItemCount(int visibleItemCount) {
        this.visibleItemCount = visibleItemCount;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }
}
