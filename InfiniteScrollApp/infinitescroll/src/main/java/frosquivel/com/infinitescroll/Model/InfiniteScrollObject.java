package frosquivel.com.infinitescroll.Model;

import android.app.Activity;
import android.widget.ProgressBar;

/**
 * Created by Fabian on 02/06/2017.
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

    private Activity activity;

    InfiniteScrollObject(InfiniteScrollBuilder builder) {
        this.progressBar = builder.getProgressBar();
        this.minimunNumberRowLoadingMore = builder.getMinimunNumberRowLoadingMore();
        this.currentPage = builder.getCurrentPage();
        this.activity = builder.getActivity();

        this.firstVisibleItem = 0;
        this.visibleItemCount = 0;
        this.totalItemCount = 0;
        this.isFinalItem = 1;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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

    public int getMinimunNumberRowLoadingMore() {
        return minimunNumberRowLoadingMore;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage, boolean isFirst) {

        if(!isFirst)
            this.currentPage = currentPage;
        else
            this.currentPage = currentPage - 1;
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
