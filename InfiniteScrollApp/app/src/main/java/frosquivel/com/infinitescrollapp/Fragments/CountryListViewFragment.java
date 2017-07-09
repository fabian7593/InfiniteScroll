package frosquivel.com.infinitescrollapp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.irozon.sneaker.Sneaker;
import com.konifar.fab_transformation.FabTransformation;

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
    private ConstraintLayout listViewConstraint;
    private ListView lvItems;
    private static ResponseModel responseModelStatic;

    private ImageView toolbar_git;
    private ImageView toolbar_linked_in;
    private ImageView toolbar_messenger;
    private ImageView toolbar_email;
    private ImageView toolbar_youtube;
    private ImageView toolbar_share;
    private ImageView toolbar_back;

    private Toolbar toolbarFooter;
    private FloatingActionButton fab;

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

        initUiComponents(rootView);

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

                @Override
                public void onFailure(String errorResponse){
                    //Utils.showSneakerDialog(getActivity(), errorResponse);
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

    private void initUiComponents(View rootView){
        toolbarFooter = (Toolbar) rootView.findViewById(R.id.toolbar_footer);
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);

        toolbar_back = (ImageView) rootView.findViewById(R.id.toolbar_back);
        toolbar_share = (ImageView) rootView.findViewById(R.id.toolbar_share);
        toolbar_youtube = (ImageView) rootView.findViewById(R.id.toolbar_youtube);
        toolbar_email = (ImageView) rootView.findViewById(R.id.toolbar_email);
        toolbar_messenger = (ImageView) rootView.findViewById(R.id.toolbar_messenger);
        toolbar_linked_in = (ImageView) rootView.findViewById(R.id.toolbar_linked_in);
        toolbar_git = (ImageView) rootView.findViewById(R.id.toolbar_git);

        listViewConstraint = (ConstraintLayout) rootView.findViewById(R.id.listViewConstraint);

        toolbar_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.sharedApp(activity);
            }
        });

        toolbar_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.openScheme(activity, activity.getString(R.string.scheme_scheme_youtube), activity.getString(R.string.scheme_id_youtube),
                        activity.getString(R.string.scheme_url_youtube), activity.getString(R.string.error_not_open_youtube));
            }
        });

        toolbar_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.sendMeAnEmail(activity);
            }
        });

        toolbar_messenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openScheme(activity, activity.getString(R.string.scheme_scheme_messenger), activity.getString(R.string.scheme_id_messenger),
                        activity.getString(R.string.scheme_url_messenger), activity.getString(R.string.error_not_open_messenger));
            }
        });

        toolbar_linked_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openScheme(activity, activity.getString(R.string.scheme_scheme_linkedin), activity.getString(R.string.scheme_id_linkedin),
                        activity.getString(R.string.scheme_url_linkedin), activity.getString(R.string.error_not_open_linkedin));
            }
        });

        toolbar_git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goToWebView(getString(R.string.menu_git_creator), activity);
            }
        });

        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabTransformation.with(fab)
                        .transformFrom(toolbarFooter);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabTransformation.with(fab)
                        .transformTo(toolbarFooter);
            }
        });

        toolbarFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabTransformation.with(fab)
                        .transformFrom(toolbarFooter);
            }
        });


        listViewConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabTransformation.with(fab)
                        .transformFrom(toolbarFooter);
            }
        });
    }
}
