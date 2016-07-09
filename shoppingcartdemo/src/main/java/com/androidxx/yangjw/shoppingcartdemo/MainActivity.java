package com.androidxx.yangjw.shoppingcartdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidxx.yangjw.adapter.ConvertAdapter;
import com.androidxx.yangjw.adapter.ICallbackAdapter;
import com.androidxx.yangjw.bean.ProductBean;
import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.androidxx.yangjw.shoppingcart.dao.DaoMaster;
import com.androidxx.yangjw.shoppingcart.dao.DaoSession;
import com.androidxx.yangjw.shoppingcart.dao.ShoppingCart;
import com.androidxx.yangjw.shoppingcart.dao.ShoppingCartDao;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ICallbackAdapter,View.OnClickListener{

    private static final String TAG = "androidxx";
    public ListView mListView;
    public Button mAddShoppingCartBtn;
    private List<ProductBean.DataParentBean.ItemsBean> datas = new ArrayList<>();
    public ConvertAdapter mConvertAdapter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadDatas();
    }



    private void loadDatas() {
        mProgressDialog.show();
        OkHttpTool.newInstance().start("http://api.liwushuo.com/v2/items?gender=1&limit=20&offset=0&generation=2").callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                Gson gson = new Gson();
                ProductBean productBean = gson.fromJson(result, ProductBean.class);
                if (productBean != null && productBean.getData() != null) {
                    datas.addAll(productBean.getData().getItems());
                    mConvertAdapter.notifyDataSetChanged();
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    /**
     * 初始化试图
     */
    private void initView() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("正在吐血加载~");

        mListView = (ListView) findViewById(R.id.shopping_cart_lv);
        mAddShoppingCartBtn = (Button) findViewById(R.id.shopping_cart_submit_btn);
        mAddShoppingCartBtn.setOnClickListener(this);
        //初始化适配器
        mConvertAdapter = new ConvertAdapter(this);
        mConvertAdapter.setDatas(datas);
        mListView.setAdapter(mConvertAdapter);
    }


    @Override
    public View createView(int position,ViewGroup parent) {
        View view = LayoutInflater.from(this).inflate(R.layout.shopping_item, parent, false);
        //创建一个ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        ProductBean.DataParentBean.ItemsBean itemsBean = datas.get(position);
        viewHolder.nameTxt.setText(itemsBean.getData().getName() + "["+itemsBean.getData().getPrice()+"]");
        viewHolder.addBtn.setTag(position);
        return view;
    }

    @Override
    public void bindView(int position, View view) {
        //使用VIewHolder
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        ProductBean.DataParentBean.ItemsBean itemsBean = datas.get(position);
        viewHolder.nameTxt.setText(itemsBean.getData().getName() + "["+itemsBean.getData().getPrice()+"]");
        viewHolder.addBtn.setTag(position);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,CartActivity.class);
        startActivity(intent);
    }

    class ViewHolder implements View.OnClickListener{
        public Button addBtn;
        private TextView nameTxt;
        public ViewHolder(View view) {
            addBtn = (Button) view.findViewById(R.id.item_add_cart_btn);
            nameTxt = (TextView) view.findViewById(R.id.item_product_name);
            addBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int postion = (int) v.getTag();
            ProductBean.DataParentBean.ItemsBean itemsBean = datas.get(postion);
            try {
                //将商品存入数据库

                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setProductName(itemsBean.getData().getName());
                shoppingCart.setProductPrice(Float.valueOf(itemsBean.getData().getPrice()));
                shoppingCart.setProductNum(1);
//                Long key = mShoppingCartDao.getKey(shoppingCart);
//                Log.d(TAG, "onClick: " + key);
//                if (key != null && key <= 0) {
                    mShoppingCartDao.insertOrReplace(shoppingCart);
//                }

                List<ShoppingCart> shoppingCarts = mShoppingCartDao.loadAll();
                for (int i = 0; i < shoppingCarts.size(); i++) {
                    Log.d(TAG, "onClick: product_name= " + shoppingCarts.get(i).getProductName());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "价格异常，请联系商家", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
