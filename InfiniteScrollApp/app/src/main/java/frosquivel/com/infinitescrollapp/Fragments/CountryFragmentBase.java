package frosquivel.com.infinitescrollapp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.konifar.fab_transformation.FabTransformation;

import frosquivel.com.infinitescrollapp.Activities.SharedPreferenceActivity;
import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 02/06/2017.
 */

public class CountryFragmentBase extends Fragment {

    private ConstraintLayout listViewConstraint;
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
    protected static Activity activity;
    protected static Menu menu;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int chargeLayout = getArguments().getInt("R.Layout");
        final View rootView = inflater.inflate(chargeLayout, container, false);
        context = rootView.getContext();
        activity = getActivity();

        setHasOptionsMenu(true);
        initUiComponents(rootView);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);

       // MenuItem item = menu.findItem(R.id.action_list);
       // item.setVisible(false);
    }


    //Menu options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent mainIntent = new Intent(activity, SharedPreferenceActivity.class);
                startActivity(mainIntent);
                return true;

            case R.id.action_recycler:
                Utils.chargeFragments(new CountryRecyclerViewFragment(),
                        getFragmentManager(), R.layout.fragment_country_recycler_view);


                return true;

            case R.id.action_list:
                Utils.chargeFragments(new CountryListViewFragment(),
                        getFragmentManager(), R.layout.fragment_country_list_view);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
