package com.workpath.bookstreamapp.adapters;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.workpath.bookstreamapp.R;
import com.workpath.bookstreamapp.moudles.goods.goodsMoudle;
import com.workpath.bookstreamapp.myapp.MyAdapter;
import com.workpath.bookstreamapp.utils.ScreenUtils;

import java.util.List;

/**
 * Created by JayChen on 2020/3/10.
 */

public class GoodsAdapter extends MyAdapter<goodsMoudle>{


    private Context context;

    public GoodsAdapter(Context context, List<goodsMoudle> dataList, AdapterListener listener){
        super(dataList, listener);
        this.context = context;
    }

    @Override
    protected int getItemViewType(int position, goodsMoudle data) {
        return R.layout.goods_item;
    }


    @Override
    protected MyViewHolder<goodsMoudle> onCreateViewHolder(View root, int viewType) {
        return new goodsShowHolder(root);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public class goodsShowHolder extends MyViewHolder<goodsMoudle>{

        private ImageView mImageView;

        public goodsShowHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.goods_show);
        }

        @Override
        protected void onBind(goodsMoudle data, int position) {
            goodsMoudle liveBean=mDataList.get(position);
            //获取Item宽度，计算图片等比比例缩放后的高度，为ImageView设置参数
            LinearLayout.LayoutParams params1= (LinearLayout.LayoutParams) mImageView.getLayoutParams();
            float itemWidth=(ScreenUtils.getScreenWidth(itemView.getContext())-1*3)/2;

            //获取静态图片的宽高
            BitmapFactory.Options options = new BitmapFactory.Options();
            BitmapFactory.decodeResource(data.getContext().getResources(), data.getId(),options);
            int height = options.outHeight;
            int width = options.outWidth;

            //得到放缩后的宽高
            params1.width=(int)itemWidth;
            float scale=(itemWidth+0f)/width;
            params1.height=(int) (height*scale);
            mImageView.setLayoutParams(params1);

            //设置图片到控件
            mImageView.setImageResource(data.getId());

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
