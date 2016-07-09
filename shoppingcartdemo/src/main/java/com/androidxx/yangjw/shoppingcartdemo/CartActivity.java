package com.androidxx.yangjw.shoppingcartdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidxx.yangjw.adapter.ConvertAdapter;
import com.androidxx.yangjw.adapter.ICallbackAdapter;
import com.androidxx.yangjw.shoppingcart.dao.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity implements ICallbackAdapter{

    public ListView mListView;
    public Button mPayBtn;
    private List<ShoppingCart> datas = new ArrayList<>();
    public ConvertAdapter mConvertAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        loadDatas();
    }

    private void loadDatas() {
        List<ShoppingCart> shoppingCarts = mShoppingCartDao.loadAll();
        datas.addAll(shoppingCarts);
        mConvertAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.cart_list_lv);
        mPayBtn = (Button) findViewById(R.id.cart_pay_btn);
        mConvertAdapter = new ConvertAdapter(this);
        mConvertAdapter.setDatas(datas);
        mListView.setAdapter(mConvertAdapter);
    }

    @Override
    public View createView(int position,ViewGroup parent) {
        View view = LayoutInflater.from(this).inflate(R.layout.cart_item_view, null);
        ViewHolder viewHolder = new ViewHolder(view);

        ShoppingCart shoppingCart = datas.get(position);
        viewHolder.nameTxt.setText(shoppingCart.getProductName());
        viewHolder.numTxt.setText(shoppingCart.getProductNum()+"");
        viewHolder.minusBtn.setTag(position);
        viewHolder.plusBtn.setTag(position);
        return view;
    }

    @Override
    public void bindView(int position, View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        ShoppingCart shoppingCart = datas.get(position);
        viewHolder.nameTxt.setText(shoppingCart.getProductName());
        viewHolder.numTxt.setText(shoppingCart.getProductNum()+"");
        viewHolder.minusBtn.setTag(position);
        viewHolder.plusBtn.setTag(position);
    }

    class ViewHolder implements View.OnClickListener{
        public TextView nameTxt;
        public TextView numTxt;
        public Button plusBtn;
        public Button minusBtn;

        public ViewHolder(View view) {
            view.setTag(this);
            nameTxt = (TextView) view.findViewById(R.id.cart_item_name_txt);
            numTxt = (TextView) view.findViewById(R.id.cart_item_num_text);
            plusBtn = (Button) view.findViewById(R.id.cart_item_plus_btn);
            minusBtn = (Button) view.findViewById(R.id.cart_item_minus_btn);
            plusBtn.setOnClickListener(this);
            minusBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            ShoppingCart shoppingCart = datas.get(position);
            Integer productNum = shoppingCart.getProductNum();
            switch (v.getId()) {
                case R.id.cart_item_minus_btn:
                    numTxt.setText((--productNum < 1 ? 1 : productNum) + "");
                    break;
                case R.id.cart_item_plus_btn:
                    numTxt.setText(++productNum + "");
                    break;
            }
            shoppingCart.setProductNum(productNum);

            mShoppingCartDao.insertOrReplace(shoppingCart);
        }
    }
}
