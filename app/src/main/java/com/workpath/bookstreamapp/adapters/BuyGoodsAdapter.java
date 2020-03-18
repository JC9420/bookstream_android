package com.workpath.bookstreamapp.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.moudles.goods.BuyCoogsMoudle;
import com.workpath.bookstreamapp.myapp.MyAdapter;

import java.util.List;

/**
 * Created by JayChen on 2020/3/18.
 */

public class BuyGoodsAdapter extends MyAdapter<BuyCoogsMoudle> {
    private Context context;

    public BuyGoodsAdapter(Context context, List<BuyCoogsMoudle> dataList, AdapterListener listener){
        super(dataList,listener);
        this.context = context;
    }

    @Override
    protected int getItemViewType(int position, BuyCoogsMoudle data) {
        return R.layout.buy_goods_item;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected MyViewHolder<BuyCoogsMoudle> onCreateViewHolder(View root, int viewType) {
        return new BuyGoodsHolder(root);
    }

    public class BuyGoodsHolder extends MyViewHolder<BuyCoogsMoudle>{

        private AppCompatImageView mAppCompatImageView;
        private TextView bookName,bookFrom,bookOrder,bookPrice;
        private Button bookAlong;

        public BuyGoodsHolder(View itemView) {
            super(itemView);
            mAppCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.iv_show_book);
            bookName = (TextView) itemView.findViewById(R.id.tv_book_title);
            bookFrom = (TextView) itemView.findViewById(R.id.tv_book_from);
            bookOrder = (TextView) itemView.findViewById(R.id.tv_book_order);
            bookPrice = (TextView) itemView.findViewById(R.id.tv_book_price);
            bookAlong = (Button) itemView.findViewById(R.id.btn_book_along);
        }

        @Override
        protected void onBind(BuyCoogsMoudle data, int position) {
            mAppCompatImageView.setBackgroundResource(data.getImageId());
            bookName.setText(data.getBookName());
            bookFrom.setText(data.getFromName());
            bookOrder.setText("第"+data.getOrder()+"版");
            bookPrice.setText(""+data.getPrice());
            if(data.getWhoGet()==0) bookAlong.setText("平台");
            else bookAlong.setText("私人");
        }
    }
}
