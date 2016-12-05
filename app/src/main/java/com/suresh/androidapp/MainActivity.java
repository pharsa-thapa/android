package com.suresh.androidapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.suresh.androidapp.fragments.ContactAddFragment;
import com.suresh.androidapp.fragments.ContactListFragment;
import com.suresh.androidapp.fragments.FlowerListFragment;
import com.suresh.androidapp.presenters.MainPresenter;
import com.suresh.androidapp.serviceManagers.NetworkServiceManager;
import com.suresh.androidapp.views.MainView;


public class MainActivity extends MvpActivity<MainView,MainPresenter> implements MainView, NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                String ide = "";
                Fragment mCurrentFragment = null;

                if (fm.getBackStackEntryCount() > 1) {
                    ide = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 2).getName();
                    mCurrentFragment = fm.findFragmentByTag(ide);
                }

                if (mCurrentFragment != null) {
                    Log.v("Last BackStack ID", mCurrentFragment.getTag());
                    toggleFragmentView(mCurrentFragment);

                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //hide progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.async_progress);
        progressBar.setVisibility(View.INVISIBLE);

    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        fab.setVisibility(View.VISIBLE);

        if (id == R.id.nav_flowers) {
            if (new NetworkServiceManager(this).isNetworkAvailable()) {
                fab.setVisibility(View.INVISIBLE);
                FlowerListFragment frag = new FlowerListFragment();
                toggleFragmentView(frag);
            } else {
                Toast.makeText(this, "Network Not Available", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_contacts_list) {
            ContactListFragment frag = new ContactListFragment();
            toggleFragmentView(frag);

        } else if (id == R.id.nav_contacts_add) {

            ContactAddFragment frag = new ContactAddFragment();
            toggleFragmentView(frag);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void toggleFragmentView(Fragment newFragment) {

        String backStateName = newFragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        FragmentTransaction ft = manager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(backStateName);


//        ft.replace(R.id.content_main, null, null);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
            //fragment not in back stack, create it.
            ft.replace(R.id.content_main, newFragment, fragmentTag);
            ft.commit();
        }

    }


    @Override
    public Context getContext() {
        return  this;
    }
}
