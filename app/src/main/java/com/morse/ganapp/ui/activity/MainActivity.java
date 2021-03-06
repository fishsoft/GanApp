package com.morse.ganapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.morse.ganapp.R;
import com.morse.ganapp.ui.adapter.GanPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.navigation)
    NavigationView mNavigation;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.pager)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        setViewPager();

        if (null != mNavigation) {
            setupDrawer();
            setupActionBarToggle();
        }

        mPager.setCurrentItem(0);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setViewPager() {
        String[] types = getResources().getStringArray(R.array.ganType);
        GanPagerAdapter adapter = new GanPagerAdapter(getSupportFragmentManager(), types);
        mPager.setAdapter(adapter);
        mTab.setupWithViewPager(mPager);
    }

    private void setupDrawer() {
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        break;
                    case R.id.navItem2:
                        break;
                    case R.id.navItem3:
                        break;
                }
                return false;
            }
        });
    }

    private void setupActionBarToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.natigation_open, R.string
                .navigation_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }
}
