package com.androidxx.yangjw.butterknifedemo.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidxx.yangjw.butterknifedemo.BannerInfo;
import com.androidxx.yangjw.butterknifedemo.R;
import com.androidxx.yangjw.butterknifedemo.constant.UrlConfig;
import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link WellChosenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WellChosenFragment extends Fragment {


    private View view;
    @BindView(R.id.wellchosen_list)
    ExpandableListView expandableListView;

    private Map<String,List<String>> datas = new HashMap<>();
    private List<BannerInfo.DataBean.BannersBean> imageDatas = new ArrayList<>();
    private List<String> groupNameDatas = new ArrayList<>();
    private Context mContext;
    private MyExpadnListAdapter myExpadnListAdapter;
    public HeaderRVAdapter headerRVAdapter;
    HeaderViewHolder headerViewHolder;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WellChosenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WellChosenFragment newInstance() {
        WellChosenFragment fragment = new WellChosenFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_well_chosen, container, false);
        ButterKnife.bind(this,view);
        setupHeaderView();
        setupExpandListView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始自动滚动
        headerViewHolder.convenientBanner.startTurning(2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止滚动
        headerViewHolder.convenientBanner.stopTurning();
    }

    /**
     * 添加头部布局
     */
    private void setupHeaderView() {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.wellchosen_header_view, null);
        headerViewHolder = new HeaderViewHolder(headerView);
        setupHeaderRecyclerView(headerViewHolder);
        loadBannerDatas();//动态加载数据
        setupBanner(headerViewHolder);
        expandableListView.addHeaderView(headerView);

    }

    private void loadBannerDatas() {
        OkHttpTool.newInstance().start(UrlConfig.BANNER_URL).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                BannerInfo bannerInfo = gson.fromJson(result, BannerInfo.class);
                imageDatas.addAll(bannerInfo.getData().getBanners());
                headerViewHolder.convenientBanner.getViewPager().getAdapter().notifyDataSetChanged();
            }
        });
    }

    private void setupBanner(HeaderViewHolder headerViewHolder) {

        headerViewHolder.convenientBanner.setPages(new CBViewHolderCreator<HeaderBannerViewHolder>() {
            @Override
            public HeaderBannerViewHolder createHolder() {
                return new HeaderBannerViewHolder();
            }
        },imageDatas)
        .setPageIndicator(new int[]{R.drawable.btn_check_disabled_nightmode,R.drawable.btn_check_normal})
        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
    }

    class HeaderBannerViewHolder implements Holder<BannerInfo.DataBean.BannersBean> {
        ImageView imageView;
        @Override
        public View createView(Context context) {
             imageView = new ImageView(context);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerInfo.DataBean.BannersBean data) {
//            imageView.setImageResource(data.getImage_url());
            Picasso.with(mContext).load(data.getImage_url()).into(imageView);
        }
    }

    /**
     * 初始化头部RecyclerView
     * @param headerViewHolder
     */
    private void setupHeaderRecyclerView(HeaderViewHolder headerViewHolder) {
//        headerViewHolder.mRecyclerView
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        headerViewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        headerRVAdapter = new HeaderRVAdapter();
        headerViewHolder.mRecyclerView.setAdapter(headerRVAdapter);
    }

    class HeaderViewHolder {

        @BindView(R.id.header_view_rv)
        RecyclerView mRecyclerView;
        @BindView(R.id.header_view_cb)
        ConvenientBanner convenientBanner;

        public HeaderViewHolder(View headerView) {
            ButterKnife.bind(this,headerView);
        }
    }



    //创建ViewHolder
    class HeaderRVViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public HeaderRVViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }

    //创建适配器
    class HeaderRVAdapter extends RecyclerView.Adapter<HeaderRVViewHolder> {

        @Override
        public HeaderRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(mContext);
            return new HeaderRVViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(HeaderRVViewHolder holder, int position) {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    /**
     * 初始化ExpandableListView
     */
    private void setupExpandListView() {

        //1、创建数据源
        setupDatas();
        //2、创建适配器
        myExpadnListAdapter = new MyExpadnListAdapter();
        //3、关联适配器
        expandableListView.setAdapter(myExpadnListAdapter);

        //设置ExpandableListView点击不收缩
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        //默认所有的Group全部展开
        for (int i = 0; i < groupNameDatas.size(); i++) {
            expandableListView.expandGroup(i);
        }

    }

    private void setupDatas() {
        if (groupNameDatas != null && !groupNameDatas.isEmpty()) {
            return;
        }
        for (int i = 0; i < 20; i++) {
            String groupName = "GROUP" + i;
            groupNameDatas.add(groupName);
            ArrayList<String> childListData = new ArrayList<>();
            datas.put(groupName,childListData);
            for (int j = 0; j < 10; j++) {
                childListData.add("CHILD" + j);
            }
        }
    }

    class MyExpadnListAdapter extends BaseExpandableListAdapter {

        /**
         * 返回分组的数量
         * @return
         */
        @Override
        public int getGroupCount() {
            return groupNameDatas == null ? 0 : groupNameDatas.size();
        }

        /**
         * 返回每一个组中的item的个数
         * @param groupPosition
         * @return
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = groupNameDatas.get(groupPosition);
            List<String> list = datas.get(key);
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupNameDatas.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        /**
         * 创建分组的视图
         * @param groupPosition
         * @param isExpanded
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = convertView;
            GroupViewHolder groupViewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.group_view, null);
                groupViewHolder = new GroupViewHolder(view);
            } else {
                groupViewHolder = (GroupViewHolder) view.getTag();
            }
            groupViewHolder.mLeftTxt.setText("2016-06-27 星期一");
            return view;
        }

        class GroupViewHolder {
            @BindView(R.id.group_left_tv)
            TextView mLeftTxt;
            @BindView(R.id.grou_right_tv)
            TextView mRightTxt;
            public GroupViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this,view);
            }
        }

        /**
         * 创建分组中的Item的视图
         * @param groupPosition
         * @param childPosition
         * @param isLastChild
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = convertView;
            ChildViewHolder childViewHolder = null;
            if (view == null) {
               view = LayoutInflater.from(mContext).inflate(R.layout.child_view, null);
                childViewHolder = new ChildViewHolder(view);
            } else {
                childViewHolder = (ChildViewHolder) view.getTag();
            }
            childViewHolder.mImageView.setImageResource(R.drawable.a3);
            return view;
        }

        class ChildViewHolder {
            @BindView(R.id.child_image)
            ImageView mImageView;
            public ChildViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this,view);
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }


}
