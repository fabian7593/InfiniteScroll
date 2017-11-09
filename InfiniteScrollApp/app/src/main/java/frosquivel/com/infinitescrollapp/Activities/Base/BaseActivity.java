package frosquivel.com.infinitescrollapp.Activities.Base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import frosquivel.com.infinitescrollapp.Activities.AboutActivity;
import frosquivel.com.infinitescrollapp.Activities.AwarenessActivity;
import frosquivel.com.infinitescrollapp.Activities.PaperOnboardingActivity;
import frosquivel.com.infinitescrollapp.Activities.SharedPreferenceActivity;
import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.Fragments.CountryListViewFragment;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 02/06/2017.
 * A base activity with menu drawer logic
 */

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //call the layour
        setContentView(R.layout.activity_main);

        //set the tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add the drawer layout and the image of user
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView imageViewBackground = (ImageView) headerView.findViewById(R.id.imageViewBackground);

        ImageView nav_drawer_linked_in = (ImageView) headerView.findViewById(R.id.nav_drawer_linked_in);
        ImageView nav_drawer_git = (ImageView) headerView.findViewById(R.id.nav_drawer_git);

        //animation of open or close drawer menu
        AnimationDrawable animacion;
        animacion = (AnimationDrawable)getResources().getDrawable(
                R.drawable.animation_list);
        imageViewBackground.setImageDrawable(animacion);
        animacion.setEnterFadeDuration(5000);
        animacion.setExitFadeDuration(4000);
        animacion.start();

        //set links for enter when user click in icons of drawer menu or photo
        nav_drawer_git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goToWebView(BaseActivity.this.getString(R.string.menu_personal_value), BaseActivity.this);
            }
        });

        nav_drawer_linked_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openScheme(BaseActivity.this, BaseActivity.this.getString(R.string.scheme_scheme_linkedin), BaseActivity.this.getString(R.string.scheme_id_linkedin),
                        BaseActivity.this.getString(R.string.scheme_url_linkedin), BaseActivity.this.getString(R.string.error_not_open_linkedin));
            }
        });

        imageViewBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.goToWebView(BaseActivity.this.getString(R.string.menu_personal_value), BaseActivity.this);
            }
        });

        //charge the fragment
        Utils.chargeFragments(new CountryListViewFragment(),
                getFragmentManager(), R.layout.fragment_country_list_view);

        //Utils.chargeFragments(new CountryRecyclerViewFragment(),
              //   getFragmentManager(), R.layout.fragment_country_recycler_view);
    }

    @Override
    public void onBackPressed() {
        //logic of on back press
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_infinite_scroll) {
            Utils.goToWebView(this.getString(R.string.menu_infinite_scroll_value), BaseActivity.this);
        } else if (id == R.id.nav_license) {
            Utils.goToWebView(this.getString(R.string.menu_license_value), BaseActivity.this);
        } else if (id == R.id.nav_country_api) {
            Utils.goToWebView(this.getString(R.string.menu_county_api_value), BaseActivity.this);
        } else if (id == R.id.nav_share) {
            Utils.sharedApp(BaseActivity.this);
        } else if (id == R.id.nav_on_boarding) {
            Intent mainIntent = new Intent(this, PaperOnboardingActivity.class);
            startActivity(mainIntent);
        } else if (id == R.id.nav_about) {
            Intent mainIntent = new Intent(this, AboutActivity.class);
            startActivity(mainIntent);
        } else if (id == R.id.nav_awareness) {
            Intent mainIntent = new Intent(this, AwarenessActivity.class);
            startActivity(mainIntent);
        } else if (id == R.id.nav_settings) {
            Intent mainIntent = new Intent(this, SharedPreferenceActivity.class);
            startActivity(mainIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if(fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

        return true;
    }
}