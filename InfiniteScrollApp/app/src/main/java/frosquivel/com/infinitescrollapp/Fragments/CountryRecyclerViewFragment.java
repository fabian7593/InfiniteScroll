package frosquivel.com.infinitescrollapp.Fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import frosquivel.com.infinitescroll.Interface.InfiniteScrollImpl;
import frosquivel.com.infinitescroll.Interface.InfiniteScrollInterface;
import frosquivel.com.infinitescrollapp.Adapter.CountryAdapter;
import frosquivel.com.infinitescrollapp.Adapter.CountryRecyclerAdapter;
import frosquivel.com.infinitescrollapp.Classes.Const;
import frosquivel.com.infinitescrollapp.Classes.RequestApi;
import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.Models.ResponseModel;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 26/07/2017.
 */

public class CountryRecyclerViewFragment extends CountryFragmentBase {

    private List<Object> objectList;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private CountryRecyclerAdapter adapter;
    private static ResponseModel responseModelStatic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = super.onCreateView(inflater,container,savedInstanceState);
        //Create the relation of your layout and your progress bar
        View footer = activity.getLayoutInflater().inflate(R.layout.progress_bar, null);
        progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
       // recyclerView.addFooterView(footer);


        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
      //  recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        return rootView;
    }



    public void onStart() {
        super.onStart();
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        resquestAPIMethod(1);

      /*  recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               // Movie movie = movieList.get(position);
               // Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
    }


    @Override
    public void onStop() {
        super.onStop();

        adapter = null;
        recyclerView.setAdapter(null);
        recyclerView.setOnScrollListener(null);
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
                        adapter = new CountryRecyclerAdapter(objectList, activity);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(adapter!=null)
                                    recyclerView.setAdapter(adapter);
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
