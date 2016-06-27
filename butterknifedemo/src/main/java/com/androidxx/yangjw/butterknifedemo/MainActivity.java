package com.androidxx.yangjw.butterknifedemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidxx.yangjw.butterknifedemo.fragment.OthersFragment;
import com.androidxx.yangjw.butterknifedemo.fragment.WellChosenFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.guide_content_vp)
    ViewPager mViewPager;
    @BindView(R.id.guide_index_tl)
    TabLayout mTabLayout;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> mTitleDatas = new ArrayList<>();
    private MyViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //1、准备数据源
        setupData();
        setupTitleDatas();
        //2、创建适配器
        mViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        //3、关联适配器
        mViewPager.setAdapter(mViewPagerAdapter);

        //TabLayout初始化
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupTitleDatas() {
        mTitleDatas.add("精选");
        mTitleDatas.add("海淘");
        mTitleDatas.add("送女票");
        mTitleDatas.add("创意生活");
    }

    private void setupData() {
        fragments.add(WellChosenFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {


        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleDatas.get(position);
        }
    }


}
