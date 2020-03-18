package com.workpath.bookstreamapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.activities.RentActivity;
import com.workpath.bookstreamapp.moudles.goods.BuyCoogsMoudle;
import com.workpath.bookstreamapp.myapp.MyAdapter;

import java.util.List;

/**
 * Created by JayChen on 2020/3/18.
 */

public class RentGoodsAdapter extends MyAdapter<BuyCoogsMoudle> {
    private Context context;

    public RentGoodsAdapter(Context context, List<BuyCoogsMoudle> dataList, AdapterListener listener){
        super(dataList,listener);
        this.context = context;
    }

    @Override
    protected int getItemViewType(int position, BuyCoogsMoudle data) {
        return R.layout.rent_goods_item;
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
        return new RentGoodsHolder(root);
    }

    public class RentGoodsHolder extends MyViewHolder<BuyCoogsMoudle>{

        private AppCompatImageView mAppCompatImageView;
        private TextView bookName,bookFrom,bookOrder,bookPrice;
        private Button bookRent;

        public RentGoodsHolder(View itemView) {
            super(itemView);
            mAppCompatImageView = (AppCompatImageView) itemView.findViewById(R.id.iv_show_book);
            bookName = (TextView) itemView.findViewById(R.id.tv_book_title);
            bookFrom = (TextView) itemView.findViewById(R.id.tv_book_from);
            bookOrder = (TextView) itemView.findViewById(R.id.tv_book_order);
            bookPrice = (TextView) itemView.findViewById(R.id.tv_book_price);
            bookRent = (Button) itemView.findViewById(R.id.btn_book_rent);

        }

        @Override
        protected void onBind(BuyCoogsMoudle data, int position) {
            bookRent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(context)
                            .setTitle("操作提醒")
                            .setMessage("是否继续租书？点击确认后可在“我的交易”中查看租赁订单……")
                            .setIcon(R.mipmap.ilogo)
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(context,"申请成功，请24小时内移步至处理点完成订单",Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create();
                    alertDialog.show();
                }
            });
            mAppCompatImageView.setBackgroundResource(data.getImageId());
            bookName.setText(data.getBookName());
            bookFrom.setText(data.getFromName());
            bookOrder.setText("第"+data.getOrder()+"版");
            bookPrice.setText(""+data.getPrice());
        }
    }
}
