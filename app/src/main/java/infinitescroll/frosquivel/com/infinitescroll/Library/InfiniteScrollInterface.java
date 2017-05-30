package infinitescroll.frosquivel.com.infinitescroll.Library;

import java.util.List;

import infinitescroll.frosquivel.com.infinitescroll.App.Utilities.ResponseModel;

/**
 * Created by Fabian on 27/05/2017.
 */

public interface InfiniteScrollInterface{
    void onSuccess(Object responseObject);
    void onFailure(String errorResponse);
}

