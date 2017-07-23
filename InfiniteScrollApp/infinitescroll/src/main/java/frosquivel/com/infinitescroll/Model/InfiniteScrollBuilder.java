package frosquivel.com.infinitescroll.Model;

import android.app.Activity;
import android.widget.ProgressBar;

/**
 * Created by Fabian on 22/07/2017.
 */

public class InfiniteScrollBuilder {
    // The minimum number of items to have below your current scroll position
    // before loading more.
    private int minimunNumberRowLoadingMore;
    // The current offset index of data you have loaded
    private int currentPage;
    private ProgressBar progressBar;
    private Activity activity;

    public InfiniteScrollBuilder(Activity activity){
        this.activity = activity;
        this.progressBar = null;
        this.minimunNumberRowLoadingMore = 3;
        this.currentPage = 0;
    }

    public InfiniteScrollBuilder setMinimunNumberRowLoadingMore(int minimunNumberRowLoadingMore) {
        this.minimunNumberRowLoadingMore = minimunNumberRowLoadingMore;
        return this;
    }

    public InfiniteScrollBuilder setCurrentPage(int currentPage) {
        this.currentPage = currentPage - 1;
        return this;
    }

    public InfiniteScrollBuilder setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
        return this;
    }

    public InfiniteScrollObject build() {
        return new InfiniteScrollObject(this);
    }


    public int getMinimunNumberRowLoadingMore() {
        return minimunNumberRowLoadingMore;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Activity getActivity() {
        return activity;
    }
}
