package github.frosquivel.infinitescrollapp.Fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import github.frosquivel.infinitescroll.Interface.InfiniteScrollImpl;
import github.frosquivel.infinitescroll.Interface.InfiniteScrollInterface;
import github.frosquivel.infinitescroll.Logic.InfiniteRecyclerOnScrollListener;
import github.frosquivel.infinitescroll.Model.InfiniteScrollBuilder;
import github.frosquivel.infinitescroll.Model.InfiniteScrollObject;
import github.frosquivel.infinitescrollapp.Adapter.CountryRecyclerAdapter;
import github.frosquivel.infinitescrollapp.Classes.Const;
import github.frosquivel.infinitescrollapp.Classes.RequestApi;
import github.frosquivel.infinitescrollapp.Classes.Utils;
import github.frosquivel.infinitescrollapp.Models.Country;
import github.frosquivel.infinitescrollapp.Models.ResponseModel;
import github.frosquivel.infinitescrollapp.R;

/**
 * Created by Fabian on 26/07/2017.
 * The example of implementation infinite scroll library in recycler view
 */

public class CountryRecyclerViewFragment extends CountryFragmentBase {
    //declare the list of objects
    private List<Object> objectList;
    //declare progress bar
    private ProgressBar progressBar;
    //declare the recycler view
    private RecyclerView recyclerView;
    //declare adapter of  recycler view, this is not necesary to extends of any other class of the library
    private CountryRecyclerAdapter adapter;
    //declare static response model, you can see more detail of this , with go to definition the class
    private static ResponseModel responseModelStatic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = super.onCreateView(inflater,container,savedInstanceState);
        //Create the relation of your layout and your progress bar
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        return rootView;
    }

    public void onStart() {
        super.onStart();

        //show a text of recycler view
        Toast.makeText(activity, "Recycler view", Toast.LENGTH_SHORT).show();

        //Call the request , with the current page that have (in this example), in shared preference memory
        resquestAPIMethod(Integer.parseInt(getValue(Const.C_CURRENT_PAGE)));

        //Init the infinite scroll object with builder
        //and set the necessary parameters (but isnt required).
        //In this case we need progress bar to show when the library call the service.
        //Set the current page (of rest pagination) of the service.
        //Minimun number of row to loading more, for set a minimun number to call the requets
        //for example if the number is 5, when the user is missing 5 numbers to end the list, this activate the
        //call of the request to avoid see the end of the list
        InfiniteScrollObject infiniteScrollObject = new InfiniteScrollBuilder(activity)
                .setProgressBar(progressBar)
                .setCurrentPage(Integer.parseInt(getValue(Const.C_CURRENT_PAGE)))
                .setMinimunNumberRowLoadingMore(Integer.parseInt(getValue(Const.C_MINIMUM_NUMBER_ROW_SHOW)))
                .build();

        //set the recyvler animation
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //set the on scroll listener of  the recycler view.
        //Declare a new instance of the library InfiniteRecyclerOnScrollListener, set the object in parameter.
        //and override the method of onLoadMore, in this part generate the request of the service,
        // with new page (of pagination rest api).
        recyclerView.addOnScrollListener(new InfiniteRecyclerOnScrollListener(recyclerView, activity, infiniteScrollObject) {
            @Override
            public int onLoadMore(int currentPage, int totalItemsCount) {
                return resquestAPIMethod(currentPage);
            }
        });

    }

    @Override
    public void onStop() {
        //if call to onStop() event, set null all data of adapter and scroll listener.
        super.onStop();
        adapter = null;
        recyclerView.setAdapter(null);
    }

    //method of request the country api
    public int resquestAPIMethod(final int offset) {
        //verify if have network connection
        if(Utils.isNetworkAvailable(activity)) {

            //Instance the InfiniteScrollInterface with the class InfiniteScrollImpl
            InfiniteScrollInterface interfaceInfinite = new InfiniteScrollImpl() {

                //If the request is success and get data, call onSuccess event of this interface
                //The parameter is an object and you cast this object to another class type
                @Override
                public void onSuccess(Object responseModel) {
                    //set the response model in a static variable
                    responseModelStatic = (ResponseModel) responseModel;

                    //if the adapter its initialized, do the next logic
                    if (adapter != null) {
                        //if you need update the list view and your respective data
                        //foreach the response Model class and declare Country
                        for (Country country : ((ResponseModel) responseModel).getResponse()) {
                            //if the object nor have the information of the object, add to list
                            if(!objectList.contains(country))
                                objectList.add(country);
                        }

                        //For a good practice, you need to refresh the list view into UiThread
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    adapter.notifyDataSetChanged();
                                }catch(Exception ex){}

                            }
                        });
                    } else {
                        //if not have adapter, init this and set adapter in listview
                        //set all data into object list
                        objectList = new ArrayList<Object>(((ResponseModel) responseModel).getResponse());
                        //declare for firt time the adapter, and pass the params required
                        adapter = new CountryRecyclerAdapter(objectList, activity);
                        //modify and refresh list into UiThread, its for best practices
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(adapter!=null)
                                    recyclerView.setAdapter(adapter);
                            }
                        });
                    }
                }

                //If the request have a failure, call this event
                @Override
                public void onFailure(String errorResponse){
                   // Utils.showSneakerDialog(activity, errorResponse);
                    Toast.makeText(activity, errorResponse, Toast.LENGTH_SHORT).show();
                }
            };

            //Call the request of country api
            //In this example call a static method, and pass the params
            //This params value are in shared preference memory and you have to change this in
            //settings option.
            //This create a string format for build an url for realized the request.
            // You can see the example of this url already formed in the github of this service:
            // https://github.com/fabian7593/CountryAPI  (I create this rest api for this example)
            // If you give me an star I will thank you.
            RequestApi.callCountryAPI(
                    String.format(
                            Const.C_URL_REQUEST_COUNTRYAPI,
                            getValue(Const.C_MAX_LIMIT), offset, getValue(Const.C_P_NAME),
                            getValue(Const.C_P_ALPHA_CODE_2), getValue(Const.C_P_ALPHA_CODE_3),
                            getValue(Const.C_P_AREA_FROM), getValue(Const.C_P_AREA_TO),
                            (getValue(Const.C_P_REGION).equals("All")) ? Const.C_EMPTY_STRING : getValue(Const.C_P_REGION),
                            (getValue(Const.C_P_SUB_REGION).equals("All")) ? Const.C_EMPTY_STRING : getValue(Const.C_P_SUB_REGION)),
                    activity, interfaceInfinite);

            //if the static variable of response objects have data return the size of this, else return 0
            if (responseModelStatic != null)
                return responseModelStatic.getResponse().size();
            else
                return 0;
        }else{
            return 0;
        }
    }

    //get the value of shared preference
    private static String getValue(String constVar){
        return Utils.getSharedPreference(activity, constVar);
    }
}
