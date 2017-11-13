package github.frosquivel.infinitescrollapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import github.frosquivel.infinitescroll.Adapter.InfiniteScrollAdapter;
import github.frosquivel.infinitescroll.Interface.InfiniteScrollImpl;
import github.frosquivel.infinitescroll.Interface.InfiniteScrollInterface;
import github.frosquivel.infinitescroll.Logic.InfiniteListOnScrollListener;
import github.frosquivel.infinitescroll.Model.InfiniteScrollBuilder;
import github.frosquivel.infinitescroll.Model.InfiniteScrollObject;
import github.frosquivel.infinitescrollapp.Activities.CountryDetailActivity;
import github.frosquivel.infinitescrollapp.Adapter.CountryAdapter;
import github.frosquivel.infinitescrollapp.Classes.Const;
import github.frosquivel.infinitescrollapp.Classes.RequestApi;
import github.frosquivel.infinitescrollapp.Classes.Utils;
import github.frosquivel.infinitescrollapp.Models.Country;
import github.frosquivel.infinitescrollapp.Models.ResponseModel;
import github.frosquivel.infinitescrollapp.R;

/**
 * Created by Fabian on 24/07/2017.
 * The example of implementation infinite scroll library in list view
 */

public class CountryListViewFragment extends CountryFragmentBase{

    //declare the adapter of infinite scroll
    public InfiniteScrollAdapter adapter;
    //declare the list of objects
    private List<Object> objectList;
    //declare progress bar
    private ProgressBar progressBar;
    //declare list view
    private ListView lvItems;
    //declare static response model, you can see more detail of this , with go to definition the class
    private static ResponseModel responseModelStatic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = super.onCreateView(inflater,container,savedInstanceState);
        //Create the relation of your layout and your progress bar
        View footer = activity.getLayoutInflater().inflate(R.layout.progress_bar, null);
        //set the progress bar and set visibility hidden
        progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        //set list view and set footer
        //this footer have the progressbar of the list
        lvItems = (ListView) rootView.findViewById(R.id.listView);
        lvItems.addFooterView(footer);

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        //I put the logic into onStart() event because I do the example with fragments
        //and for the correct functionallity I need to do that.

        //show a text of list view
        Toast.makeText(activity, "List view", Toast.LENGTH_SHORT).show();

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

        //set the on scroll listener of  the list view.
        //Declare a new instance of the library InfiniteListOnScrollListener, set the object in parameter.
        //and override the method of onLoadMore, in this part generate the request of the service,
        // with new page (of pagination rest api).
        lvItems.setOnScrollListener(new InfiniteListOnScrollListener(infiniteScrollObject) {
            @Override
            public int onLoadMoreData(int page, int totalItemsCount, ListView view) {
                //this need to return an int of size of the list
                //for use this number so that the logic of the library works correctly
                return resquestAPIMethod(page);
            }
        });

        //When the user click in item list view go to detail of the country
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

                Intent mainIntent = new Intent(activity, CountryDetailActivity.class);
                Bundle bundle = new Bundle();
                Country country = (Country) objectList.get(position);
                bundle.putSerializable("Country", country);
                mainIntent.putExtras(bundle);
                startActivity(mainIntent);
            }
        });

        //Call the request , with the current page that have (in this example), in shared preference memory
        resquestAPIMethod(Integer.parseInt(getValue(Const.C_CURRENT_PAGE)));
    }

    @Override
    public void onStop() {
        super.onStop();

        //if call to onStop() event, set null all data of adapter and scroll listener.
        adapter = null;
        lvItems.setAdapter(null);
        lvItems.setOnScrollListener(null);
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
                        adapter = new CountryAdapter(activity, objectList, R.layout.row_item_list_view);
                        //modify and refresh list into UiThread, its for best practices
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(adapter!=null)
                                    lvItems.setAdapter(adapter);
                            }
                        });
                    }
                }

                //If the request have a failure, call this event
                @Override
                public void onFailure(String errorResponse){
                    //Utils.showSneakerDialog(getActivity(), errorResponse);
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
