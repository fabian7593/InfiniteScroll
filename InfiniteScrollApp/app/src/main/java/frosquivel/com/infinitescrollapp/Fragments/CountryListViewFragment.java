package frosquivel.com.infinitescrollapp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import frosquivel.com.infinitescroll.Model.InfiniteScrollObject;
import frosquivel.com.infinitescrollapp.Adapter.CountryAdapter;
import frosquivel.com.infinitescrollapp.Classes.Const;
import frosquivel.com.infinitescrollapp.Classes.RequestApi;
import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.Models.Country;
import frosquivel.com.infinitescrollapp.Models.ResponseModel;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 02/06/2017.
 */

public class CountryListViewFragment extends Fragment {

    public InfiniteScrollAdapter adapter;
    private List<Object> objectList;
    private final int numberOfRequest = 10;
    private ProgressBar progressBar;
    private ListView lvItems;
    private static ResponseModel responseModelStatic;

    private Context context;
    private Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_country_list_view, container, false);
        context = rootView.getContext();
        activity = getActivity();

        setHasOptionsMenu(true);
        //Create the relation of your layout and your progress bar
        View footer = activity.getLayoutInflater().inflate(R.layout.progress_bar, null);
        progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        lvItems = (ListView) rootView.findViewById(R.id.listView);
        lvItems.addFooterView(footer);

        InfiniteScrollObject infiniteScrollObject = new InfiniteScrollObject(activity);
        infiniteScrollObject.setCurrentPage(1);
        infiniteScrollObject.setMinimunNumberRowLoadingMore(8);
        infiniteScrollObject.setProgressBar(progressBar);

        lvItems.setOnScrollListener(new InfiniteScrollCallRequest(infiniteScrollObject) {
            @Override
            public int onLoadMoreData(int page, int totalItemsCount, ListView view) {
                return resquestAPIMethod(page);
            }
        });

        resquestAPIMethod(1);

        return rootView;
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
                            objectList.add(country);
                        }

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        //if not have adapter, init this and set adapter in listview
                        objectList = new ArrayList<Object>(((ResponseModel) responseModel).getResponse());
                        adapter = new CountryAdapter(activity, objectList, R.layout.row_item_list_view);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lvItems.setAdapter(adapter);
                            }
                        });
                    }
                }
            };

            RequestApi.callCountryAPI(
                    String.format(Const.C_URL_REQUEST_COUNTRYAPI, "60", String.valueOf(offset)),
                    activity, interfaceInfinite);

            if (responseModelStatic != null)
                return responseModelStatic.getResponse().size();
            else
                return 0;
        }else{
            return 0;
        }
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchName = query;
                //coursesList = CoursesController.getCoursesWithFilters(dbHelper, query, searchCategory, idCoursesList);
                //setAdapter();
                return false;
            }

        });
    }
}
