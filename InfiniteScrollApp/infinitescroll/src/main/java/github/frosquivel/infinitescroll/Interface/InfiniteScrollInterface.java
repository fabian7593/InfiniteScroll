package github.frosquivel.infinitescroll.Interface;

/**
 * Created by Fabian on 02/06/2017.
 * An interface for call onSuccess and onFailure if you needed
 */

public interface InfiniteScrollInterface {
    void onSuccess(Object responseObject);
    void onFailure(String errorResponse);
}
