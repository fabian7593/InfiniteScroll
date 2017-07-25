package frosquivel.com.infinitescrollapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import frosquivel.com.infinitescroll.Adapter.InfiniteScrollAdapter;
import frosquivel.com.infinitescroll.Interface.InfiniteScrollImpl;
import frosquivel.com.infinitescroll.Interface.InfiniteScrollInterface;
import frosquivel.com.infinitescroll.Logic.InfiniteScrollCallRequest;
import frosquivel.com.infinitescroll.Model.InfiniteScrollBuilder;
import frosquivel.com.infinitescroll.Model.InfiniteScrollObject;
import frosquivel.com.infinitescrollapp.Adapter.CountryAdapter;
import frosquivel.com.infinitescrollapp.Classes.Const;
import frosquivel.com.infinitescrollapp.Classes.RequestApi;
import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.Models.ResponseModel;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 24/07/2017.
 */

public class CountryListViewFragment extends CountryFragmentBase{

    public InfiniteScrollAdapter adapter;
    private List<Object> objectList;
    private ProgressBar progressBar;
    private ListView lvItems;
    private static ResponseModel responseModelStatic;
    private int fragmentId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentId = getArguments().getInt("R.Layout");

        final View rootView = super.onCreateView(inflater,container,savedInstanceState);
        //Create the relation of your layout and your progress bar
        View footer = activity.getLayoutInflater().inflate(R.layout.progress_bar, null);
        progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        lvItems = (ListView) rootView.findViewById(R.id.listView);
        lvItems.addFooterView(footer);

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();

        InfiniteScrollObject infiniteScrollObject = new InfiniteScrollBuilder(activity)
                .setProgressBar(progressBar)
                .setCurrentPage(Integer.parseInt(getValue(Const.C_CURRENT_PAGE)))
                .setMinimunNumberRowLoadingMore(Integer.parseInt(getValue(Const.C_MINIMUM_NUMBER_ROW_SHOW)))
                .build();

        lvItems.setOnScrollListener(new InfiniteScrollCallRequest(infiniteScrollObject) {
            @Override
            public int onLoadMoreData(int page, int totalItemsCount, ListView view) {
                return resquestAPIMethod(page);
            }
        });

        resquestAPIMethod(Integer.parseInt(getValue(Const.C_CURRENT_PAGE)));
    }

    @Override
    public void onStop() {
        super.onStop();

        adapter = null;
        lvItems.setAdapter(null);
        lvItems.setOnScrollListener(null);
    }

    public int resquestAPIMethod(final int offset) {
        if(Utils.isNetworkAvailable(activity)) {
            InfiniteScrollInterface interfaceInfinite = new InfiniteScrollImpl() {
                @Override
                public void onSuccess(Object responseModel) {
                    responseModelStatic = (ResponseModel) responseModel;

                    if (adapter != null) {
                        //if you need update the list view and your respective data
                        for (Country country : ((ResponseModel) responseModel).getResponse()) {
                            if(!objectList.contains(country))
                                objectList.add(country);
                        }

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
                        objectList = new ArrayList<Object>(((ResponseModel) responseModel).getResponse());
                        adapter = new CountryAdapter(activity, objectList, R.layout.row_item_list_view);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(adapter!=null)
                                    lvItems.setAdapter(adapter);
                            }
                        });
                    }
                }

                @Override
                public void onFailure(String errorResponse){
                    //Utils.showSneakerDialog(getActivity(), errorResponse);
                }
            };


            Utils.getSharedPreference(activity, Const.C_MAX_LIMIT);

            RequestApi.callCountryAPI(
                    String.format(
                            Const.C_URL_REQUEST_COUNTRYAPI,
                            getValue(Const.C_MAX_LIMIT), offset, getValue(Const.C_P_NAME),
                            getValue(Const.C_P_ALPHA_CODE_2), getValue(Const.C_P_ALPHA_CODE_3),
                            getValue(Const.C_P_AREA_FROM), getValue(Const.C_P_AREA_TO),
                            (getValue(Const.C_P_REGION).equals("All")) ? Const.C_EMPTY_STRING : getValue(Const.C_P_REGION),
                            (getValue(Const.C_P_SUB_REGION).equals("All")) ? Const.C_EMPTY_STRING : getValue(Const.C_P_SUB_REGION)),
                    activity, interfaceInfinite);


            if (responseModelStatic != null)
                return responseModelStatic.getResponse().size();
            else
                return 0;
        }else{
            return 0;
        }
    }

    private static String getValue(String constVar){
        return Utils.getSharedPreference(activity, constVar);
    }
}
