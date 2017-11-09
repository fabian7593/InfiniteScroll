package frosquivel.com.infinitescroll.Interface;

/**
 * Created by Fabian on 02/06/2017.
 * Implements the InfiniteScrollInterface for override the methods of onSuccess and onFailure
 * And set these not required methods override
 */

public class InfiniteScrollImpl implements InfiniteScrollInterface {

    public InfiniteScrollImpl (){}

    @Override
    public void onSuccess(Object responseObject){

    }

    @Override
    public void onFailure(String errorResponse){

    }
}