package com.androidxx.yangjw.eventdispatchdemo.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.androidxx.yangjw.eventdispatchdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDrawerEventActivity extends AppCompatActivity {

    public ViewPager mViewPager;
    List<Fragment> fragmentList = new ArrayList<>();
    public MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_drawer_event);
        mViewPager = (ViewPager) findViewById(R.id.event_viewpager);
        initData();
        setupViewPager();
    }

    private void setupViewPager() {
        mAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    private void initData() {
        fragmentList.add(BlankFragment.newInstance("",""));
        fragmentList.add(BlankFragment.newInstance("",""));
        fragmentList.add(BlankFragment.newInstance("",""));
        fragmentList.add(BlankFragment.newInstance("",""));
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }


}
